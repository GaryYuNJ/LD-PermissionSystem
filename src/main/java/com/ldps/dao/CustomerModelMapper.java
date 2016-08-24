package com.ldps.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
 
    CustomerModel selectIdByMobile(String mobile);

	CustomerModel simpleSelectByMobile(String mobile);  
	
	List<CustomerModel> selectAllWithPageIndex(@Param("startRow") Integer startRow,
			@Param("pageSize") Integer pageSize);
	
	Integer selectTotalCount();
	
}