package com.ldps.facade;

import com.ldps.model.ResourceModel;



public interface ResourceFacade {
	
	public int createNewResource(ResourceModel model);
	
	public int updateResource(ResourceModel model);
	
	public int deleteResource(Integer primaryId);
}
