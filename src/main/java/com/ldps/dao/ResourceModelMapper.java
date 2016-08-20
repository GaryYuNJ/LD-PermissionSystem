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
    
    List<ResourceModel>  selectSharableResourceById(Long customerId);

	List<ResourceModel> selectValidPubResByBuildingId(Integer buildingId);

	List<ResourceModel> queryPrivateResByBIdAndCusId(@Param("buildingId") Integer buildingId,
			@Param("customerId") Long customerId);

	List<ResourceModel> selectBasicResByNodeIdList(@Param("nodeIds")List<Integer> nodeIds);
    
}