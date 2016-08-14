package com.ldps.dao;

import com.ldps.model.NodeModel;

public interface NodeModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NodeModel record);

    int insertSelective(NodeModel record);

    NodeModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NodeModel record);

    int updateByPrimaryKey(NodeModel record);
}