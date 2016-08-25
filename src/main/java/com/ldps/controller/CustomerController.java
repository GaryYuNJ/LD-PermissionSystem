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
import com.ldps.data.APIMessage;
import com.ldps.data.BootstrapTableData;
import com.ldps.data.CustomerData;
import com.ldps.facade.CustomerFacade;
import com.ldps.model.CustomerModel;
import com.ldps.service.ICustomerService;

@Controller
@RequestMapping(value = "user")
public class CustomerController {

	CustomerModel customer;
	
	@Resource
	ICustomerService customerService;
	
	@Resource
	CustomerFacade customerFacade;

	@RequestMapping(value="userManage",method=RequestMethod.GET)
	public String userManage( ModelMap model){
		//页面菜单样式需要
		model.put("pageIndex", 3);
		
		return "userManage";
	}
	
	@RequestMapping(value="showUserList.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String showUserList( String search, @RequestParam("limit") Integer limit, 
			@RequestParam("offset") Integer offset, ModelMap model){
		//页面菜单样式需要
		model.put("pageIndex", 3);
		
		BootstrapTableData bData = new BootstrapTableData();
		
		if(StringUtils.isEmpty(search)){
			List<CustomerData> cDatas = customerFacade.queryAllUserListWithPageIndex(offset, limit);
			if(null != cDatas && cDatas.size()>0){
				bData.setRows(cDatas);
				bData.setPage(offset/limit +1);
				//get total
				bData.setTotal(customerFacade.queryCustomerTotalCount());
			}
		}else{
			CustomerData cData = customerFacade.searchUserByMobileWithPageIndex(search, offset, limit);
			
			if(null != cData && null != cData.getId()){
				List<CustomerData> cDatas = new ArrayList<CustomerData> ();
				cDatas.add(cData);
				bData.setRows(cDatas);
				bData.setPage(1);				
				bData.setTotal(1);
			}
		}
		if(null == bData.getRows()){
			bData.setPage(0);
			bData.setRows(new Object());
			bData.setTotal(0);
		}
		return JSON.toJSONString(bData);
	}
	@RequestMapping(value="showUserDetail.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String showUserDetail( @RequestParam("userId") Long userId, ModelMap model){
		CustomerData data = customerFacade.getUserDataByPrimaryId(userId);
		return JSON.toJSONString(data);
	}
	
	//删除用户与用户组关系
	@RequestMapping(value="delUserGroupRelation.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String delUserGroupRelation( @RequestParam("userId") Long userId , @RequestParam("groupId") Integer groupId , ModelMap model){
		
		APIMessage apiMessage = new APIMessage();
		
		int flag = customerFacade.delUserGroupRelation(userId, groupId);
		apiMessage.setStatus(flag);
		
		return JSON.toJSONString(apiMessage);
	}
	
	//删除用户与用户组关系
	@RequestMapping(value="addUserGroupRelation.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addUserGroupRelation( @RequestParam("userId") Long userId , 
			@RequestParam("groupId") Integer groupId , ModelMap model){
		
		APIMessage apiMessage = new APIMessage();
		
		int flag = customerFacade.addUserGroupRelation(userId, groupId);
		apiMessage.setStatus(flag);
		
		return JSON.toJSONString(apiMessage);
	}
	
	@RequestMapping(value="/queryCustomer1",method=RequestMethod.GET)
	public String demo(@ModelAttribute CustomerModel customer,Model model){
		model.addAttribute("customer", customerService.getUserByCId(customer.getCid()));
		return "demo";
	}
	
	@RequestMapping(value="/queryCustomer2",method=RequestMethod.GET)
	public String demo2(@RequestParam("id")String id,Model model){
		CustomerModel cModel= customerService.getUserByCId(id);
		model.addAttribute("customer", cModel);
		return "demo";
	}
	
	@RequestMapping(value="/queryCustomer3",method=RequestMethod.GET)
	public String demo3(Model model){
		model.addAttribute("customer", customerService.getUserByCId(customer.getCid()));
		return "demo";
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public ICustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}
	

}
