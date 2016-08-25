package com.ldps.facade.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ldps.converter.CusResourceRelModelConverter;
import com.ldps.converter.CustomerGroupModelConverter;
import com.ldps.converter.CustomerModelConverter;
import com.ldps.converter.ResourceModelConverter;
import com.ldps.data.CusResourceRelData;
import com.ldps.data.CustomerData;
import com.ldps.data.CustomerGroupData;
import com.ldps.data.ResourceData;
import com.ldps.facade.CustomerFacade;
import com.ldps.facade.CustomerGroupFacade;
import com.ldps.model.CusGrpResourceRelModel;
import com.ldps.model.CusResourceRelModel;
import com.ldps.model.CustomerModel;
import com.ldps.model.ResourceGroupModel;
import com.ldps.model.CustomerGroupModel;
import com.ldps.model.ResourceModel;
import com.ldps.service.ICusGrpResourceRelService;
import com.ldps.service.ICusResourceRelService;
import com.ldps.service.ICustomerGroupService;
import com.ldps.service.ICustomerService;
import com.ldps.service.IResourceService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("customerGroupFacade")
public class CustomerGroupFacadeImpl implements CustomerGroupFacade {

	private static Logger logger = Logger
			.getLogger(CustomerGroupFacadeImpl.class);
	
	@Resource
	ICustomerGroupService iCustomerGroupService;

	@Resource
	CustomerGroupModelConverter customerGroupModelConverter;
	
	@Override
	public List<CustomerGroupData> queryAllCusGropWithPageIndex(Integer startRow,
			Integer pageSize) {

		List<CustomerGroupModel> cModels = iCustomerGroupService.queryAllWithPageIndex(startRow, pageSize);
		
		return customerGroupModelConverter.processList(cModels);
	}

	@Override
	public Integer queryCusGroupTotalCount() {
		// TODO Auto-generated method stub
		return iCustomerGroupService.queryCustomerTotalCount();
	}

	@Override
	public CustomerGroupData searchUserGroupByNameWithPageIndex(String name,
			Integer offset, Integer limit) {
		CustomerGroupModel cModel = iCustomerGroupService.getCustomerGroupModelByName(name);
		
		return customerGroupModelConverter.process(cModel,null);
	}

	@Override
	public List<CustomerGroupData> queryJoinCustomerIdWithPageIndex(Long customerId,
			Integer offset, Integer limit) {
		List<CustomerGroupModel> cModels = 
				iCustomerGroupService.queryJoinCusIdWithPageIndex(offset, limit, customerId);
		
		return customerGroupModelConverter.processList(cModels);
	}
	
	@Override
	public CustomerGroupData searchByNameJoinCusIdWithPageIndex(String name, Long customerId,
			Integer offset, Integer limit) {
		CustomerGroupModel cModel = 
				iCustomerGroupService.queryByNameJoinCusIdWithPageIndex(name, customerId);
		
		return customerGroupModelConverter.process(cModel, null);
	}
}
