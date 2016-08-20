package com.ldps.dao;

import com.ldps.model.NodeRelModel;

public interface NodeRelModelMapper {
    int insert(NodeRelModel record);

    int insertSelective(NodeRelModel record);
    
    Integer selectParentNodeIdbyResourceId(Integer resourceId);

	Integer selectParentNodeIdbyChildNodeId(Integer idChild);
}