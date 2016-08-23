package com.ldps.facade;

import java.util.List;

import com.ldps.data.CustomerData;
import com.ldps.data.CustomerGroupData;



public interface CustomerGroupFacade {

	List<CustomerGroupData> queryAllCusGropWithPageIndex(Integer startRow,
			Integer pageSize);

	Integer queryCusGroupTotalCount();

	CustomerGroupData searchUserGroupByNameWithPageIndex(String name,
			Integer startRow, Integer pageSize);

	
}
