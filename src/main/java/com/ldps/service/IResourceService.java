package com.ldps.service;

import java.io.IOException;
import java.util.List;

import com.ldps.data.APIMessage;
import com.ldps.model.ResourceKeyModel;
import com.ldps.model.ResourceModel;

public interface IResourceService {
	
    //返回带有grouplist的resourceModel
	ResourceModel queryWithGroupsByMAC(String mac);

	//根据MAC查找资源(mac不为空时使用)
	ResourceModel queryResourceByMAC(String mac);
	
	//根据MAC查找资源Id(mac不为空时使用)
	Integer queryResourceIdByMAC(String mac);

	ResourceModel queryModelById(Integer sourceId);

	/*
		获取building里的公共资源
	 */
	List<ResourceModel> queryValidPubResByBuildingId(Integer buildingId);

	/*
		获取building里用户有权限设备
	 */
	List<ResourceModel> queryPrivateResByBIdAndCusId(Integer buildingId,
			Long customerId); 
	/*
		根据节点ID获取直接挂载的基础资源
	 */
	List<ResourceModel> queryBasicResByNodeId(Integer nodeId); 
	
	/*
		根据节点ID获取直接挂载的所有资源
	*/
	List<ResourceModel> queryAllResByNodeId(Integer nodeId); 
	
	/*
	根据节点ID list获取挂载的所有基础资源
	 */
	List<ResourceModel> queryBasicResByNodeIdList(List<Integer> nodeIds);

	int createResource(ResourceModel model);

	int updateResource(ResourceModel model); 
	 
	int deleteResource(Integer primaryId);
	
	List<ResourceModel> queryBasicResByCondition(ResourceModel model, Integer pageNo, Integer pageSize);
	int queryCountByCondition(ResourceModel model);

	List<ResourceModel> queryResByConditionWithCusId(
			ResourceModel resourceModel, Integer offset, Integer limit);

	List<ResourceModel> queryResByConditionWithCusGroupId(
			ResourceModel resourceModel, Integer offset, Integer limit);

	List<ResourceModel> queryPubResWithKeysByBuildingId(Integer buildingId);

	List<ResourceModel> queryPriResWithKeysByBIdAndCusId(Integer buildingId,
			Long customerId);
	
	ResourceModel getReourceAndKeyById(Integer sourceId);
	List<ResourceKeyModel> queryKeyByresource(Integer resourceId);
	APIMessage importResFromExcel(String filePath, Integer nodeId) throws IOException;

	List<ResourceModel> queryBasicResByConditionWithGId(ResourceModel model, Integer pageNo, Integer pageSize);

	List<ResourceModel> queryValidResByCIdAndMac(Long customerId,
			Integer resourceId);
}
