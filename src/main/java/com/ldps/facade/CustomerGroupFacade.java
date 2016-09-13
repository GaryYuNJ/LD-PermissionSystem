package com.ldps.facade;

import java.util.List;

import com.ldps.data.CustomerGroupData;
import com.ldps.model.CusGroupResGroupRelModel;
import com.ldps.model.CusGrpResourceRelModel;
import com.ldps.model.CusResourceRelModel;



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

	int updateUserGroup(Integer customerGroupId, String customerGroupName);

	int disableCusGrpResPermission(Integer cusGrpId, Integer resourceId);

	int authCusGrpResPermission(CusGrpResourceRelModel cusGrpResourceRelModel);

	int jointAuthCusGrpResPermission(
			CusGrpResourceRelModel cusGrpResourceRelModel);

	int jointAuthCusGrpResGrpPermission(
			CusGroupResGroupRelModel cusGrpResGrpRelModel);

	
}
