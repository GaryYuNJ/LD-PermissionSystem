package com.ldps.service.impl;


import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ldps.dao.CusGroupRelModelMapper;
import com.ldps.model.CusGroupRelModel;
import com.ldps.service.ICustomerGroupRelService;

@Service("iCustomerGroupRelService")
public class CustomerGroupRelServiceImpl implements ICustomerGroupRelService {
 

	@Resource
	private CusGroupRelModelMapper customerGroupRelDao;
	

	@Override
	public int delUserGroupRelation(Long userId, Integer groupId) {
		// TODO Auto-generated method stub
		return customerGroupRelDao.delUserGroupRelation(userId, groupId);
	}


	@Override
	public int addUserGroupRelation(Long userId, Integer groupId) {
		// TODO Auto-generated method stub
		CusGroupRelModel model = new CusGroupRelModel();
		model.setCgroupId(groupId);
		model.setCustomerId(userId);
		model.setCreateDate(new Date());
		model.setCreateUser(0); //session User
		return customerGroupRelDao.insert(model);
	}
	

}
