package com.ldps.dao;

import com.wuye.demo.model.User;

public interface UserMapper {

    User selectByPrimaryKey(Integer id);
    
    User selectByUserName(String userName);
    
    int deleteByPrimaryKey(Integer id);
    
    int deleteByUserName(String userName);

    int insert(User record);

    //int insertSelective(User record);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}