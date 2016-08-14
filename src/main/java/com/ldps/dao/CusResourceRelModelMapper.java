package com.ldps.dao;

import com.ldps.model.CusResourceRelModel;

public interface CusResourceRelModelMapper {
    int insert(CusResourceRelModel record);

    int insertSelective(CusResourceRelModel record);
}