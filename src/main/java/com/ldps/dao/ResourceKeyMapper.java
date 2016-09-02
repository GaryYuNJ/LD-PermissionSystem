package com.ldps.dao;

import com.ldps.model.ResourceKey;

public interface ResourceKeyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResourceKey record);

    int insertSelective(ResourceKey record);

    ResourceKey selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResourceKey record);

    int updateByPrimaryKey(ResourceKey record);
}