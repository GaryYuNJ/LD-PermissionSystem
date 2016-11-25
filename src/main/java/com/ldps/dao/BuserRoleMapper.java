package com.ldps.dao;

import org.apache.ibatis.annotations.Param;
import com.ldps.model.BuserRole;

public interface BuserRoleMapper {
    int insert(BuserRole record);

    int insertSelective(BuserRole record);
    
    int delete(@Param(value="roleId") Long roleId,@Param(value="userId") Long userId);
    int deleteByUser(@Param(value="userId") Long userId);
    int deleteByRole(@Param(value="roleId") Long roleId);
}