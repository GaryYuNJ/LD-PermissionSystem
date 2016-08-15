package com.ldps.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ldps.dao.CusGrpResourceRelModelMapper;
import com.ldps.model.CusGrpResourceRelModel;
import com.ldps.service.ICusGrpResourceRelService;

@Service("iCusGrpResourceRelService")
public class CusGrpResourceRelServiceImpl implements ICusGrpResourceRelService {

	@Resource
	private CusGrpResourceRelModelMapper customerGrpResourceRelDao;

	@Override
	public CusGrpResourceRelModel queryModelByCidAndResId(CusGrpResourceRelModel model) {
		// TODO Auto-generated method stub
		return customerGrpResourceRelDao.selectByGrpIdAndResId(model);
	}

}