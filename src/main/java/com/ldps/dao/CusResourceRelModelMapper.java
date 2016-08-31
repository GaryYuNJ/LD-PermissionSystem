package com.ldps.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ldps.model.CusResourceRelModel;
import com.ldps.model.ResourceModel;

public interface CusResourceRelModelMapper {
    int insert(CusResourceRelModel record);

    int insertSelective(CusResourceRelModel record);
    
    CusResourceRelModel selectByCusIdAndResourceId(CusResourceRelModel record);
    
	List<CusResourceRelModel> selectByShareCustomerId(Long createUser);

	int deleteSharedResource(@Param("fromCustomerId")Long fromCustomerId, @Param("toCustomerId")Long toCustomerId, @Param("resourceId")Integer resourceId);

	int updateByConditionSelective(CusResourceRelModel record);
	
	List<ResourceModel> selectResouceListWithSpecUserId(@Param("resourceModel")ResourceModel resourceModel,@Param("startRow") Integer startRow,
			@Param("pageSize") Integer pageSize);

	int disableResourcePermission(CusResourceRelModel crModel);

	int disableBatchResourcePermission(@Param("disableFlag")String disableFlag, @Param("user")Integer user,
			@Param("customerIds") List<Long> customerIds, @Param("resourceId") Integer resourceId);

}