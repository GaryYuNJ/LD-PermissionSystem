package com.ldps.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ldps.model.CusResourceRelModel;

public interface CusResourceRelModelMapper {
    int insert(CusResourceRelModel record);

    int insertSelective(CusResourceRelModel record);
    
    CusResourceRelModel selectByCidAndResourceId(CusResourceRelModel record);
    
	List<CusResourceRelModel> selectByShareCustomerId(String sharedUser);

	int deleteSharedResource(@Param("fromCId")String fromCId, @Param("toCId")String toCId, @Param("resourceId")Integer resourceId);

}