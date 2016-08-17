package com.ldps.service.impl;

import java.util.Date;
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

	//用户删除分享给其他人的权限
	@Override
	public int removeSharedResource(String fromCId, String toCId,
			Integer sourceKeyId) {
		return  customerResourceRelDao.deleteSharedResource(fromCId,toCId,sourceKeyId);
	}

	//分享给其他人的权限
	@Override
	public int shareResource(String fromCId, String toCId, Integer sourceKeyId,
			Date startDate, Date endDate) {
		CusResourceRelModel model = new CusResourceRelModel();
		model.setCid(toCId);
		model.setFromShared("Y");
		model.setResourceId(sourceKeyId);
		model.setEnable("Y");
		model.setSharedUser(fromCId);
		model.setCreateDate(new Date());
		//model.setCreateUser(createUser);
		model.setStartDate(startDate);
		model.setEndDate(endDate);
		return  customerResourceRelDao.insertSelective(model);
	}

}