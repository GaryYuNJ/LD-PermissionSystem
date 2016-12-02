package com.ldps.event.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;  
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.ldps.dao.CusGroupResGroupRelModelMapper;
import com.ldps.dao.CusResourceRelModelMapper;
import com.ldps.dao.CustomerResGroupRelModelMapper;
import com.ldps.dao.PermissionRecordModelMapper;
import com.ldps.dao.ResourceGrpRelModelMapper;
import com.ldps.data.CusAndCusGrpRelChangeEventData;
import com.ldps.event.RemoveCusFromCusGrpPermChangeEvent;
import com.ldps.model.CusGroupResGroupRelModel;
import com.ldps.model.CusGrpResourceRelModel;
import com.ldps.model.CusResourceRelModel;
import com.ldps.model.PermissionRecordModel;
import com.ldps.service.ICusGrpResourceRelService;
import com.ldps.service.ICusResourceRelService;
import com.ldps.service.IUserService;


/*
	用户组删除用户带来的用户与资源关系更改 异步 监听
	删除用户来自用户组与资源关系的权限
	
先找到所有要检查的用户与资源关系；
    下面循环处理每个资源：
    	如果当前用户对资源没有权限，不做任何动作，返回；
    	如果有权限：
    		查看权限更新记录表，看看资源与用户最新的记录是否是当前用户组带来的：
    			如果是，恢复为上一个记录的权限关系，如果没有上一个记录，直接删除权限；
    			如果不是，不做任何动作；
    
   下面的操作过于复杂，并且 有逻辑缺陷，不使用。
-----	    用户与资源的关系不与移除的关系相匹配(时间范围不一致，一致说明不是移除的关系带来的权限，此时不更新用户与资源关系)
-----	    用户与资源的关系与移除的关系相匹配(时间范围一致，说明可能是)：
-----        a) 找到 用户所属用户组对当前资源的权限，取更新、创建时间最新的；
-----        b) 找到 用户所属用户组对包含当前资源的资源组权限，取更新、创建时间最新的；
-----        c) 找到用户与资源组对当前资源的权限，取更新、创建时间最新的；
-----        d) 上面a,b,c合并取时间最新的，更新用户与资源关系；
-----        e) 如果a,b,c没有找到对应关系，直接清除用户与资源关系；
*/
@Component
public class RemoveCusFromCusGrpPermChangeListener implements ApplicationListener<RemoveCusFromCusGrpPermChangeEvent> {

	@Resource
	ICusResourceRelService iCusResourceRelService;
	@Resource
	CusGroupResGroupRelModelMapper cusGroupResGroupRelDao;
	@Resource
	ICusGrpResourceRelService iCusGrpResourceRelService;
	@Resource
	CustomerResGroupRelModelMapper customerResGroupRelDao;
	@Resource
	ResourceGrpRelModelMapper resourceGrpRelDao;
	@Resource
	private CusResourceRelModelMapper customerResourceRelDao;
	@Resource
	PermissionRecordModelMapper permissionRecordDao;
	@Resource
	private IUserService userService;
	
	@Async
	@Override
	public void onApplicationEvent(RemoveCusFromCusGrpPermChangeEvent event) {
		CusAndCusGrpRelChangeEventData eData = (CusAndCusGrpRelChangeEventData)event.getSource();
		
		//用户对资源的关系
		PermissionRecordModel permRecordModel = new PermissionRecordModel();
		permRecordModel.setObjectRelation(8); // 用户组删除用户
		permRecordModel.setActionType(0); //0 撤销权限'
		permRecordModel.setCreateDate(new Date());
		permRecordModel.setCustomerId(eData.getCustomerId());
		permRecordModel.setCgroupId(eData.getCusGroupId());
	
		//先找要检查的资源list
		List<Integer> resourceIds = new ArrayList<Integer>();
		
		//获取用户组对应的资源list
		List<CusGrpResourceRelModel> cGrpResRelModels = iCusGrpResourceRelService.queryByCusGroupId(eData.getCusGroupId());
		if(null != cGrpResRelModels && cGrpResRelModels.size()>0){
			for(CusGrpResourceRelModel cusGrpResRelmodel : cGrpResRelModels){
				resourceIds.add(cusGrpResRelmodel.getResourceId());
			}
		}
		
		//获取用户组对应的资源组list
		//用户对资源组的关系
		List<CusGroupResGroupRelModel> cusGrpResGrpRelModels = cusGroupResGroupRelDao.selectByCusGroupId(eData.getCusGroupId());
		if(null != cusGrpResGrpRelModels && cusGrpResGrpRelModels.size()>0){
			List<Integer> resGrpIds = new ArrayList<Integer>();
			for(CusGroupResGroupRelModel cusGrpResGrpRelModel: cusGrpResGrpRelModels){
				resGrpIds.add(cusGrpResGrpRelModel.getRgroupId());
			}
			//获取资源组列表对应的资源id list
			List<Integer> idsTmp = resourceGrpRelDao.selectResIdsByResGroupIdList(resGrpIds);
			if(null != idsTmp && idsTmp.size()>0){
				resourceIds.addAll(idsTmp);
			}
		}
		
		//找到了要检查的资源，循环对每个资源关系处理
		CusResourceRelModel relModel = null;
		for(Integer resId : resourceIds){
			relModel = new CusResourceRelModel();
			relModel.setCustomerId(eData.getCustomerId());
			relModel.setResourceId(resId);
			//查看现有的资源与用户关系
			relModel = customerResourceRelDao.selectByCusIdAndResourceId(relModel);
			
			//如果现在就是没有权限，不做处理
			if(null == relModel || "N".equals(relModel.getEnable()) 
					||(null != relModel.getEndDate() && relModel.getEndDate().before(new Date()))){
				continue;
			}
			
			//如果有权限，查看授权记录
			PermissionRecordModel permRecord = new PermissionRecordModel();
			permRecord.setCustomerId(eData.getCustomerId());
			permRecord.setResourceId(resId);
			List<PermissionRecordModel> permRecordList = permissionRecordDao.selectByCondition(permRecord);
			if(null == permRecordList || permRecordList.size() > 0){
				//如果最后一次授权是取消权限，不做处理
				if(0 == permRecordList.get(0).getActionType()){
					continue;
				}
				
				//最后一次授权是否是当前用户组连带授权带来的
				//如果是
				if(null != permRecordList.get(0).getCgroupId() 
						&& eData.getCusGroupId().intValue() == permRecordList.get(0).getCgroupId().intValue()){
					//如果只有一条授权记录，不做处理；如果有大于1条记录，恢复第二条记录的状态
					if(permRecordList.size() > 1){
						permRecord = permRecordList.get(1);
					}else{
						continue;
					}
					
					if(permRecord.getActionType() == 0 
							|| (null != permRecord.getCgroupId() 
								&& eData.getCusGroupId().intValue() == permRecord.getCgroupId().intValue())){
						//没有权限记录，，或者 授权也是由这个cusGroup带来的，直接删除关系
						
						permRecord.setActionType(0); //取消权限
						permRecord.setCreateDate(new Date());
						permRecord.setCreateUser(userService.getSessionUserId());
						permRecord.setObjectRelation(-5); //用户组移除用户
						permRecord.setRgroupId(null);
						permRecord.setStartDate(null);
						permRecord.setEndDate(null);
						
						iCusResourceRelService.disableResourcePermission(relModel, permRecord);
					}else{
						//恢复上次的授权状态
						relModel.setCreateDate(new Date());
						relModel.setStartDate(permRecord.getStartDate());
						relModel.setEndDate(permRecord.getEndDate());

						permRecord.setActionType(1); //权限
						permRecord.setCreateDate(new Date());
						permRecord.setCreateUser(userService.getSessionUserId());
						permRecord.setObjectRelation(-5); //用户组移除用户造成的上个权限恢复
						permRecord.setRgroupId(null);
						permRecord.setStartDate(null);
						permRecord.setEndDate(null);
						iCusResourceRelService.authorizeResPermission(relModel, permRecord);
					}
				
				//如果不是，不做处理
				}else{
					continue;
				}
			}else{
			//没有权限记录，直接删除关系（连带保存取消授权记录）
				//记录权限更新
				permRecord.setActionType(0);
				permRecord.setObjectRelation(-5);
				permRecord.setCreateUser(userService.getSessionUserId());
				permRecord.setCreateDate(new Date());
				permRecord.setCgroupId(eData.getCusGroupId());
				
				iCusResourceRelService.disableResourcePermission(relModel,permRecord);
			}
		}
	}
}
