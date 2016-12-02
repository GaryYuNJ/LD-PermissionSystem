package com.ldps.dao;

import java.util.List;

import com.ldps.model.BuildingModel;
import com.ldps.model.RoleBuilding;

public interface RoleBuildingMapper {
    int insert(RoleBuilding record);

    int insertSelective(RoleBuilding record);
    
    int deleteByRoleId(Long roleId);
    
    List<Integer> selectBuildingsByRoleId(Long roleId);
    
    List<BuildingModel> selectBuildingsByRoles(List<Long> roleIds);
    
}