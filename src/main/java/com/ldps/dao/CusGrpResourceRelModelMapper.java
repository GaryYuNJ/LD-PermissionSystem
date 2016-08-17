package com.ldps.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ldps.model.CusGrpResourceRelModel;

public interface CusGrpResourceRelModelMapper {
    int insert(CusGrpResourceRelModel record);

    int insertSelective(CusGrpResourceRelModel record);
    
    CusGrpResourceRelModel selectByGrpIdAndResId(CusGrpResourceRelModel record);
    
    List<CusGrpResourceRelModel> selectByGroupIdListAndResId(@Param("groupIds")List<Integer> groupIds, 
    		@Param("resourceId")Integer resourceId);
}