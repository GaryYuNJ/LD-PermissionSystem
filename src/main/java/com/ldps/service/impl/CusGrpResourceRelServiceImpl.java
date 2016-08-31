package com.ldps.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ldps.dao.CusGrpResourceRelModelMapper;
import com.ldps.model.CusGroupRelModel;
import com.ldps.model.CusGrpResourceRelModel;
import com.ldps.model.CusResourceRelModel;
import com.ldps.service.ICusGrpResourceRelService;
import com.ldps.service.ICusResourceRelService;

@Service("iCusGrpResourceRelService")
public class CusGrpResourceRelServiceImpl implements ICusGrpResourceRelService {

	@Resource
	private CusGrpResourceRelModelMapper customerGrpResourceRelDao;
	@Resource
	ICusResourceRelService iCusResourceRelService;
	@Resource
	CustomerGroupRelServiceImpl iCustomerGroupRelService;

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
	public int disableCusGrpResPermission(Integer cusGrpId, Integer resourceId) {
		
		CusGrpResourceRelModel cmodel = new CusGrpResourceRelModel();
		cmodel.setCgroupId(cusGrpId);
		cmodel.setResourceId(resourceId);
		cmodel.setEnable("Y");
		//先更新用户组与资源关系
		int flag = customerGrpResourceRelDao.disableResourcePermission(cmodel);
		//更新用户组里的用户与资源关系(批量更新)
		if(flag == 1){
			List<Long> customerIds = new ArrayList<Long> ();
			//获取用户组ids
			List<CusGroupRelModel> cGrpRelModels = new ArrayList<CusGroupRelModel> ();
			//获取用户组ids
			cGrpRelModels = iCustomerGroupRelService.queryByGroupId(cusGrpId);
			
			for(CusGroupRelModel model : cGrpRelModels){
				customerIds.add(model.getCustomerId());
			}
			iCusResourceRelService.disableBatchResourcePermission(customerIds, resourceId );
		}
		
		return flag;
	}

	//单个用户组对资源授权
	@Override
	public int authCusGrpResPermission(CusGrpResourceRelModel cusGrpResourceRelModel) {
		//先更新用户组与资源关系
		int flag = customerGrpResourceRelDao.updateByConditionSelective(cusGrpResourceRelModel);
		//更新用户组里的用户与资源关系(批量更新)
		if(flag == 1){
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
				iCusResourceRelService.authorizeResPermission(cResRelmodel);
			}
		}
		return flag;
	}


	@Override
	public int jointAuthCusGrpResPermission(
			CusGrpResourceRelModel cusGrpResourceRelModel) {
		//先更新用户组与资源关系
				int flag = customerGrpResourceRelDao.updateByConditionSelective(cusGrpResourceRelModel);
				//更新用户组里的用户与资源关系(批量更新)
				if(flag == 1){
					List<CusGroupRelModel> cGrpRelModels = new ArrayList<CusGroupRelModel> ();
					//获取用户组ids
					cGrpRelModels = iCustomerGroupRelService.queryByGroupId(cusGrpResourceRelModel.getCgroupId());
					
					//循环赋权(是否可以异步实现)
					for(CusGroupRelModel model : cGrpRelModels){
						iCusResourceRelService.jointAuthorizeResPermission(model.getCustomerId(), cusGrpResourceRelModel.getResourceId(),
								cusGrpResourceRelModel.getStartDate(), cusGrpResourceRelModel.getEndDate(), "N", 
								null == cusGrpResourceRelModel.getCreateUser()? null:Long.parseLong(cusGrpResourceRelModel.getCreateUser().toString()));
					}
				}
				return flag;
	}

}