package com.ldps.dao;

import com.ldps.model.ResourceGrpRelModel;

public interface ResourceGrpRelModelMapper {
    int insert(ResourceGrpRelModel record);

    int insertSelective(ResourceGrpRelModel record);
    
    int deleteByPrimaryKey(ResourceGrpRelModel record);
}