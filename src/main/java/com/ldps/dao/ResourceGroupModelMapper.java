package com.ldps.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ldps.model.ResourceGroupModel;
import com.ldps.model.ResourceModel;

public interface ResourceGroupModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResourceGroupModel record);

    int insertSelective(ResourceGroupModel record);

    ResourceGroupModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResourceGroupModel record);

    int updateByPrimaryKey(ResourceGroupModel record);
    
    List<ResourceGroupModel> selectByResourceId(Integer id);
    
    List<ResourceGroupModel> selectResouceGroupListByCondition(@Param("resourceGroupModel")ResourceGroupModel resourceGroupModel,@Param("startRow") Integer startRow,
			@Param("pageSize") Integer pageSize);
	int selectCountByCondition(@Param("resourceGroupModel")ResourceGroupModel resourceGroupModel);
}