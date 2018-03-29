package com.ldps.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ldps.model.CusGroupRelModel;
import com.ldps.model.CustomerModel;

public interface CusGroupRelModelMapper {
    int insert(CusGroupRelModel record);

    int insertSelective(CusGroupRelModel record);

	int delUserGroupRelation(@Param("customerId") Long customerId, @Param("groupId")Integer groupId);

	/*
	根据 指定的BindGrpId 绑定关系，mobile，name搜索用户列表
	 */
	List<CustomerModel> selectCusModelWithBindGrpId(@Param("userGroupId")String userGroupId,
			@Param("mobile")String mobile, @Param("userName")String userName, 
			@Param("startRow")Integer startRow, @Param("pageSize")Integer pageSize);

	/*
	根据指定的BindGrpId 绑定关系，mobile，name 查用户总数
	 */
	Integer selectCusModelCountWithBindGrpId(@Param("userGroupId")String userGroupId,
			@Param("mobile")String mobile, @Param("userName")String userName);

	/*
	根据 mobile，name搜索用户列表，并加上与指定groupId的依赖关系
	 */
	List<CustomerModel> selectWithGrpIdFlagByNameAndMobile(@Param("userGroupId")String userGroupId,
			@Param("mobile")String mobile, @Param("userName")String userName, 
			@Param("startRow")Integer startRow, @Param("pageSize")Integer pageSize);

	/*
	根据mobile，name 查用户总数
	 */
	Integer selectCusModelCountByMobileAndUserName(@Param("mobile")String mobile, 
			@Param("userName")String userName);
	
	
	List<CusGroupRelModel> selectByGroupId(@Param("userGroupId")Integer userGroupId);
	
	List<Long> selectCusIdListByGroupId(@Param("userGroupId")Integer userGroupId);
	
	List<Long> selectCusIdListByGroupIdList(@Param("userGroupIdList")List<Integer> userGroupIdList);
	
	List<Long> selectCusIdByGroupIdAndCustId(@Param("customerId") Long customerId, @Param("groupId")Integer groupId);
}