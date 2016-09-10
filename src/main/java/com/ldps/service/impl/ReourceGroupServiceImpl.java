package com.ldps.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.ldps.dao.CusGroupRelModelMapper;
import com.ldps.dao.CusGroupResGroupRelModelMapper;
import com.ldps.dao.CustomerResGroupRelModelMapper;
import com.ldps.dao.PermissionRecordModelMapper;
import com.ldps.dao.ResourceGroupModelMapper;
import com.ldps.dao.ResourceGrpRelModelMapper;
import com.ldps.data.ResAndResGroupRelChangeEventData;
import com.ldps.event.AddResToResGroupPermChangeEvent;
import com.ldps.event.RemoveResFromResGroupPermChangeEvent;
import com.ldps.model.CusGroupRelModel;
import com.ldps.model.CusGroupResGroupRelModel;
import com.ldps.model.PermissionRecordModel;
import com.ldps.model.ResourceGroupModel;
import com.ldps.model.ResourceGrpRelModel;
import com.ldps.service.ICusGrpResourceRelService;
import com.ldps.service.ICusResourceRelService;
import com.ldps.service.IResourceGroupService;
@Service("iResourceGroupService")
public class ReourceGroupServiceImpl implements IResourceGroupService {

	@Resource
	private ResourceGroupModelMapper resourceGroupModelDao;
	@Resource
	private ResourceGrpRelModelMapper resourceGrpRelModelDao;
	@Resource
	private CustomerResGroupRelModelMapper cusResGroupRelModelMapper;
	@Resource
	private CusGroupResGroupRelModelMapper cusGroupResGroupRelModelDao;
	@Autowired  
	private ApplicationContext applicationContext;  
	@Resource
	private PermissionRecordModelMapper permissionRecordModelDao;
	@Resource
	private CusGroupRelModelMapper cusGroupRelModelDao;
	@Resource
	private ICusGrpResourceRelService iCusGrpResourceRelService;
	@Resource
	private ICusResourceRelService iCusResourceRelService;
	
	@Override
	public List<ResourceGroupModel> queryBasicResGroupByCondition(
			ResourceGroupModel model, Integer pageNo, Integer pageSize) {
		return resourceGroupModelDao.selectResouceGroupListByCondition(model, pageNo, pageSize);
	}

	@Override
	public int queryCountByCondition(ResourceGroupModel model) {
		return resourceGroupModelDao.selectCountByCondition(model);
	}

	@Override
	public int addNewGroup(ResourceGroupModel model) {
		return resourceGroupModelDao.insertSelective(model);
	}

	@Override
	public ResourceGroupModel queryReourceGroupModelById(Integer id) {
		return resourceGroupModelDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateResourceGroup(ResourceGroupModel model) {
		return resourceGroupModelDao.updateByPrimaryKeySelective(model);
	}

	@Override
	public int addResourceGroupRel(ResourceGrpRelModel model) {
		int flag =  resourceGrpRelModelDao.insertSelective(model);
		
		//资源组的权限关系也要复制给资源
		//构造异步 event data
		ResAndResGroupRelChangeEventData eData = new ResAndResGroupRelChangeEventData();
		eData.setResGroupId(model.getRgroupId());
		eData.setResourceId(model.getResourceId());
		applicationContext.publishEvent(new AddResToResGroupPermChangeEvent(eData));
		
		return flag;
	}

	@Override
	public int deleteResourceGroupRel(ResourceGrpRelModel model) {
		// TODO Auto-generated method stub
		int flag = resourceGrpRelModelDao.deleteByPrimaryKey(model);
		
		//资源移除关联的权限更新
		//用户组删除用户带来的权限更新
		//构造异步 event 
		ResAndResGroupRelChangeEventData eData = new ResAndResGroupRelChangeEventData();
		eData.setResGroupId(model.getRgroupId());
		eData.setResourceId(model.getResourceId());
		applicationContext.publishEvent(new RemoveResFromResGroupPermChangeEvent(eData));
				
		return flag;
	}

	@Override
	public List<ResourceGroupModel> queryBasicResGroupWithCusId(
			ResourceGroupModel resourceGroupModel, Integer offset, Integer limit) {
		// TODO Auto-generated method stub
		return cusResGroupRelModelMapper.selectResGrpListWithSpecUserId(resourceGroupModel, offset, limit);
	}

	@Override
	public List<ResourceGroupModel> queryResGroupWithCusGrpId(
			ResourceGroupModel resourceGroupModel, Integer offset, Integer limit) {
		return cusGroupResGroupRelModelDao.selectResGrpListWithSpecUserGrpId(resourceGroupModel, offset, limit);
	}

	@Override
	public int deleteCusGrpResGrpPermission(Integer userGrpId, Integer resGroupId) {
		// TODO Auto-generated method stub
		CusGroupResGroupRelModel model = new CusGroupResGroupRelModel();
		model.setCgroupId(userGrpId);
		model.setRgroupId(resGroupId);
		
		int flag = cusGroupResGroupRelModelDao.deleteByCondition(model);
		
		if(flag == 1){
			PermissionRecordModel permRecordModel = new PermissionRecordModel();
			permRecordModel.setObjectRelation(-4); //授权关系；1 用户与资源；2 用户与资源组；3 用户组与资源 ；4 用户组与资源组；5 用户组添加用户；6 资源组添加资源；取消权限、相反操作，对应负值
			permRecordModel.setRgroupId(resGroupId);
			permRecordModel.setActionType(0); //移除权限
			permRecordModel.setCgroupId(userGrpId);
			permRecordModel.setCreateUser(0L);
			permRecordModel.setCreateDate(new Date());
			//记录用户组与资源组的权限变化
			//insert
			permissionRecordModelDao.insertSelective(permRecordModel);
			
			//撤销用户与资源的关.
			//获得资源列表
			List<Integer> resourceIds = resourceGrpRelModelDao.selectResIdsByGroupId(resGroupId);
			if(null != resourceIds && resourceIds.size()>0){
				//获得用户Id列表
				List<Long> customerIds = cusGroupRelModelDao.selectCusIdListByGroupId(userGrpId);
				if(null != customerIds && customerIds.size()>0){
					for(Integer resourceId : resourceIds){
						
						iCusResourceRelService.disableBatchResourcePermission(customerIds, resourceId, permRecordModel );
//						
//						//批量删除资源与用户组的关系
//						iCusGrpResourceRelService.disableCusGrpResPermission(userGrpId, resourceId, 
//								permRecordModel);
						
					}
				}
			}
		}
		
		return flag;
	}

}
