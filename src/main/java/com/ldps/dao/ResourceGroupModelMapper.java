package com.ldps.dao;

import java.util.List;

import com.ldps.model.ResourceGroupModel;

public interface ResourceGroupModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResourceGroupModel record);

    int insertSelective(ResourceGroupModel record);

    ResourceGroupModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResourceGroupModel record);

    int updateByPrimaryKey(ResourceGroupModel record);
    
    List<ResourceGroupModel> selectByResourceId(Integer id);
}