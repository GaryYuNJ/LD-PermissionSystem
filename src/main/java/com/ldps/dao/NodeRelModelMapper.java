package com.ldps.dao;

import com.ldps.model.NodeRelModel;

public interface NodeRelModelMapper {
    int insert(NodeRelModel record);

    int insertSelective(NodeRelModel record);
}