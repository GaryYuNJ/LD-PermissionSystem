package com.ldps.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ldps.dao.RoleMapper;
import com.ldps.model.Role;
import com.ldps.service.IRoleService;

@Service("iRoleService")
public class RoleServiceImpl implements IRoleService {

	@Resource
	private RoleMapper roleDao;
	
	@Override
	public List<Role> queryRoleWithPageIndex(String roleName, Integer pageNo, Integer pageSize) {
		return roleDao.selectRoleWithCondition(roleName, pageNo, pageSize);
	}

	@Override
	public int queryCountByCondition(String roleName) {
		return roleDao.selectCountWithCondition(roleName);
	}

	@Override
	public List<Role> queryRoleWithPageIndexWithUser(String roleName,Long userId, Integer pageNo, Integer pageSize) {
		return roleDao.selectRoleWithConditionUser(roleName,userId, pageNo, pageSize);
	}

	@Override
	public int queryCountByConditionWithUser(String roleName,Long userId) {
		return roleDao.selectCountWithConditionUser(roleName,userId);
	}

	@Override
	public Long saveOrUpdate(Role role) {
		if(role!=null){
			if(role.getId()!=null){
				if(roleDao.updateByPrimaryKeySelective(role)==1)
					return role.getId();
			}else{
				roleDao.insert(role);
				return role.getId();
			}
		}
		return 0L;
	}

	@Override
	public int deleteById(Long id) {
		return roleDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<Role> getAllRole() {
		return roleDao.selectAllRoles();
	}

	@Override
	public Role queryRoleById(Long id) {
		return roleDao.selectByPrimaryKey(id);
	}
}
