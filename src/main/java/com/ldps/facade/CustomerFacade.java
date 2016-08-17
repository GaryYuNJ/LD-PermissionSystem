package com.ldps.facade;


import org.springframework.stereotype.Service;

import com.ldps.data.CusResourceRelData;
import com.ldps.data.ResourceData;

import java.util.List;



public interface CustomerFacade {
	public String verification(String cid, String mac);
	
	public List<ResourceData> querySharableResource(String cid);

	List<CusResourceRelData> queryResourceRelByShareCustomerId(String customerId);

	public String removeSharedResource(String fromCId, String toCId,
			Integer sourceKeyId);

	public String shareResource(String fromCId, String toCId, Integer sourceKeyId, String startDate, String endDate);
}
