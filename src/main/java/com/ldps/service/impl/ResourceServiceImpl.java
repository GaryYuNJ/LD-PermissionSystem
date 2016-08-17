package com.ldps.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ldps.dao.ResourceModelMapper;
import com.ldps.model.ResourceModel;
import com.ldps.service.IResourceService;

@Service("iResourceService")
public class ResourceServiceImpl implements IResourceService {

	@Resource
	private ResourceModelMapper resourceDao;

	@Override
	public ResourceModel queryResourceByMAC(String mac) {
		return resourceDao.selectByMac(mac);
	}
	
	@Override
	public ResourceModel queryWithGroupsByMAC(String mac) {
		return resourceDao.selectWithGroupsByMAC(mac);
	}

	@Override
	public ResourceModel queryModelById(Integer sourceKeyId) {
		return resourceDao.selectByPrimaryKey(sourceKeyId);
	}
	
}
