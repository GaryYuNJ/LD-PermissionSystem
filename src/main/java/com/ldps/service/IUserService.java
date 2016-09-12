package com.ldps.service;

import com.ldps.model.UserModel;

public interface IUserService {
	public UserModel getUserById(long userId); 
	UserModel getUserByUP(String userName,String password);
	int changePassword(UserModel currentUserModel, String newPassword);
	UserModel getSessionUserModel();
	Long  getSessionUserId();
}
