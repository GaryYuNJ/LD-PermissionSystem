package com.ldps.service;

import java.util.List;

import com.ldps.model.ResourceModel;

public interface IResourceService {
	
    //返回带有grouplist的resourceModel
	ResourceModel queryWithGroupsByMAC(String mac);

	//根据MAC查找资源(mac不为空时使用)
	ResourceModel queryResourceByMAC(String mac);

	ResourceModel queryModelById(Integer sourceKeyId); 
	 
}
