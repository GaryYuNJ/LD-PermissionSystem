package com.ldps.dao;

import com.ldps.model.RoleBuilding;

public interface RoleBuildingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleBuilding record);

    int insertSelective(RoleBuilding record);

    RoleBuilding selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleBuilding record);

    int updateByPrimaryKey(RoleBuilding record);
}