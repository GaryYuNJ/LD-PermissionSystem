package com.ldps.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ldps.model.ResourceModel;

public interface ResourceModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResourceModel record);

    int insertSelective(ResourceModel record);

    ResourceModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResourceModel record);

    int updateByPrimaryKey(ResourceModel record);
    
    ResourceModel selectByMac(String mac);
    
    ResourceModel  selectWithGroupsByMAC(String mac);
    
    ResourceModel selectIdByMac(String mac);
    
    List<ResourceModel>  selectSharableResourceById(Long customerId);

	List<ResourceModel> selectValidPubResByBuildingId(Integer buildingId);
	
	List<ResourceModel> selectPubResWithKeysByBuildingId(Integer buildingId);

	List<ResourceModel> selectPrivateResByBIdAndCusId(@Param("buildingId") Integer buildingId,
			@Param("customerId") Long customerId);

	List<ResourceModel> selectBasicResByNodeIdList(@Param("nodeIds")List<Integer> nodeIds);
	
	List<ResourceModel> selectResouceListByCondition(@Param("resourceModel")ResourceModel resourceModel,@Param("startRow") Integer startRow,
			@Param("pageSize") Integer pageSize);
	int selectCountByCondition(@Param("resourceModel")ResourceModel resourceModel);
	
	List<ResourceModel>  selectPriResWIthKeysByBIdAndCusId (@Param("buildingId") Integer buildingId,
			@Param("customerId") Long customerId);

	ResourceModel selectWithResKeysByName(@Param("name") String name);
	
	ResourceModel selectWithResKeysById(@Param("resourceId") Integer resourceId);

	ResourceModel selectByCondition(ResourceModel newResModel);
	List<ResourceModel> selectResouceListByConditionWithGId(@Param("resourceModel")ResourceModel resourceModel,@Param("startRow") Integer startRow,
			@Param("pageSize") Integer pageSize);
	List<ResourceModel> selectCountConditionWithGId(@Param("resourceModel")ResourceModel resourceModel,@Param("startRow") Integer startRow,
			@Param("pageSize") Integer pageSize);

	ResourceModel selectValidResByCIdAndMac(@Param("customerId")Long customerId,
			@Param("resourceId")Integer resourceId);
}