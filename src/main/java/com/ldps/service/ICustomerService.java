package com.ldps.service;

import java.util.List;

import com.ldps.model.CustomerModel;
import com.ldps.model.ResourceModel;

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
	
	//获取用户可分享权限的资源列表
	/*
		不包含公共资源，不包含用户组授权，只针对用户与资源的可用关系
	*/
	List<ResourceModel> querySharableResource(Long customerId);
	
	Long getCustomerIdByMobile(String mobile);

	CustomerModel simpleSelectWithGroupsById(Long customerId);

	CustomerModel getCustomerModelByMobile(String mobile);

	List<CustomerModel> queryAllWithPageIndex(Integer startRow, Integer pageSize);
	
	Integer queryCustomerTotalCount();

	public CustomerModel UserDataByPrimaryId(Long customerId);

	public List<CustomerModel> queryByMobileAndNameWithPageIndex(String mobile,
			String userName, Integer startRow, Integer pageSize);

	public Integer queryTotalCountByMobileAndName(String mobile, String userName);
}
