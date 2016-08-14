package com.ldps.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ldps.dao.CustomerModelMapper;
import com.ldps.model.CustomerModel;
import com.ldps.service.ICustomerService;

@Service("customerService")
public class CustomerServiceImpl implements ICustomerService {

	@Resource
	private CustomerModelMapper customerMapper;

	@Override
	public CustomerModel getUserByCId(String cid) {
		// TODO Auto-generated method stub
		return this.customerMapper.simpleSelectByCID(cid);
	}

	
}
