package com.ldps.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ldps.model.ResourceGrpRelModel;
import com.ldps.model.ResourceModel;

public interface ResourceGrpRelModelMapper {
    int insert(ResourceGrpRelModel record);

    int insertSelective(ResourceGrpRelModel record);
    
    int deleteByPrimaryKey(ResourceGrpRelModel record);
    
    List <Integer> selectResIdsByGroupId(@Param("rgroupId") Integer rgroupId);
    
    List<ResourceModel> selectResouceListByConditionWithGId(@Param("resourceModel")ResourceModel resourceModel,@Param("startRow") Integer startRow,
			@Param("pageSize") Integer pageSize);
    
	
	Integer selectResouceCountConditionWithGId(@Param("resourceModel")ResourceModel resourceModel);

    
    int selectCountConditionWithGId(@Param("resourceModel")ResourceModel resourceModel);
}
