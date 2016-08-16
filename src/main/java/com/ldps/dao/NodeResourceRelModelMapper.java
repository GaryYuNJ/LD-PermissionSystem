package com.ldps.dao;

import com.ldps.model.NodeResourceRelModel;

public interface NodeResourceRelModelMapper {
    int insert(NodeResourceRelModel record);

    int insertSelective(NodeResourceRelModel record);
}