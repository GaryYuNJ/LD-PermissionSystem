package com.ldps.controller;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ldps.data.BootstrapTableData;
import com.ldps.data.CustomerData;
import com.ldps.data.CustomerGroupData;
import com.ldps.facade.CustomerFacade;
import com.ldps.facade.CustomerGroupFacade;
import com.ldps.model.CustomerModel;
import com.ldps.service.ICustomerService;

@Controller
@RequestMapping(value = "userGroup")
public class CustomerGroupController {

	CustomerModel customer;
	
	@Resource
	CustomerGroupFacade customerGroupFacade;

	@RequestMapping(value="showUserGroup.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String showUserGroups( String search, @RequestParam("limit") Integer limit, 
			@RequestParam("offset") Integer offset, ModelMap model){
		//页面菜单样式需要
		model.put("pageIndex", 4);
		
		BootstrapTableData bData = new BootstrapTableData();
		
		if(StringUtils.isEmpty(search)){
			List<CustomerGroupData> cDatas = customerGroupFacade.queryAllCusGropWithPageIndex(offset, limit);
			if(null != cDatas && cDatas.size()>0){
				bData.setRows(cDatas);
				bData.setPage(offset/limit +1);
				//get total
				bData.setTotal(customerGroupFacade.queryCusGroupTotalCount());
			}
		}else{
			CustomerGroupData cData = customerGroupFacade.searchUserGroupByNameWithPageIndex(search, offset, limit);
			
			if(null != cData){
				List<CustomerGroupData> cDatas = new ArrayList<CustomerGroupData> ();
				cDatas.add(cData);
				bData.setRows(cDatas);
				bData.setPage(1);				
				bData.setTotal(1);
			}
		}
		return JSON.toJSONString(bData);
	}

	@RequestMapping(value="userGroupManage",method=RequestMethod.GET)
	public String userGroupManage(ModelMap model){
		//页面菜单样式需要
		model.put("pageIndex", 4);
		return "userGroupManage";
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

}
