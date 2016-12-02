package com.ldps.service;

import java.util.List;
import com.ldps.model.BuildingModel;
import com.ldps.model.RoleBuilding;

public interface IRoleBuildingService {
	List<RoleBuilding> queryRoleId(Long roleId);
	int deleteByRoleId(Long roleId);
	List<Integer> queryBuildingIdRoleId(Long roleId);
	int save(RoleBuilding roleBuilding);
	List<BuildingModel> queryBuildingByRoles(List<Long> roleIds);
	List<BuildingModel> queryBuildingByRoleId(Long roleId);
}
