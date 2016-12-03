package com.ldps.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ldps.model.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
	List<Role> selectRoleWithCondition(@Param("roleName")String roleName,@Param("startRow") Integer startRow,
			@Param("pageSize") Integer pageSize);
	
	int selectCountWithCondition(@Param("roleName")String roleName);
	List<Role> selectRoleWithConditionUser(@Param("roleName")String roleName,@Param("userId")Long userId,@Param("startRow") Integer startRow,
			@Param("pageSize") Integer pageSize);
	
	int selectCountWithConditionUser(@Param("roleName")String roleName,@Param("userId")Long userId);
	
	List<Role> selectAllRoles();
}

