package com.ldps.facade;

import java.util.List;

import com.ldps.data.CustomerGroupData;



public interface CustomerGroupFacade {

	List<CustomerGroupData> queryAllCusGropWithPageIndex(Integer startRow,
			Integer pageSize);

	Integer queryCusGroupTotalCount();

	CustomerGroupData searchUserGroupByNameWithPageIndex(String name,
			Integer startRow, Integer pageSize);

	List<CustomerGroupData> queryJoinCustomerIdWithPageIndex(Long customerId,
			Integer offset, Integer limit);

	CustomerGroupData searchByNameJoinCusIdWithPageIndex(String name,
			Long customerId, Integer offset, Integer limit);

	int createNewUserGroup(String userGroupName);

	int deleteUserGroupById(Integer customerGroupId);

	CustomerGroupData showUserGroupDetail(Integer customerGroupId);

	
}
