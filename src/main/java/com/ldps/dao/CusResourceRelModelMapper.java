package com.ldps.dao;

import java.util.List;

import com.ldps.model.CusResourceRelModel;

public interface CusResourceRelModelMapper {
    int insert(CusResourceRelModel record);

    int insertSelective(CusResourceRelModel record);
    
    CusResourceRelModel selectByCidAndResourceId(CusResourceRelModel record);
    
	List<CusResourceRelModel> selectByShareCustomerId(String sharedUser);
}