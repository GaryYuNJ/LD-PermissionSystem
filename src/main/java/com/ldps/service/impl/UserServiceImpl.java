package com.ldps.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ldps.dao.UserModelMapper;
import com.ldps.model.UserModel;
import com.ldps.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private UserModelMapper userMapper;

	@Override
	public UserModel getUserById(long userId) {
		// TODO Auto-generated method stub
		return this.userMapper.selectByPrimaryKey(userId);
	}

}
