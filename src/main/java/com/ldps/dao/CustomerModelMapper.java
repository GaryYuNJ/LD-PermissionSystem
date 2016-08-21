package com.ldps.dao;

import com.ldps.model.CustomerModel;

public interface CustomerModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerModel record);

    int insertSelective(CustomerModel record);

    CustomerModel selectByPrimaryKey(Long id);
    
    CustomerModel simpleSelectByCID(String cid);
    
    CustomerModel simpleSelectWithGroupsByCID(String cid);
    
    CustomerModel simpleSelectWithGroupsById(Long customerId);
    
    int updateByPrimaryKeySelective(CustomerModel record);

    int updateByPrimaryKey(CustomerModel record);
    
    int deleteByCID(String cid);
    
    int updateByCIDSelective(CustomerModel record);

	Long queryIdByMobile(String mobile);

	CustomerModel simpleSelectByMobile(String mobile);  

}