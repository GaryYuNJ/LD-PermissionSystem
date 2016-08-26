package com.ldps.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ldps.dao.CustomerGroupModelMapper;
import com.ldps.model.CustomerGroupModel;
import com.ldps.service.ICustomerGroupService;

@Service("iCustomerGroupService")
public class CustomerGroupServiceImpl implements ICustomerGroupService {
 

	@Resource
	private CustomerGroupModelMapper customerGroupDao;
	
	@Override
	public List<CustomerGroupModel> queryAllWithPageIndex(Integer startRow,
			Integer pageSize) {

		return customerGroupDao.selectAllWithPageIndex(startRow, pageSize);
	}

	@Override
	public Integer queryCustomerTotalCount() {
		// TODO Auto-generated method stub
		return customerGroupDao.selectTotalCount();
	}

	@Override
	public CustomerGroupModel getCustomerGroupModelByName(String name) {
		// TODO Auto-generated method stub
		return customerGroupDao.selectByName(name);
	}

	@Override
	public List<CustomerGroupModel> queryJoinCusIdWithPageIndex(Integer startRow,
			Integer pageSize, Long customerId) {

		return customerGroupDao.selectJoinCustomerIdWithPageIndex(startRow, pageSize, customerId);
	}

	@Override
	public CustomerGroupModel queryByNameJoinCusIdWithPageIndex(String name, Long customerId) {
		return customerGroupDao.selectByNameJoinCusIdWithPageIndex(name, customerId);
	}

	@Override
	public int createNewUserGroup(String userGroupName) {
		CustomerGroupModel model = new CustomerGroupModel(); 
		model.setName(userGroupName);
		model.setCreateDate(new Date());
		model.setStatus("Y");
		model.setCreateUser(0);
		
		return customerGroupDao.insertSelective(model);
	}

	@Override
	public int deleteUserGroupById(Integer customerGroupId) {
		// TODO Auto-generated method stub
		return customerGroupDao.deleteByPrimaryKey(customerGroupId);
	}

	@Override
	public CustomerGroupModel showUserGroupDetail(Integer customerGroupId) {
		// TODO Auto-generated method stub
		return customerGroupDao.selectByPrimaryKey(customerGroupId);
	}
}
