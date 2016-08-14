package com.ldps.dao;

import com.ldps.model.CustomerGroupModel;

public interface CustomerGroupModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerGroupModel record);

    int insertSelective(CustomerGroupModel record);

    CustomerGroupModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerGroupModel record);

    int updateByPrimaryKey(CustomerGroupModel record);
}