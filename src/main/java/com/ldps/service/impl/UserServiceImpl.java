package com.ldps.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldps.dao.UserModelMapper;
import com.ldps.model.UserModel;
import com.ldps.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private UserModelMapper userMapper;
	@Autowired  
	private HttpSession session;  

	@Override
	public Long getSessionUserId (){
		// TODO Auto-generated method stub
		UserModel uModel = (UserModel) session.getAttribute("user");
		if(null == uModel){
			return 0L;
		}else{
			return uModel.getId();
		}
		
	}
	
	@Override
	public UserModel getSessionUserModel() {
		return null == session.getAttribute("user") ? null : (UserModel) session.getAttribute("user");
	}
	
	@Override
	public UserModel getUserById(long userId) {
		// TODO Auto-generated method stub
		return this.userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public UserModel getUserByUP(String userName, String password) {
		return userMapper.selectByUnamePword(userName, password);
	}

	@Override
	public int changePassword(UserModel currentUserModel, String newPassword) {
		currentUserModel.setPassword(newPassword);
		return userMapper.updateByPrimaryKeySelective(currentUserModel);
	}
}
