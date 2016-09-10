package com.ldps.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ldps.model.PermissionRecordModel;

public interface PermissionRecordModelMapper {
    int insert(PermissionRecordModel record);

    int insertSelective(PermissionRecordModel record);
    
    List<PermissionRecordModel> selectByCondition(@Param("permRecordModel") PermissionRecordModel PermissionRecordModel);
}