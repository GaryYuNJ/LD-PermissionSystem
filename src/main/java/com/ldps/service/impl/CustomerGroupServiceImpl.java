package com.ldps.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ldps.dao.CustomerGroupModelMapper;
import com.ldps.model.CustomerGroupModel;
import com.ldps.service.ICustomerGroupService;
import com.ldps.service.IUserService;

@Service("iCustomerGroupService")
public class CustomerGroupServiceImpl implements ICustomerGroupService {
 

	@Resource
	private CustomerGroupModelMapper customerGroupDao;
	@Resource
	private IUserService userService;
	
	@Override
	public List<CustomerGroupModel> queryAllWithPageIndex(Integer startRow,
			Integer pageSize) {

		return customerGroupDao.selectAllWithPageIndex(startRow, pageSize);
	}

	@Override
	public Integer queryCustomerTotalCount() {
		return customerGroupDao.selectTotalCount();
	}

	@Override
	public CustomerGroupModel getCustomerGroupModelByName(String name) {
		return customerGroupDao.selectByName(name);
	}
	

	@Override
	public List<CustomerGroupModel> getCustomerGroupByNameLike(String name,Integer startRow, Integer pageSize) {
		return customerGroupDao.selectByNameLike(name, startRow, pageSize);
	}

	@Override
	public Integer queryCustomerTotalCountByNameLike(String name) {
		return customerGroupDao.selectTotalCountByNameLike(name);
	}

	@Override
	public List<CustomerGroupModel> queryJoinCusIdWithPageIndex(Integer startRow,
			Integer pageSize, Long customerId) {

		return customerGroupDao.selectJoinCustomerIdWithPageIndex(startRow, pageSize, customerId);
	}

	@Override
	public List<CustomerGroupModel> queryByNameJoinCusIdWithPageIndex(Integer startRow,
			Integer pageSize, String name, Long customerId) {
		return customerGroupDao.selectByNameJoinCusIdWithPageIndex(startRow, pageSize, name, customerId);
	}

	@Override
	public int createNewUserGroup(String userGroupName) {
		CustomerGroupModel model = new CustomerGroupModel(); 
		model.setName(userGroupName);
		model.setCreateDate(new Date());
		model.setStatus("Y");
		model.setCreateUser(userService.getSessionUserId());
		
		return customerGroupDao.insertSelective(model);
	}

	@Override
	public int deleteUserGroupById(Integer customerGroupId) {
		return customerGroupDao.deleteByPrimaryKey(customerGroupId);
	}

	@Override
	public CustomerGroupModel showUserGroupDetail(Integer customerGroupId) {
		return customerGroupDao.selectByPrimaryKey(customerGroupId);
	}

	//防止资源死锁
	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public int updateUserGroup(Integer customerGroupId,
			String customerGroupName) {
		CustomerGroupModel model = new CustomerGroupModel(); 
		model.setId(customerGroupId);
		model.setName(customerGroupName);
		
		return customerGroupDao.updateByPrimaryKeySelective(model);
	}
}
