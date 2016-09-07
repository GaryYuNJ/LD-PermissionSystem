package com.ldps.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ldps.model.CusGroupResGroupRelModel;
import com.ldps.model.ResourceGroupModel;

public interface CusGroupResGroupRelModelMapper {
    int insert(CusGroupResGroupRelModel record);

    int insertSelective(CusGroupResGroupRelModel record);
    
	List<ResourceGroupModel> selectResGrpListWithSpecUserGrpId(
			@Param("resourceGroupModel")ResourceGroupModel resourceGroupModel, 
    		@Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize);

	int deleteByCondition(CusGroupResGroupRelModel cusGrpResGrpRelModel);
	
	List<CusGroupResGroupRelModel> selectByCusGroupId(@Param("cusGroupId")Integer cusGroupId);
	List<CusGroupResGroupRelModel> selectByResGroupId(@Param("resGroupId")Integer resGroupId);
}