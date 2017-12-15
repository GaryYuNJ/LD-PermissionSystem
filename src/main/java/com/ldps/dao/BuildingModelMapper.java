package com.ldps.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.ldps.model.BuildingModel;

public interface BuildingModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BuildingModel record);

    int insertSelective(BuildingModel record);

    BuildingModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BuildingModel record);

    int updateByPrimaryKey(BuildingModel record);
    
    List<BuildingModel> selectAll();
    
    List<BuildingModel> selectBuildingWithCondition(@Param("buildingName")String buildingName,@Param("startRow") Integer startRow,
			@Param("pageSize") Integer pageSize);
	
	int selectCountWithCondition(@Param("buildingName")String buildingName);
}