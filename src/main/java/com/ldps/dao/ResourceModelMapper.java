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
    

	List<ResourceModel> selectValidPubResByBuildingId(Integer buildingId);
	
	List<ResourceModel> selectPubResWithKeysByBuildingId(Integer buildingId);
	
	List<ResourceModel> selectPubResWithKeys();

	List<ResourceModel> selectBasicResByNodeIdList(@Param("nodeIds")List<Integer> nodeIds);
	
	List<ResourceModel> selectResouceListByCondition(@Param("resourceModel")ResourceModel resourceModel,@Param("startRow") Integer startRow,
			@Param("pageSize") Integer pageSize);
	int selectCountByCondition(@Param("resourceModel")ResourceModel resourceModel);
	

	ResourceModel selectWithResKeysByName(@Param("name") String name);
	
	ResourceModel selectWithResKeysById(@Param("resourceId") Integer resourceId);

	ResourceModel selectByCondition(ResourceModel newResModel);

}