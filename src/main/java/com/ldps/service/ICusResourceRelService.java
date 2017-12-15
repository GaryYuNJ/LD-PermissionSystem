package com.ldps.service;

import java.util.Date;
import java.util.List;

import com.ldps.model.CusResourceRelModel;
import com.ldps.model.CustomerResGroupRelModel;
import com.ldps.model.PermissionRecordModel;

public interface ICusResourceRelService {

	//用户删除分享给其他人的权限
	int removeSharedResource(Long fromCustomerId, Long toCustomerId,
			Integer sourceKeyId);

	//查询用户分享出去的资源信息
	List<CusResourceRelModel> queryByShareCustomerId(Long customerId);

	//分享给其他人的权限
	int shareResource(Long fromCId, Long toCId, Integer sourceKeyId,
			Date startDate, Date endDate);

	//	//查找用户与资源的关系
	CusResourceRelModel queryModelByCustomerIdAndResId(Long customerId,
			Integer resourceId); 
	
	
	//联合授权-向用户授权指定的资源以及所有上层节点的所有基础资源
	int jointAuthorizeResPermission(Long customerId, Integer resourceId,
			Date startDate, Date endDate, String fromShared, Long createUser,
			PermissionRecordModel perRecordModel);

	int updateByConditionSelective(CusResourceRelModel crModel);

	//向用户授权指定的资源
	int authorizeResPermission(CusResourceRelModel sourceModel,
			PermissionRecordModel perRecordModel);

	int disableBatchResourcePermission(List<Long> customerIds,
			Integer resourceId, PermissionRecordModel permRecordModel);

	int jointAuthorizeResGrpPermission(Long customerId, Integer rgroupId,
			Date startDate, Date endDate, Long createUser,
			PermissionRecordModel permRecordModel);

	int disableResourcePermission(CusResourceRelModel crModel,
			PermissionRecordModel permRecordModel);

	int deleteCusResGrpPermission(CustomerResGroupRelModel crgModel,
			PermissionRecordModel permRecordModel);
	
	List<CusResourceRelModel> querySharableResource(Long customerId,Integer buildingId,Integer floor,Long toCustomerId,Date startDate,Date endDate);

}
