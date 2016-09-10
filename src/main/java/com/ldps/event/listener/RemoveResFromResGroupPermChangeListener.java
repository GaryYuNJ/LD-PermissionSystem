package com.ldps.event.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;  
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.ldps.dao.CusGroupRelModelMapper;
import com.ldps.dao.CusGroupResGroupRelModelMapper;
import com.ldps.dao.CusResourceRelModelMapper;
import com.ldps.dao.CustomerResGroupRelModelMapper;
import com.ldps.dao.PermissionRecordModelMapper;
import com.ldps.data.ResAndResGroupRelChangeEventData;
import com.ldps.event.RemoveResFromResGroupPermChangeEvent;
import com.ldps.model.CusResourceRelModel;
import com.ldps.model.PermissionRecordModel;
import com.ldps.service.ICusGrpResourceRelService;
import com.ldps.service.ICusResourceRelService;


/*
	资源组删除资源带来的用户与资源关系更改 异步 监听
	删除用户来自用户组与资源关系的权限
	
先找到所有要检查的用户与资源关系；
    下面循环处理每个资源：
    	如果当前用户对资源没有权限，不做任何动作，返回；
    	如果有权限：
    		查看权限更新记录表，看看资源与用户最新的记录是否是当前用户组带来的：
    			如果是，恢复为上一个记录的权限关系，如果没有上一个记录，直接删除权限；
    			如果不是，不做任何动作；
*/
@Component
public class RemoveResFromResGroupPermChangeListener implements ApplicationListener<RemoveResFromResGroupPermChangeEvent> {

	@Resource
	ICusResourceRelService iCusResourceRelService;
	@Resource
	CusGroupResGroupRelModelMapper cusGroupResGroupRelDao;
	@Resource
	ICusGrpResourceRelService iCusGrpResourceRelService;
	@Resource
	CustomerResGroupRelModelMapper customerResGroupRelDao;
	@Resource
	CusGroupRelModelMapper cusGroupRelDao;
	@Resource
	private CusResourceRelModelMapper customerResourceRelDao;
	@Resource
	PermissionRecordModelMapper permissionRecordDao;
	
	@Async
	@Override
	public void onApplicationEvent(RemoveResFromResGroupPermChangeEvent event) {
		// TODO Auto-generated method stub
		ResAndResGroupRelChangeEventData eData = (ResAndResGroupRelChangeEventData)event.getSource();
		
		//用户对资源的关系
		PermissionRecordModel permRecordModel = new PermissionRecordModel();
		permRecordModel.setObjectRelation(-6); // 资源组移除资源
		permRecordModel.setActionType(0); //撤销权限
		permRecordModel.setCreateDate(new Date());
		permRecordModel.setResourceId(eData.getResourceId());
		permRecordModel.setRgroupId(eData.getResGroupId());
		
		//先找要检查的用户list
		List<Long> customerIds = new ArrayList<Long>();
		
		//获取资源组关联的用户组Id list
		List<Integer> cusGrpIdList = cusGroupResGroupRelDao.selectCusGrpIdListByResGroupId(eData.getResGroupId());
		List<Long>  cusIdListTemp = null;
		if(null != cusGrpIdList && cusGrpIdList.size()>0){
			//获取用户组List对应的用户列表
			cusIdListTemp = cusGroupRelDao.selectCusIdListByGroupIdList(cusGrpIdList);
			if(null != cusIdListTemp){
				customerIds.addAll(cusIdListTemp);
			}
		}
		
		//获取资源组关联的用户
		cusIdListTemp = customerResGroupRelDao.selectCusIdListByResGroupId(eData.getResGroupId());
		if(null != cusIdListTemp){
			customerIds.addAll(cusIdListTemp);
		}
		
		//循环检查、撤销用户与资源的关系
		//找到了要检查的资源，循环对每个资源关系处理
		CusResourceRelModel relModel = null;
		for(Long customerId : customerIds){
			relModel = new CusResourceRelModel();
			relModel.setCustomerId(customerId);
			relModel.setResourceId(eData.getResourceId());
			//查看现有的资源与用户关系
			relModel = customerResourceRelDao.selectByCusIdAndResourceId(relModel);
			
			//如果现在就是没有权限，不做处理
			if(null == relModel || "N".equals(relModel.getEnable()) 
					||(null != relModel.getEndDate() && relModel.getEndDate().before(new Date()))){
				continue;
			}
			
			//如果有权限，查看授权记录
			PermissionRecordModel permRecord = new PermissionRecordModel();
			permRecord.setCustomerId(customerId);
			permRecord.setResourceId(eData.getResourceId());
			List<PermissionRecordModel> permRecordList = permissionRecordDao.selectByCondition(permRecord);
			if(null == permRecordList || permRecordList.size() > 0){
				//如果最后一次授权是取消权限，不做处理
				if(0 == permRecordList.get(0).getActionType()){
					continue;
				}
				
				//最后一次授权是否是当前用户组连带授权带来的
				//如果是
				if(null != permRecordList.get(0).getRgroupId() && eData.getResGroupId().intValue() == permRecordList.get(0).getRgroupId().intValue()){
					//如果只有一条授权记录，不做处理；如果有大于1条记录，恢复第二条记录的状态（如果是授权操作，并且 不是这个resourceGroup带来的）
					if(permRecordList.size() > 1){
						permRecord = permRecordList.get(1);
					}else{
						continue;
					}
					
					if(permRecord.getActionType() == 0 
							|| (null != permRecord.getRgroupId() 
								&& eData.getResGroupId().intValue() == permRecord.getRgroupId().intValue())){
						//上个权限操作记录为取消， 或者 授权也是由这个resourceGroup带来的，直接删除
						permRecord.setActionType(0); //取消权限
						permRecord.setCreateDate(new Date());
						permRecord.setCreateUser(0L);
						permRecord.setObjectRelation(-6);// 资源组移除资源
						permRecord.setRgroupId(eData.getResGroupId());
						permRecord.setCgroupId(null);
						permRecord.setStartDate(null);
						permRecord.setEndDate(null);
						
						iCusResourceRelService.disableResourcePermission(relModel, permRecord);
					}else{
						//恢复上次的授权状态
						relModel.setCreateDate(new Date());
						relModel.setStartDate(permRecord.getStartDate());
						relModel.setEndDate(permRecord.getEndDate());

						permRecord.setActionType(1); //恢复权限
						permRecord.setCreateDate(new Date());
						permRecord.setCreateUser(0L);
						permRecord.setObjectRelation(-6);// 资源组移除资源造成的上个权限恢复
						permRecord.setRgroupId(eData.getResGroupId());
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
				permRecord.setObjectRelation(-6);// 资源组移除资源
				permRecord.setCreateUser(0L);
				permRecord.setCreateDate(new Date());
				permRecord.setRgroupId(eData.getResGroupId());
				
				iCusResourceRelService.disableResourcePermission(relModel,permRecord);
			}
		}
		
		
	}
}
