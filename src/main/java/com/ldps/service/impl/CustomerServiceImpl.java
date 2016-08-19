package com.ldps.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ldps.dao.CustomerModelMapper;
import com.ldps.dao.ResourceModelMapper;
import com.ldps.model.CustomerModel;
import com.ldps.model.ResourceModel;
import com.ldps.service.ICustomerService;

@Service("iCustomerSevice")
public class CustomerServiceImpl implements ICustomerService {

	@Resource
	private CustomerModelMapper customerDao;
	
	@Resource
	private ResourceModelMapper resourceDao;
	
	@Override
	public String addVerification(CustomerModel custoemrModel) {
		 String message="";
		 if(StringUtils.isEmpty(custoemrModel.getCid())){
			 message+="会员账号不能为空;";
		 }
		 if(StringUtils.isEmpty(custoemrModel.getCtype())){
			 message+="会员类型不能为空;";
		 }
		 if(StringUtils.isEmpty(custoemrModel.getCstatus())){
			 message+="会员状态不能为空;";
		 }
		return message;
	}
 
	@Override
	public int addCustomer(CustomerModel custoemrModel) {
		if(getUserByCId(custoemrModel.getCid())!=null)
			return -1;
		return customerDao.insertSelective(custoemrModel);
	}

	@Override
	public int deleteCustomer(String cid) {
		return customerDao.deleteByCID(cid);
	}

	@Override
	public int updateCustomer(CustomerModel custoemrModel) {
		return customerDao.updateByCIDSelective(custoemrModel);
	}

	@Override
	public CustomerModel getUserByCId(String cid) {
		return customerDao.simpleSelectByCID(cid);
	}
	
	@Override
	public CustomerModel getModelWithGroupsByCID(String cid) {
		return customerDao.simpleSelectWithGroupsByCID(cid);
	}
	
	@Override
	public CustomerModel simpleSelectWithGroupsById(Long customerId) {
		return customerDao.simpleSelectWithGroupsById(customerId);
	}


	//获取用户可分享权限的资源列表
	/*
		不包含公共资源，不包含用户组授权，只针对用户与资源的可用关系
	*/
	@Override
	public List<ResourceModel> querySharableResource(Long customerId) {
		return resourceDao.selectSharableResourceById(customerId);
	}

	@Override
	public Long getCustomerIdByMobile(String mobile) {
		// TODO Auto-generated method stub
		return customerDao.queryIdByMobile(mobile);
	}
	
	@Override
	public CustomerModel getCustomerModelByMobile(String mobile) {
		// TODO Auto-generated method stub
		return customerDao.simpleSelectByMobile(mobile);
	}
}
