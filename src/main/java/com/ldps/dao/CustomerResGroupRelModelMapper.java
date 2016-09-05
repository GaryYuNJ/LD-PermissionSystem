package com.ldps.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ldps.model.CustomerResGroupRelModel;
import com.ldps.model.ResourceGroupModel;

public interface CustomerResGroupRelModelMapper {
    int insert(CustomerResGroupRelModel record);

    int insertSelective(CustomerResGroupRelModel record);
    
    List<ResourceGroupModel> selectResGrpListWithSpecUserId(@Param("resourceGroupModel")ResourceGroupModel resourceGroupModel, 
    		@Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize);

    CustomerResGroupRelModel selectByCondition(CustomerResGroupRelModel model);

	int updateByCondition(CustomerResGroupRelModel model);

	int deleteByCondition(CustomerResGroupRelModel crgModel);
    
}