package com.ldps.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ldps.dao.CusResourceRelModelMapper;
import com.ldps.dao.CustomerResGroupRelModelMapper;
import com.ldps.dao.PermissionRecordModelMapper;
import com.ldps.dao.ResourceGrpRelModelMapper;
import com.ldps.model.CusResourceRelModel;
import com.ldps.model.CustomerResGroupRelModel;
import com.ldps.model.PermissionRecordModel;
import com.ldps.model.ResourceModel;
import com.ldps.service.ICusResourceRelService;
import com.ldps.service.INodeService;
import com.ldps.service.IResourceService;

@Service("iCusResourceRelService")
public class CusResourceRelServiceImpl implements ICusResourceRelService {

	@Resource
	private CusResourceRelModelMapper customerResourceRelDao;
	
	@Resource
	private IResourceService iResourceService;
	
	@Resource
	private INodeService nodeService;
	@Resource
	ResourceGrpRelModelMapper resourceGrpRelModelDao;
	@Resource
	CustomerResGroupRelModelMapper cusResGroupRelModelDao;
	@Resource
	PermissionRecordModelMapper permissionRecordModelDao;
	@Override
	public CusResourceRelModel queryModelByCustomerIdAndResId(Long customerId,
			Integer resourceId) {
		CusResourceRelModel cusRRModel = new CusResourceRelModel();
		cusRRModel.setCustomerId(customerId);
		cusRRModel.setResourceId(resourceId);
		// TODO Auto-generated method stub
		return customerResourceRelDao.selectByCusIdAndResourceId(cusRRModel);
	}

	@Override
	public List<CusResourceRelModel> queryByShareCustomerId(
			Long customerId) {
		// TODO Auto-generated method stub
		return  customerResourceRelDao.selectByShareCustomerId(customerId);
	}

	//用户删除分享给其他人的权限
	@Override
	public int removeSharedResource(Long fromCustomerId, Long toCustomerId,
			Integer sourceKeyId) {
		return  customerResourceRelDao.deleteSharedResource(fromCustomerId,fromCustomerId,sourceKeyId);
	}

	//分享给其他人的权限
	@Override
	public int shareResource(Long fromCId, Long toCId, Integer sourceKeyId,
			Date startDate, Date endDate) {
		CusResourceRelModel model = new CusResourceRelModel();
		model.setCustomerId(toCId);
		model.setFromShared("Y");
		model.setResourceId(sourceKeyId);
		model.setEnable("Y");
		model.setCreateDate(new Date());
		model.setCreateUser(fromCId);
		model.setStartDate(startDate);
		model.setEndDate(endDate);
		return  customerResourceRelDao.insertSelective(model);
	}

	//向用户针对一个资源授权
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public int authorizeResPermission(CusResourceRelModel sourceModel, PermissionRecordModel permRecordModel) {
		
		int updateFlag = 0;
		//如果已有权限，要看起止时间，取入参和已有时间中的最大范围时间保留或更新；
		//如果有关联数据没有权限，更新；
		//如果没有数据，插入
		CusResourceRelModel currentModel = this.queryModelByCustomerIdAndResId(sourceModel.getCustomerId(),
				sourceModel.getResourceId());
		if(null == currentModel){
			updateFlag = this.customerResourceRelDao.insertSelective(sourceModel);
		}else{
			
			
			//如果用户已有权限，并且操作来自分享，分享的时间配置不能覆盖已有的最大时间范围
			if("Y".equals(sourceModel.getFromShared()) && "Y".equals(currentModel.getEnable()) ){
				if(null == currentModel.getStartDate() || currentModel.getStartDate().before(sourceModel.getStartDate())){
					sourceModel.setStartDate(currentModel.getStartDate());
					updateFlag = 1;
				}
				if(null == currentModel.getEndDate() ||currentModel.getEndDate().before(sourceModel.getEndDate())){
					sourceModel.setEndDate(currentModel.getEndDate());
					updateFlag = 1;
				}
			}else{
				updateFlag = 1;
			}
			sourceModel.setCreateDate(new Date());
			sourceModel.setEnable("Y");
			if(updateFlag == 1){
				updateFlag = this.customerResourceRelDao.updateByConditionSelective(sourceModel);
			}
		}
		
		//创建授权记录
		try{
			if(updateFlag == 1){
				if(null == permRecordModel){
					permRecordModel = new PermissionRecordModel();
					permRecordModel.setObjectRelation(1); //'授权关系；1 用户与资源；2 用户与资源组；3 用户组与资源 ；4 用户组与资源组；
					permRecordModel.setActionType(1); //
					permRecordModel.setCreateDate(new Date());
				}
				permRecordModel.setCustomerId(sourceModel.getCustomerId());
				permRecordModel.setResourceId(sourceModel.getResourceId());
				//insert
				permissionRecordModelDao.insertSelective(permRecordModel);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return updateFlag;
	}
	
	
	//联合授权-向用户授权指定的资源以及所有上层节点的所有基础资源
	@Override
	public int jointAuthorizeResPermission(Long customerId, Integer resourceId,
			Date startDate, Date endDate, String fromShared, Long createUser, PermissionRecordModel perRecordModel) {
		int flag = 0;
		//通过resourceId查找到直接对应的nodeId
		ResourceModel rModel = iResourceService.queryModelById(resourceId);
		//通过nodeId所有上层节点的nodeIdlist
		List<Integer> nodeIds = nodeService.getAllParentNodeIdsbyNodeId(rModel.getNodeId());
		//List<Integer> nodeIds = nodeService.getParentNodeIdsbyResourceId(resourceId);
		
		//根据nodeIdlist查找到所有要授权的基础资源id，并加上入参 resourceId
		List<ResourceModel> rmodels = iResourceService.queryBasicResByNodeIdList(nodeIds);
		if(null == rmodels){
			rmodels = new ArrayList<ResourceModel>();
			rmodels.add(rModel);
		
		//当rModel不为基础资源时，rmodels里不会包含它，此处做添加
		}else if(rModel.getPermissionAttrId() != 2){
			rmodels.add(rModel);
		}
		
		CusResourceRelModel cResRelModel = new CusResourceRelModel();
		//调用 authorizeResPermission 循环授权
		for(ResourceModel rModel1 : rmodels){
			try{
				cResRelModel.setCreateUser(createUser);
				cResRelModel.setCustomerId(customerId);
				cResRelModel.setEndDate(endDate);
				cResRelModel.setStartDate(startDate);
				cResRelModel.setFromShared(fromShared);
				cResRelModel.setResourceId(rModel1.getId());
				cResRelModel.setCreateDate(new Date());
				cResRelModel.setEnable("Y");
				this.authorizeResPermission(cResRelModel,perRecordModel);
				flag = 1;
			}catch(Exception e){
				flag = -1;
				e.printStackTrace();
			}
		}
		return flag;
	}

	
	//连带授权资源组
	@Override
	public int jointAuthorizeResGrpPermission(Long customerId,
			Integer rgroupId, Date startDate, Date endDate, Long createUser, PermissionRecordModel permRecordModel) {
		int flag = 0;

		//如果来源就是用户与资源组授权关系：
		if(null == permRecordModel){
			//添加、更新customer & resGroup relation
			CustomerResGroupRelModel model = new CustomerResGroupRelModel();
			model.setCreateDate(new Date());
			model.setCreateUser(createUser);
			model.setCustomerId(customerId);
			model.setEndDate(endDate);
			model.setRgroupId(rgroupId);
			model.setStartDate(startDate);
			
			
			CustomerResGroupRelModel modelTem = cusResGroupRelModelDao.selectByCondition(model);
			if(null != modelTem){
				flag = cusResGroupRelModelDao.updateByCondition(model);
			}else{
				flag = cusResGroupRelModelDao.insertSelective(model);
			}
			
			//创建授权记录
			try{
				if(flag == 1 ){
					permRecordModel = new PermissionRecordModel();
					permRecordModel.setObjectRelation(2); //'授权关系；1 用户与资源；2 用户与资源组；3 用户组与资源 ；4 用户组与资源组；
					permRecordModel.setRgroupId(rgroupId);
					permRecordModel.setActionType(1); //
					permRecordModel.setCustomerId(customerId);
					permRecordModel.setCreateUser(createUser);
					permRecordModel.setCreateDate(new Date());
					//insert
					permissionRecordModelDao.insertSelective(permRecordModel);
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		//添加customer & resource relation
		//通过 resGroupId 获得resourceId list
		List <Integer>resourceIds = resourceGrpRelModelDao.selectResIdsByGroupId(rgroupId);
		
		if(null != resourceIds){
			
			//循环连带授权
			for(Integer rId : resourceIds){
				this.jointAuthorizeResPermission(customerId, rId, startDate, 
						endDate, "N", createUser, permRecordModel);
			}
		}
		
		return flag;
	}

	
	@Override
	public int updateByConditionSelective(CusResourceRelModel crModel) {

		return customerResourceRelDao.updateByConditionSelective(crModel);
	}

	@Override
	public int disableResourcePermission(CusResourceRelModel crModel) {
		return customerResourceRelDao.disableResourcePermission(crModel);
	}

	@Override
	public int disableBatchResourcePermission(List<Long> customerIds,
			Integer resourceId) {
		String disableFlag = "N";
		Integer user = 0;
		return customerResourceRelDao.disableBatchResourcePermission(disableFlag,user,customerIds,resourceId);
	}

	@Override
	public int deleteResGrpPermission(CustomerResGroupRelModel crgModel) {
		// TODO Auto-generated method stub
		return cusResGroupRelModelDao.deleteByCondition(crgModel);
	}

}