package com.ldps.dao;

import com.ldps.model.PermissonRecordModel;

public interface PermissonRecordModelMapper {
    int insert(PermissonRecordModel record);

    int insertSelective(PermissonRecordModel record);
}