package com.ldps.service;

import com.ldps.model.CustomerModel;

public interface ICustomerService {
    public CustomerModel getUserByCId(String cid); 

	//验证添加是否数据的完整性
	String addVerification(CustomerModel custoemrModel);
	
	//新增数据
	int addCustomer(CustomerModel custoemrModel);
	
	//删除数据
	int deleteCustomer(String cid);
	
	//按照账号更新数据
	int updateCustomer(CustomerModel custoemrModel);

	public CustomerModel getModelWithGroupsByCID(String cid);
	 
}
