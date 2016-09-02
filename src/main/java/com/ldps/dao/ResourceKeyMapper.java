package com.ldps.dao;

import java.util.List;

import com.ldps.model.ResourceKeyModel;

public interface ResourceKeyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResourceKeyModel record);

    int insertSelective(ResourceKeyModel record);

    ResourceKeyModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResourceKeyModel record);

    int updateByPrimaryKey(ResourceKeyModel record);
    
    List<ResourceKeyModel> selectByResourceId(Integer id);
}
