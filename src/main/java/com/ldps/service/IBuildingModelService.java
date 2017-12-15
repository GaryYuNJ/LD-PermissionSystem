package com.ldps.service;

import java.util.List;
import com.ldps.model.BuildingModel;

public interface IBuildingModelService {

	List<BuildingModel> queryAll();
	
	BuildingModel queryBuilding(Integer id);
	
	int save(BuildingModel buildingModel);

	int delete(Integer id);
	
	List<BuildingModel> queryBuildingWithPageIndex(String buildingName, Integer pageNo, Integer pageSize);
	int queryCountByCondition(String buildingName);
}
