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
}