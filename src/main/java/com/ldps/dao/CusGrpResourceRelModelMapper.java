package com.ldps.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ldps.model.CusGrpResourceRelModel;
import com.ldps.model.CusResourceRelModel;
import com.ldps.model.ResourceModel;

public interface CusGrpResourceRelModelMapper {
    int insert(CusGrpResourceRelModel record);

    int insertSelective(CusGrpResourceRelModel record);
    
    CusGrpResourceRelModel selectByGrpIdAndResId(CusGrpResourceRelModel record);
    
    List<CusGrpResourceRelModel> selectByGroupIdListAndResId(@Param("groupIds")List<Integer> groupIds, 
    		@Param("resourceId")Integer resourceId);
    

	List<ResourceModel> selectResouceListWithSpecCusGroupId(@Param("resourceModel")ResourceModel resourceModel,@Param("startRow") Integer startRow,
			@Param("pageSize") Integer pageSize);
	
	  int disableResourcePermission(CusGrpResourceRelModel record);
	  
	  int updateByConditionSelective(CusGrpResourceRelModel record);
}