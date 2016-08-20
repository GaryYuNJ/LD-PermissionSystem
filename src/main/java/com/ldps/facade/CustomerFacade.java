package com.ldps.facade;


import org.springframework.stereotype.Service;

import com.ldps.data.CusResourceRelData;
import com.ldps.data.ResourceData;

import java.util.List;



public interface CustomerFacade {
	public String verification(String mobile, String mac);
	
	public List<ResourceData> querySharableResource(String cid);

	List<CusResourceRelData> queryResourceRelByShareCustomerId(String customerId);

	public String removeSharedResource(String fromCId, String toCId,
			Integer sourceKeyId);

	public String shareResource(String fromCId, String toCId, Integer sourceKeyId, String startDate, String endDate);

	/*
	获取building里的公共资源
	 */
	public List<ResourceData> queryPubResByBuildingId(Integer buildingId);
	/*
	获取building里用户有权限设备
	 */
	public List<ResourceData> queryPrivateResByBIdAndMobile(Integer buildingId,
			String mobile);
}
