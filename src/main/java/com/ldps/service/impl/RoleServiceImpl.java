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
	public int saveOrUpdate(Role role) {
		if(role!=null){
			if(role.getId()!=null){
				return roleDao.updateByPrimaryKeySelective(role);
			}else{
				return roleDao.insert(role);
			}
		}
		return 0;
	}

	@Override
	public int deleteById(Long id) {
		return roleDao.deleteByPrimaryKey(id);
	}

}
