package com.ldps.dao;

import com.ldps.model.CusGroupRelModel;

public interface CusGroupRelModelMapper {
    int insert(CusGroupRelModel record);

    int insertSelective(CusGroupRelModel record);
}