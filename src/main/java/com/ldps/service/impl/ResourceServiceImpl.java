package com.ldps.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ldps.dao.ResourceModelMapper;
import com.ldps.model.ResourceModel;
import com.ldps.service.IResourceService;

@Service("iResourceService")
public class ResourceServiceImpl implements IResourceService {

	@Resource
	private ResourceModelMapper resourceDao;

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
	获取building里的公共资源
	 */
	@Override
	public List<ResourceModel> selectValidPubResByBuildingId(Integer buildingId) {
		return resourceDao.selectValidPubResByBuildingId(buildingId);
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
		return resourceDao.selectBasicResByNodeIdList(nodeIds);
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
		
		return resourceDao.insertSelective(model);
	}
	
	//更新资源
	@Override
	public int updateResource(ResourceModel model) {
		
		return resourceDao.updateByPrimaryKeySelective(model);
	}

	@Override
	public int deleteResource(Integer primaryId) {

		return resourceDao.deleteByPrimaryKey(primaryId);
	}
	
}
