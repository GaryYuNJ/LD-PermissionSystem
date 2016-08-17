package com.ldps.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ldps.dao.CusResourceRelModelMapper;
import com.ldps.model.CusResourceRelModel;
import com.ldps.service.ICusResourceRelService;

@Service("iCusResourceRelService")
public class CusResourceRelServiceImpl implements ICusResourceRelService {

	@Resource
	private CusResourceRelModelMapper customerResourceRelDao;

	@Override
	public CusResourceRelModel queryModelByCidAndResId(CusResourceRelModel model) {
		// TODO Auto-generated method stub
		return customerResourceRelDao.selectByCidAndResourceId(model);
	}

	@Override
	public List<CusResourceRelModel> queryByShareCustomerId(
			String customerId) {
		// TODO Auto-generated method stub
		return  customerResourceRelDao.selectByShareCustomerId(customerId);
	}

}