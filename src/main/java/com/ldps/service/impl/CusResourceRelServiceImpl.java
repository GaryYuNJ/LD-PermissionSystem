package com.ldps.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ldps.dao.CusResourceRelModelMapper;
import com.ldps.dao.CustomerModelMapper;
import com.ldps.model.CusResourceRelModel;
import com.ldps.model.CustomerModel;
import com.ldps.service.ICusResourceRelService;
import com.ldps.service.ICustomerService;

@Service("iCusResourceRelService")
public class CusResourceRelServiceImpl implements ICusResourceRelService {

	@Resource
	private CusResourceRelModelMapper customerResourceRelDao;

	@Override
	public CusResourceRelModel queryModelByCidAndResId(CusResourceRelModel model) {
		// TODO Auto-generated method stub
		return customerResourceRelDao.selectByCidAndGroupId(model);
	}

}