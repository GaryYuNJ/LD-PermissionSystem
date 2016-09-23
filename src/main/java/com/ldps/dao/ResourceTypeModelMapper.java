package com.ldps.dao;

import com.ldps.model.ResourceTypeModel;

public interface ResourceTypeModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResourceTypeModel record);

    int insertSelective(ResourceTypeModel record);

    ResourceTypeModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResourceTypeModel record);

    int updateByPrimaryKey(ResourceTypeModel record);
}