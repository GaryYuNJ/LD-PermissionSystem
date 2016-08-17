package com.ldps.service;

import java.util.List;

import com.ldps.model.CusResourceRelModel;

public interface ICusResourceRelService {

	//查找用户与资源的关系
	CusResourceRelModel queryModelByCidAndResId(CusResourceRelModel model); 

	//查询用户分享出去的资源信息
	List<CusResourceRelModel> queryByShareCustomerId(String customerId); 
}
