package com.ldps.service;

import java.util.List;

import com.ldps.model.ResourceGroupModel;
import com.ldps.model.ResourceGrpRelModel;

public interface IResourceGroupService {
	
	List<ResourceGroupModel>  queryBasicResGroupByCondition(ResourceGroupModel model, Integer pageNo, Integer pageSize);
	
	int queryCountByCondition(ResourceGroupModel model);
	
	int addNewGroup(ResourceGroupModel model);
	
	ResourceGroupModel queryReourceGroupModelById(Integer id);
	
	int updateResourceGroup(ResourceGroupModel model);
	
	int addResourceGroupRel(ResourceGrpRelModel model);
	int deleteResourceGroupRel(ResourceGrpRelModel model);
}
