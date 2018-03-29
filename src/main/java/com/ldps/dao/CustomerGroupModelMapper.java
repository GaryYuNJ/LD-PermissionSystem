package com.ldps.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ldps.model.CustomerGroupModel;

public interface CustomerGroupModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerGroupModel record);

    int insertSelective(CustomerGroupModel record);

    CustomerGroupModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerGroupModel record);

    int updateByPrimaryKey(CustomerGroupModel record);

	List<CustomerGroupModel> selectAllWithPageIndex(@Param("startRow") Integer startRow,
			@Param("pageSize") Integer pageSize);

	Integer selectTotalCount();

	CustomerGroupModel selectByName();

	CustomerGroupModel selectByName(String name);
	
	List<CustomerGroupModel> selectJoinCustomerIdWithPageIndex(@Param("startRow") Integer startRow,
			@Param("pageSize") Integer pageSize, @Param("customerId") Long customerId);

	CustomerGroupModel selectByNameJoinCusIdWithPageIndex( @Param("name")String name, @Param("customerId") Long customerId);

	List<CustomerGroupModel> selectByNameLike(@Param("name")String name, @Param("startRow") Integer startRow,
			@Param("pageSize") Integer pageSize);

	Integer selectTotalCountByNameLike(@Param("name") String name);
	
}