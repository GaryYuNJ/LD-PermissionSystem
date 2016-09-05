package com.ldps.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ldps.model.ResourceGrpRelModel;

public interface ResourceGrpRelModelMapper {
    int insert(ResourceGrpRelModel record);

    int insertSelective(ResourceGrpRelModel record);
    
    int deleteByPrimaryKey(ResourceGrpRelModel record);
    
    List <Integer> selectResIdsByGroupId(@Param("rgroupId") Integer rgroupId);
}