package com.ldps.service;

import java.util.List;

import com.ldps.model.CustomerGroupModel;
import com.ldps.model.CustomerModel;


public interface ICustomerGroupService {

	public List<CustomerGroupModel> queryAllWithPageIndex(Integer startRow, Integer pageSize);

	public Integer queryCustomerTotalCount();

	CustomerGroupModel getCustomerGroupModelByName(String name);

}
