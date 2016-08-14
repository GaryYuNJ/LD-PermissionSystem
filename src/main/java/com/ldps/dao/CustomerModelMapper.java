package com.ldps.dao;

import com.ldps.model.CustomerModel;

public interface CustomerModelMapper {
    int deleteByPrimaryKey(Long id);
    
    int deleteByCID(String cid);

    int insert(CustomerModel record);

    int insertSelective(CustomerModel record);

    CustomerModel selectByPrimaryKey(Long id);
    
    CustomerModel simpleSelectByCID(String cid);

    int updateByPrimaryKeySelective(CustomerModel record);

    int updateByPrimaryKey(CustomerModel record);
    
    int updateByCIDSelective(CustomerModel record);
}