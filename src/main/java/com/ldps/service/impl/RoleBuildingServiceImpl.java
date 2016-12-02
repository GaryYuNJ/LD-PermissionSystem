package com.ldps.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ldps.dao.RoleBuildingMapper;
import com.ldps.model.BuildingModel;
import com.ldps.model.RoleBuilding;
import com.ldps.service.IRoleBuildingService;

@Service("iroleBuildingService")
public class RoleBuildingServiceImpl implements IRoleBuildingService{

	@Resource
	private RoleBuildingMapper roleBuildingDao;
	
	@Override
	public List<RoleBuilding> queryRoleId(Long roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteByRoleId(Long roleId) {
		return roleBuildingDao.deleteByRoleId(roleId);
	}

	@Override
	public List<Integer> queryBuildingIdRoleId(Long roleId) {
		return roleBuildingDao.selectBuildingIdByRoleId(roleId);
	}

	@Override
	public int save(RoleBuilding roleBuilding) {
		return roleBuildingDao.insert(roleBuilding);
	}

	@Override
	public List<BuildingModel> queryBuildingByRoles(List<Long> roleIds) {
		return roleBuildingDao.selectBuildingsByRoles(roleIds);
	}

	@Override
	public List<BuildingModel> queryBuildingByRoleId(Long roleId) {
		return roleBuildingDao.selectBuildingsByRoleId(roleId);
	}
}
