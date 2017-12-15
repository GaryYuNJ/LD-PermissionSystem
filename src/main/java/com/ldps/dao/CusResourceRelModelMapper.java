package com.ldps.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ldps.data.ResourceArea;
import com.ldps.model.CusResourceRelModel;
import com.ldps.model.ResourceModel;

public interface CusResourceRelModelMapper {
    int insert(CusResourceRelModel record);

    int insertSelective(CusResourceRelModel record);
    
    CusResourceRelModel selectByCusIdAndResourceId(CusResourceRelModel record);
    
	List<CusResourceRelModel> selectByShareCustomerId(Long createUser);

	int deleteSharedResource(@Param("fromCustomerId")Long fromCustomerId, @Param("toCustomerId")Long toCustomerId, @Param("resourceId")Integer resourceId);

	int updateByConditionSelective(CusResourceRelModel record);
	
	List<ResourceModel> selectResouceListWithSpecUserId( @Param(value="roleId") Long roleId, @Param("resourceModel")ResourceModel resourceModel,@Param("startRow") Integer startRow,
			@Param("pageSize") Integer pageSize);

	//int disableResourcePermission(CusResourceRelModel crModel);

//	int disableBatchResourcePermission(@Param("disableFlag")String disableFlag, @Param("user")Integer user,
//			@Param("customerIds") List<Long> customerIds, @Param("resourceId") Integer resourceId);
	
    List<ResourceModel>  selectSharableResourceById(Long customerId);
    
	List<ResourceModel> selectPrivateResByBIdAndCusId(@Param("buildingId") Integer buildingId,
			@Param("customerId") Long customerId);
	
	List<ResourceModel>  selectPriResWIthKeysByBIdAndCusId (@Param("buildingId") Integer buildingId,
			@Param("customerId") Long customerId);

	ResourceModel selectValidResByCIdAndMac(@Param("customerId")Long customerId,
			@Param("resourceId")Integer resourceId);
	
	int delteResourcePermission(CusResourceRelModel crModel);
	int deleteByCusIdListAndResId(@Param("customerIds") List<Long> customerIds, @Param("resourceId") Integer resourceId);

	void deleteByCusIdAndResIdList(@Param("customerId")Long customerId, @Param("resourceIds")List<Integer> resourceIds);
	
	List<ResourceArea> selectSharableResBuildingById(Long customerId);
	List<CusResourceRelModel>  selectSharableResourceByIdAndArea(@Param("customerId")Long customerId,@Param("buildingId")Integer buildingId,@Param("floor")Integer floor,@Param("toCustomerId")Long toCustomerId,@Param("startDate")Date startDate,@Param("endDate")Date endDate);
}