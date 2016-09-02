package com.ldps.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ldps.dao.CusGrpResourceRelModelMapper;
import com.ldps.dao.CusResourceRelModelMapper;
import com.ldps.dao.ResourceKeyMapper;
import com.ldps.dao.ResourceModelMapper;
import com.ldps.model.ResourceKeyModel;
import com.ldps.model.ResourceModel;
import com.ldps.service.IResourceService;

@Service("iResourceService")
public class ResourceServiceImpl implements IResourceService {

	@Resource
	private ResourceModelMapper resourceDao;
	@Resource
	private CusResourceRelModelMapper cusResourceRelDao;
	@Resource
	private CusGrpResourceRelModelMapper cusGrpResRelDao;
	@Resource
	private ResourceKeyMapper resourceKeyDao;

	@Override
	public ResourceModel queryResourceByMAC(String mac) {
		return resourceDao.selectByMac(mac);
	}
	
	@Override
	public ResourceModel queryWithGroupsByMAC(String mac) {
		return resourceDao.selectWithGroupsByMAC(mac);
	}

	@Override
	public ResourceModel queryModelById(Integer sourceId) {
		return resourceDao.selectByPrimaryKey(sourceId);
	}


	/*
	获取building里的公共资源基本信息
	 */
	@Override
	public List<ResourceModel> selectValidPubResByBuildingId(Integer buildingId) {
		return resourceDao.selectValidPubResByBuildingId(buildingId);
	}

	/*
	获取building里的公共资源，resourceKeys同时返回
	 */
	@Override
	public List<ResourceModel> selectPubResWithKeysByBuildingId(Integer buildingId) {
		return resourceDao.selectPubResWithKeysByBuildingId(buildingId);
	}
	
	/*
	获取building里用户有权限设备
	 */
	@Override
	public List<ResourceModel> queryPrivateResByBIdAndCusId(
			Integer buildingId, Long customerId) {
		return resourceDao.selectPrivateResByBIdAndCusId(buildingId,customerId);
	}

	/*
		根据节点ID获取直接挂载的基础资源
	 */
	@Override
	public List<ResourceModel> queryBasicResByNodeId(Integer nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
		根据节点ID获取直接挂载的所有资源
	 */
	@Override
	public List<ResourceModel> queryAllResByNodeId(Integer nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	根据节点ID list获取挂载的所有基础资源
	 */
	@Override
	public List<ResourceModel> queryBasicResByNodeIdList(List<Integer> nodeIds) {
		if(null != nodeIds && nodeIds.size() > 0){
			return resourceDao.selectBasicResByNodeIdList(nodeIds);
		}else{
			return null;
		}
	}

	@Override
	public Integer queryResourceIdByMAC(String mac) {
		// TODO Auto-generated method stub
		ResourceModel model = resourceDao.selectIdByMac(mac);
		
		if(null == model){
			return null;
		}else{
			return model.getId();
		}
	}
	
	//新建资源
	@Override
	public int createResource(ResourceModel model) {
		if(resourceDao.insertSelective(model)>0){
			for(ResourceKeyModel resourceKey:model.getResourceKeys()){
				resourceKey.setResourceId(model.getId());
				resourceKeyDao.insertSelective(resourceKey);
			}
			return 1;
		}
		return 0;
	}
	
	//更新资源
	@Override
	public int updateResource(ResourceModel model) {
		model.setCreateDate(new Date());
		return resourceDao.updateByPrimaryKeySelective(model);
	}

	@Override
	public int deleteResource(Integer primaryId) {

		return resourceDao.deleteByPrimaryKey(primaryId);
	}

	@Override
	public List<ResourceModel> queryBasicResByCondition(ResourceModel model, Integer pageNo, Integer pageSize) {
		if(model!=null){
			if(StringUtils.isEmpty(model.getName()))
				model.setName(null);
		} 
		return resourceDao.selectResouceListByCondition(model, pageNo, pageSize);
		//这个我们讨论下
		/*
		if(null == model || null == model.getSpecificUserId()){
			return resourceDao.selectResouceListByCondition(model, pageNo, pageSize);
		}else{
			return cusResourceRelDao.selectResouceListWithSpecUserId(model, pageNo, pageSize);
		}*/
		
	}

	@Override
	public List<ResourceModel> queryResByConditionWithCusId(ResourceModel model, Integer pageNo, Integer pageSize) {
		if(model!=null){
			if(StringUtils.isEmpty(model.getName()))
				model.setName(null);
		} 
		
		if(null == model || null == model.getSpecificUserId()){
			return resourceDao.selectResouceListByCondition(model, pageNo, pageSize);
		}else{
			return cusResourceRelDao.selectResouceListWithSpecUserId(model, pageNo, pageSize);
		}
	}
	
	@Override
	public List<ResourceModel> queryResByConditionWithCusGroupId(ResourceModel model, Integer pageNo, Integer pageSize) {
		if(model!=null){
			if(StringUtils.isEmpty(model.getName()))
				model.setName(null);
		} 
		
		if(null == model || null == model.getSpecificCusGroupId()){
			return resourceDao.selectResouceListByCondition(model, pageNo, pageSize);
		}else{
			return cusGrpResRelDao.selectResouceListWithSpecCusGroupId(model, pageNo, pageSize);
		}
	}

	@Override
	public int queryCountByCondition(ResourceModel model) {
		return resourceDao.selectCountByCondition(model);
	}

	@Override
	public List<ResourceModel> queryPriResWithKeysByBIdAndCusId(
			Integer buildingId, Long customerId) {
		return resourceDao.selectPriResWIthKeysByBIdAndCusId(buildingId, customerId);
	}
}
