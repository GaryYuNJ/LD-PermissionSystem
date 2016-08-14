package com.ldps.dao;

import com.ldps.model.CustomerModel;

public interface CustomerModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerModel record);

    int insertSelective(CustomerModel record);

    CustomerModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerModel record);

    int updateByPrimaryKey(CustomerModel record);
}