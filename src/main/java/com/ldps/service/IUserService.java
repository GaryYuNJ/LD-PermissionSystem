package com.ldps.service;

import com.ldps.model.UserModel;

public interface IUserService {
	public UserModel getUserById(long userId); 
	UserModel getUserByUP(String userName,String password);
}
