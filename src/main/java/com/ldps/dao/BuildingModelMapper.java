package com.ldps.dao;

import com.ldps.model.BuildingModel;

public interface BuildingModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BuildingModel record);

    int insertSelective(BuildingModel record);

    BuildingModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BuildingModel record);

    int updateByPrimaryKey(BuildingModel record);
}