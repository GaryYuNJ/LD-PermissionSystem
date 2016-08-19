package com.ldps.service;

import java.util.Date;
import java.util.List;

import com.ldps.model.CusResourceRelModel;

public interface ICusResourceRelService {

	//查找用户与资源的关系
	CusResourceRelModel queryModelByCidAndResId(CusResourceRelModel model); 

	//用户删除分享给其他人的权限
	int removeSharedResource(Long fromCustomerId, Long toCustomerId,
			Integer sourceKeyId);

	//查询用户分享出去的资源信息
	List<CusResourceRelModel> queryByShareCustomerId(Long customerId);

	//分享给其他人的权限
	int shareResource(Long fromCId, Long toCId, Integer sourceKeyId,
			Date startDate, Date endDate); 
}
