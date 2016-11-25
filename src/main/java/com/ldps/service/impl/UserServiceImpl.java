package com.ldps.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldps.dao.BuserRoleMapper;
import com.ldps.dao.UserModelMapper;
import com.ldps.model.BuserRole;
import com.ldps.model.UserModel;
import com.ldps.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private UserModelMapper userMapper;
	

	@Resource
	private BuserRoleMapper buserRoleDao;
	
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

	@Override
	public List<UserModel> queryUserWithPageIndex(String name, Integer pageNo,
			Integer pageSize) {
		return userMapper.selectUserWithCondition(name, pageNo, pageSize);
	}

	@Override
	public int queryCountByCondition(String name) {
		return userMapper.selectCountWithCondition(name);
	}

	@Override
	public int saveOrUpdate(UserModel userModel) {
		if(userModel.getId()==null){
			return userMapper.insert(userModel);
		}else{
			return userMapper.updateByPrimaryKey(userModel);
		}
	}
	@Override
	public int getUserByName(String name) {
		return  userMapper.selectByUname(name);
	}
	
	@Override
	public int delBuser(Long id) {
		return  userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int saveOrupdateBURole(BuserRole buserRole) {
		return buserRoleDao.insert(buserRole);
	}

	@Override
	public int delBURole(Long userId, Long roleId) {
		return buserRoleDao.delete(roleId, userId);
	}
	
	@Override
	public int delBUByRole(Long roleId) {
		return buserRoleDao.deleteByRole(roleId);
	}
	
	@Override
	public int delBURoleByUserId(Long userId) {
		return buserRoleDao.deleteByUser(userId);
	}
}
