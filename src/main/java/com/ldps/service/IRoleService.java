package com.ldps.service;

import java.util.List;

import com.ldps.model.Role;

public interface IRoleService {
	List<Role> queryRoleWithPageIndexWithUser(String roleName,Long userId, Integer pageNo, Integer pageSize);
	int queryCountByConditionWithUser(String roleNameLong ,Long userId);
	
	List<Role> queryRoleWithPageIndex(String roleName, Integer pageNo, Integer pageSize);
	int queryCountByCondition(String roleName);
	Long saveOrUpdate(Role role);
	int deleteById(Long id);
	List<Role> getRoleListByUserId(Long userId);
}
