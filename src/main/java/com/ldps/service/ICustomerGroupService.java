package com.ldps.service;

import java.util.List;

import com.ldps.data.CustomerGroupData;
import com.ldps.model.CustomerGroupModel;


public interface ICustomerGroupService {

	public List<CustomerGroupModel> queryAllWithPageIndex(Integer startRow, Integer pageSize);

	public Integer queryCustomerTotalCount();

	CustomerGroupModel getCustomerGroupModelByName(String name);

	List<CustomerGroupModel> queryJoinCusIdWithPageIndex(Integer startRow,
			Integer pageSize, Long customerId);

	public int createNewUserGroup(String userGroupName);

	public int deleteUserGroupById(Integer customerGroupId);

	public CustomerGroupModel showUserGroupDetail(Integer customerGroupId);

	public int updateUserGroup(Integer customerGroupId, String customerGroupName);

	List<CustomerGroupModel> getCustomerGroupByNameLike(String namename,Integer startRow, Integer pageSize);

	public Integer queryCustomerTotalCountByNameLike(String name);

	List<CustomerGroupModel> queryByNameJoinCusIdWithPageIndex(
			Integer startRow, Integer pageSize, String name, Long customerId);

}
