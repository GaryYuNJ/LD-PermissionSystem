package com.ldps.dao;

import org.apache.ibatis.annotations.Param;

import com.ldps.model.UserModel;

public interface UserModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserModel record);

    int insertSelective(UserModel record);

    UserModel selectByPrimaryKey(Long id);
    
    UserModel selectByUnamePword(@Param(value="userName")String userName,@Param(value="password")String password); 

    int updateByPrimaryKeySelective(UserModel record);

    int updateByPrimaryKey(UserModel record);
}