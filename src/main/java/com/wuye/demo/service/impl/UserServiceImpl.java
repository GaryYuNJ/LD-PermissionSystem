package com.wuye.demo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wuye.demo.dao.UserMapper;
import com.wuye.demo.model.User;
import com.wuye.demo.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userMapper.selectByPrimaryKey(userId);
	}

}
