package com.ldps.service.impl;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.ldps.dao.CusGroupRelModelMapper;
import com.ldps.dao.CusGroupResGroupRelModelMapper;
import com.ldps.data.CusAndCusGrpRelChangeEventData;
import com.ldps.event.AddCusToCusGroupPermChangeEvent;
import com.ldps.event.RemoveCusFromCusGrpPermChangeEvent;
import com.ldps.model.CusGroupRelModel;
import com.ldps.service.ICusGrpResourceRelService;
import com.ldps.service.ICustomerGroupRelService;
import com.ldps.service.IUserService;

@Service("iCustomerGroupRelService")
public class CustomerGroupRelServiceImpl implements ICustomerGroupRelService {
 

	@Resource
	private CusGroupRelModelMapper customerGroupRelDao;
	@Resource
	private ICusGrpResourceRelService iCusGrpResourceRelService;
	@Autowired  
	private ApplicationContext applicationContext;  
	@Resource
	private CusGroupResGroupRelModelMapper cusGroupResGroupRelDao;
	@Resource
	private IUserService userService;
	
	@Override
	public int delUserGroupRelation(Long userId, Integer groupId) {
		int flag = customerGroupRelDao.delUserGroupRelation(userId, groupId);
		
		//用户组删除用户带来的权限更新
		//构造异步 event 
		CusAndCusGrpRelChangeEventData eData = new CusAndCusGrpRelChangeEventData();
		eData.setCusGroupId(groupId);
		eData.setCustomerId(userId);
		applicationContext.publishEvent(new RemoveCusFromCusGrpPermChangeEvent(eData));
		
		return flag;
	}


	@Override
	public int addUserGroupRelation(Long userId, Integer groupId) {
		CusGroupRelModel model = new CusGroupRelModel();
		model.setCgroupId(groupId);
		model.setCustomerId(userId);
		model.setCreateDate(new Date());
		model.setCreateUser(userService.getSessionUserId()); //session User
		int flag = customerGroupRelDao.insert(model);
		
		//用户组的权限也要授权给用户
		//构造异步 event data
		CusAndCusGrpRelChangeEventData eData = new CusAndCusGrpRelChangeEventData();
		eData.setCusGroupId(groupId);
		eData.setCustomerId(userId);
		applicationContext.publishEvent(new AddCusToCusGroupPermChangeEvent(eData));
		
		return flag;
	}
	
	@Override
	public List<CusGroupRelModel> queryByGroupId(Integer groupId) {
		return customerGroupRelDao.selectByGroupId(groupId);
	}
	

}
