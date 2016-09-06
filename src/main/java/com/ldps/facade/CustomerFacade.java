package com.ldps.facade;


import com.ldps.data.CusResourceRelData;
import com.ldps.data.CustomerData;
import com.ldps.data.ResourceData;
import com.ldps.model.CusResourceRelModel;
import com.ldps.model.CustomerResGroupRelModel;

import java.util.Date;
import java.util.List;



public interface CustomerFacade {
	public String verification(String mobile, String mac);
	
	public List<ResourceData> querySharableResource(String cid);

	List<CusResourceRelData> queryResourceRelByShareCustomerId(String customerId);

	public String removeSharedResource(String fromCId, String toCId,
			Integer sourceKeyId);

	public String shareResource(String fromMobile, String toMobile, Integer sourceKeyId, String startDate, String endDate);

	/*
	获取building里用户有权限设备
	 */
	public List<ResourceData> queryPrivateResByBIdAndMobile(Integer buildingId,
			String mobile);

	int jointAuthResPermissionByCusId(Long customerId, Integer resourceId,
			Date startDate, Date endDate, Long createUserId);

	int jointAuthResPermissionByMobile(String mobile, String mac,
			Date startDate, Date endDate, Long createUserId);

	int authResPermissionByCusId(Long customerId, Integer resourceId,
			Date startDate, Date endDate, Long createUserId);

	int authResPermissionByMobile(String mobile, String resourceKey,
			Date startDate, Date endDate, Long createUserId);

	List<CustomerData> queryAllUserListWithPageIndex(Integer startRow,
			Integer pageSize);

	CustomerData searchUserByMobileWithPageIndex(String mobile, Integer pageNo,
			Integer pageSize);

	Integer queryCustomerTotalCount();

	public CustomerData getUserDataByPrimaryId(Long userId);

	public int delUserGroupRelation(Long userId, Integer groupId);

	public int addUserGroupRelation(Long userId, Integer groupId);

	public List<CustomerData> searchByMobileAndNameWithPageIndex(String mobile,
			String userName, Integer offset, Integer limit);

	public Integer queryTotalCountByMobileAndName(String mobile, String userName);

	public List<CustomerData> searchWithBindGrpIdByNameAndMobile(String userGroupId,
			String mobile, String userName, Integer startRow, Integer pageSize);

	Integer queryTotalCountWithBindGrpId(String userGroupId, String mobile,
			String userName);

	public List<CustomerData> searchWithGrpIdFlagByNameAndMobile(
			String userGroupId, String mobile, String userName, Integer offset,
			Integer limit);

	Integer queryTotalCountByMobileAndUserName(
			String mobile, String userName);

	public int disableResourcePermission(Long userId, Integer resourceId);

	//更新\添加用户资源权限
	int authCusResPermission(CusResourceRelModel cusResourceRelModel);

	int jointAuthCusResPermission(CusResourceRelModel cusResourceRelModel);

	/*
	获取building里的公共资源 ，resourceKeys同时返回
	 */
	List<ResourceData> queryPubResWithKeysByBuildingId(Integer buildingId);

	public List<ResourceData> queryPrivateResWithKeysByBIdAndMobile(
			Integer buildingId, String mobile);

	public int jointAuthCusResGrpPermission(
			CustomerResGroupRelModel cusResGrpRelModel);

	public int deleteResGrpPermission(Long userId, Integer resGroupId);

	List<ResourceData>  queryPermissionValidResByMobileAndMac(
			String mobile, String mac);
}
