package com.ldps.dao;

import com.ldps.model.ResourceModel;

public interface ResourceModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResourceModel record);

    int insertSelective(ResourceModel record);

    ResourceModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResourceModel record);

    int updateByPrimaryKey(ResourceModel record);
}