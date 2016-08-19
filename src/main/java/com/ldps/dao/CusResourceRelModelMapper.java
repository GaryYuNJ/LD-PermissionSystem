package com.ldps.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ldps.model.CusResourceRelModel;

public interface CusResourceRelModelMapper {
    int insert(CusResourceRelModel record);

    int insertSelective(CusResourceRelModel record);
    
    CusResourceRelModel selectByCusIdAndResourceId(CusResourceRelModel record);
    
	List<CusResourceRelModel> selectByShareCustomerId(Long createUser);

	int deleteSharedResource(@Param("fromCustomerId")Long fromCustomerId, @Param("toCustomerId")Long toCustomerId, @Param("resourceId")Integer resourceId);

}