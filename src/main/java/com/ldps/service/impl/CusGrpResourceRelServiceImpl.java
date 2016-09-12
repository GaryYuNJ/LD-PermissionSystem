package com.ldps.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ldps.dao.CusGroupResGroupRelModelMapper;
import com.ldps.dao.CusGrpResourceRelModelMapper;
import com.ldps.dao.PermissionRecordModelMapper;
import com.ldps.model.CusGroupRelModel;
import com.ldps.model.CusGroupResGroupRelModel;
import com.ldps.model.CusGrpResourceRelModel;
import com.ldps.model.CusResourceRelModel;
import com.ldps.model.PermissionRecordModel;
import com.ldps.service.ICusGrpResourceRelService;
import com.ldps.service.ICusResourceRelService;
import com.ldps.service.IUserService;

@Service("iCusGrpResourceRelService")
public class CusGrpResourceRelServiceImpl implements ICusGrpResourceRelService {

	@Resource
	private CusGrpResourceRelModelMapper customerGrpResourceRelDao;
	@Resource
	ICusResourceRelService iCusResourceRelService;
	@Resource
	CustomerGroupRelServiceImpl iCustomerGroupRelService;
	@Resource
	CusGroupResGroupRelModelMapper cusGroupResGroupRelModelDao;
	@Resource
	PermissionRecordModelMapper permissionRecordModelDao;
	@Resource
	private IUserService userService;

	@Override
	public CusGrpResourceRelModel queryModelByCidAndResId(CusGrpResourceRelModel model) {
		// TODO Auto-generated method stub
		return customerGrpResourceRelDao.selectByGrpIdAndResId(model);
	}
	

	@Override
	public  List<CusGrpResourceRelModel> queryByGroupIdListAndResId(List<Integer> groupIds, Integer resourceId) {
		// TODO Auto-generated method stub
		return customerGrpResourceRelDao.selectByGroupIdListAndResId(groupIds,resourceId);
	}

	@Override
	public  List<CusGrpResourceRelModel> queryByCusGroupId(Integer groupId) {
		// TODO Auto-generated method stub
		return customerGrpResourceRelDao.selectByCusGroupId(groupId);
	}

	
	@Override
	public int disableCusGrpResPermission(Integer cusGrpId, Integer resourceId, PermissionRecordModel permRecordModel) {
		
		CusGrpResourceRelModel cmodel = new CusGrpResourceRelModel();
		cmodel.setCgroupId(cusGrpId);
		cmodel.setResourceId(resourceId);
		cmodel.setEnable("N");
		//先更新用户组与资源关系
		int flag = customerGrpResourceRelDao.deleteResourcePermission(cmodel);
		
		
		if(flag == 1){
			if(null == permRecordModel){
				permRecordModel = new PermissionRecordModel();
				permRecordModel.setObjectRelation(-3); //授权关系；1 用户与资源；2 用户与资源组；3 用户组与资源 ；4 用户组与资源组；5 用户组添加用户；6 资源组添加资源；取消权限操作，对应负值
				permRecordModel.setResourceId(resourceId);
				permRecordModel.setActionType(0); //移除权限
				permRecordModel.setCgroupId(cusGrpId);
				permRecordModel.setCreateUser(userService.getSessionUserId());
				permRecordModel.setCreateDate(new Date());
				//记录用户与资源组的权限变化
				//insert
				permissionRecordModelDao.insertSelective(permRecordModel);
			}
			
			//更新用户组里的用户与资源关系(批量更新)
			List<Long> customerIds = new ArrayList<Long> ();
			//获取用户组ids
			List<CusGroupRelModel> cGrpRelModels = new ArrayList<CusGroupRelModel> ();
			//获取用户组ids
			cGrpRelModels = iCustomerGroupRelService.queryByGroupId(cusGrpId);
			
			for(CusGroupRelModel model : cGrpRelModels){
				customerIds.add(model.getCustomerId());
			}
			iCusResourceRelService.disableBatchResourcePermission(customerIds, resourceId, permRecordModel );
		}
		
		return flag;
	}

	//用户组对单个资源授权
	@Override
	public int authCusGrpResPermission(CusGrpResourceRelModel cusGrpResourceRelModel, PermissionRecordModel permRecordModel) {
		//先更新用户组与资源关系
		int flag = customerGrpResourceRelDao.updateByConditionSelective(cusGrpResourceRelModel);
		//更新用户组里的用户与资源关系(批量更新)
		if(flag == 1){
			
			//创建授权记录
			if (permRecordModel == null){
				try{
					permRecordModel = new PermissionRecordModel();
					permRecordModel.setObjectRelation(3); //'授权关系；1 用户与资源；2 用户与资源组；3 用户组与资源 ；4 用户组与资源组；
					permRecordModel.setResourceId(cusGrpResourceRelModel.getResourceId());
					permRecordModel.setActionType(1); //'动作；1 授权；0 撤销权限
					permRecordModel.setCgroupId(cusGrpResourceRelModel.getCgroupId());
					permRecordModel.setCreateUser(Long.parseLong(cusGrpResourceRelModel.getCreateUser().toString()));
					permRecordModel.setCreateDate(new Date());
					permRecordModel.setStartDate(cusGrpResourceRelModel.getStartDate());
					permRecordModel.setEndDate(cusGrpResourceRelModel.getEndDate());
					//insert
					permissionRecordModelDao.insertSelective(permRecordModel);
				}catch(Exception e){
					e.printStackTrace();
				}
					
			}
			
			List<CusGroupRelModel> cGrpRelModels = new ArrayList<CusGroupRelModel> ();
			//获取用户组ids
			cGrpRelModels = iCustomerGroupRelService.queryByGroupId(cusGrpResourceRelModel.getCgroupId());
			
			//循环赋权(是否可以异步实现)
			for(CusGroupRelModel model : cGrpRelModels){
				CusResourceRelModel cResRelmodel = new CusResourceRelModel();
				cResRelmodel.setCustomerId(model.getCustomerId());
				cResRelmodel.setEnable("Y");
				cResRelmodel.setCreateDate(new Date());
				cResRelmodel.setCreateUser(null == cusGrpResourceRelModel.getCreateUser()? null:Long.parseLong(cusGrpResourceRelModel.getCreateUser().toString()));
				cResRelmodel.setResourceId(cusGrpResourceRelModel.getResourceId());
				cResRelmodel.setEndDate(cusGrpResourceRelModel.getEndDate());
				cResRelmodel.setFromShared("N");
				cResRelmodel.setStartDate(cusGrpResourceRelModel.getStartDate());
				iCusResourceRelService.authorizeResPermission(cResRelmodel, permRecordModel);
			}
		}
		return flag;
	}


	//连带授权用户组与资源关系
	@Override
	public int jointAuthCusGrpResPermission(CusGrpResourceRelModel cusGrpResourceRelModel, PermissionRecordModel permRecordModel) {
		//先更新用户组与资源关系
		int flag = 0;
		CusGrpResourceRelModel modelTmp = customerGrpResourceRelDao.selectByGrpIdAndResId(cusGrpResourceRelModel);
		if(null != modelTmp){
			flag = customerGrpResourceRelDao.updateByConditionSelective(cusGrpResourceRelModel);
		}else{
			flag = customerGrpResourceRelDao.insertSelective(cusGrpResourceRelModel);
		}
		
		
		//创建授权记录
		try{
			if(flag == 1 && null == permRecordModel){
				permRecordModel = new PermissionRecordModel();
				permRecordModel.setObjectRelation(3); //'授权关系；1 用户与资源；2 用户与资源组；3 用户组与资源 ；4 用户组与资源组；
				permRecordModel.setResourceId(cusGrpResourceRelModel.getResourceId());
				permRecordModel.setActionType(1); //
				permRecordModel.setCreateUser(Long.parseLong(cusGrpResourceRelModel.getCreateUser().toString()));
				permRecordModel.setCgroupId(cusGrpResourceRelModel.getCgroupId());
				permRecordModel.setCreateDate(new Date());
				permRecordModel.setStartDate(cusGrpResourceRelModel.getStartDate());
				permRecordModel.setEndDate(cusGrpResourceRelModel.getEndDate());
				//insert
				permissionRecordModelDao.insertSelective(permRecordModel);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		//更新用户组里的用户与资源关系(批量更新)
		if(flag == 1){
			List<CusGroupRelModel> cGrpRelModels = new ArrayList<CusGroupRelModel> ();
			//获取用户组ids
			cGrpRelModels = iCustomerGroupRelService.queryByGroupId(cusGrpResourceRelModel.getCgroupId());
			
			//循环赋权(是否可以异步实现)
			for(CusGroupRelModel model : cGrpRelModels){
				iCusResourceRelService.jointAuthorizeResPermission(model.getCustomerId(), cusGrpResourceRelModel.getResourceId(),
						cusGrpResourceRelModel.getStartDate(), cusGrpResourceRelModel.getEndDate(), "N", 
						null == cusGrpResourceRelModel.getCreateUser()? null:Long.parseLong(cusGrpResourceRelModel.getCreateUser().toString()),
								permRecordModel);
			}
		}
		return flag;
	}

	//授权用户组与资源组关系
	@Override
	public int jointAuthCusGrpResGrpPermission(
			CusGroupResGroupRelModel cusGrpResGrpRelModel) {
		//添加customer group & resource group relation
		int flag = cusGroupResGroupRelModelDao.insertSelective(cusGrpResGrpRelModel);
		PermissionRecordModel permRecordModel = null;
		
		if(flag == 1 ){
			//创建授权记录
			try{
				permRecordModel = new PermissionRecordModel();
				permRecordModel.setObjectRelation(4); //'授权关系；1 用户与资源；2 用户与资源组；3 用户组与资源 ；4 用户组与资源组；
				permRecordModel.setRgroupId(cusGrpResGrpRelModel.getRgroupId());
				permRecordModel.setActionType(1); //
				permRecordModel.setCgroupId(cusGrpResGrpRelModel.getCgroupId());
				permRecordModel.setCreateUser(cusGrpResGrpRelModel.getCreateUser());
				permRecordModel.setCreateDate(new Date());
				permRecordModel.setStartDate(cusGrpResGrpRelModel.getStartDate());
				permRecordModel.setEndDate(cusGrpResGrpRelModel.getEndDate());
				//insert
				permissionRecordModelDao.insertSelective(permRecordModel);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			//获取userIdList
			List<CusGroupRelModel> cGroupRelModels = iCustomerGroupRelService.queryByGroupId(cusGrpResGrpRelModel.getRgroupId());
			
			//循环对单个用户、资源组授权
			if(null != cGroupRelModels){
				for(CusGroupRelModel model: cGroupRelModels){
					iCusResourceRelService.jointAuthorizeResGrpPermission(model.getCustomerId(), cusGrpResGrpRelModel.getRgroupId(), 
							cusGrpResGrpRelModel.getStartDate(), cusGrpResGrpRelModel.getEndDate(), cusGrpResGrpRelModel.getCreateUser(),
							permRecordModel);
				}
			}
		}
		
		return flag;
	}

}