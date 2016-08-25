package com.ldps.dao;

import org.apache.ibatis.annotations.Param;

import com.ldps.model.CusGroupRelModel;

public interface CusGroupRelModelMapper {
    int insert(CusGroupRelModel record);

    int insertSelective(CusGroupRelModel record);

	int delUserGroupRelation(@Param("customerId") Long customerId, @Param("groupId")Integer groupId);
    
}