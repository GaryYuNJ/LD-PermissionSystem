package com.ldps.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ldps.dao.CusResourceRelModelMapper;
import com.ldps.model.CusResourceRelModel;
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
	public int authorizeResPermission(Long customerId, Integer resourceId,
			Date startDate, Date endDate, String fromShared, Long createUser) {
		
		//如果已有权限，要看起止时间，取入参和已有时间中的最大范围时间保留或更新；
		//如果有关联数据没有权限，更新；
		//如果没有数据，插入
		CusResourceRelModel cResRelModel = this.queryModelByCustomerIdAndResId(customerId, resourceId);
		if(null == cResRelModel){
			cResRelModel = new CusResourceRelModel();
			cResRelModel.setCreateDate(new Date());
			cResRelModel.setCreateUser(createUser);
			cResRelModel.setCustomerId(customerId);
			cResRelModel.setEnable("Y");
			cResRelModel.setEndDate(endDate);
			cResRelModel.setStartDate(startDate);
			cResRelModel.setFromShared(fromShared);
			cResRelModel.setResourceId(resourceId);
			
			this.customerResourceRelDao.insertSelective(cResRelModel);
		}else{
			int updateFlag = 0;
			cResRelModel.setFromShared(fromShared);
			if("N".equals(cResRelModel.getEnable())){
				cResRelModel.setEnable("Y");
				updateFlag = 1;
			}
			if(null != cResRelModel.getStartDate() && cResRelModel.getStartDate().after(startDate)){
				cResRelModel.setStartDate(startDate);
				updateFlag = 1;
			}
			if(null != cResRelModel.getEndDate() && cResRelModel.getEndDate().before(endDate)){
				cResRelModel.setEndDate(endDate);
				updateFlag = 1;
			}
			
			if(updateFlag == 1){
				cResRelModel.setCreateUser(createUser);
				this.customerResourceRelDao.updateByPrimaryKeySelective(cResRelModel);
			}
		}
		
		return 0;
	}
	
	
	//联合授权-向用户授权指定的资源以及所有上层节点的所有基础资源
	@Override
	public int jointAuthorizeResPermission(Long customerId, Integer resourceId,
			Date startDate, Date EndDate, String fromShared, Long createUser) {
		int flag = 0;
		//通过resourceId查找到直接对应的nodeId
		ResourceModel rModel = iResourceService.queryModelById(resourceId);
		//通过nodeId所有上层节点的nodeIdlist
		List<Integer> nodeIds = nodeService.getAllParentNodeIdsbyNodeId(rModel.getNodeId());
		//List<Integer> nodeIds = nodeService.getParentNodeIdsbyResourceId(resourceId);
		
		//根据nodeIdlist查找到所有要授权的基础资源id，并加上入参 resourceId
		List<ResourceModel> rmodels = iResourceService.queryBasicResByNodeIdList(nodeIds);
		if(!rmodels.contains(rModel)){
			rmodels.add(rModel);
		}
		
		//调用 authorizeResPermission 循环授权
		for(ResourceModel rModel1 : rmodels){
			try{
				this.authorizeResPermission(customerId, rModel1.getId(), startDate, 
						EndDate, fromShared, createUser);
				flag = 1;
			}catch(Exception e){
				flag = -1;
				e.printStackTrace();
			}
		}
		return flag;
	}
}