package com.ldps.dao;

import com.ldps.model.PermissionRecordModel;

public interface PermissionRecordModelMapper {
    int insert(PermissionRecordModel record);

    int insertSelective(PermissionRecordModel record);
}