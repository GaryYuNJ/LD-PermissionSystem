package com.ldps.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ldps.data.APIMessage;
import com.ldps.data.CustomerData;
import com.ldps.data.CustomerGroupData;
import com.ldps.facade.CustomerFacade;
import com.ldps.facade.CustomerGroupFacade;
import com.ldps.model.CustomerModel;
import com.ldps.service.ICustomerService;

@Controller
@RequestMapping(value = "oaApi")
public class CustomerOAAPIController {
	private static Logger logger = Logger
			.getLogger(CustomerOAAPIController.class);

	@Resource
	private CustomerFacade customerFacade;
	
	@Resource
	private ICustomerService iCustomerSevice;

	@Resource
	CustomerGroupFacade customerGroupFacade;
	
	@RequestMapping(value="/createCusGroup",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String createCusGroup(@RequestParam("groupName")String groupName,
			Model model){
		APIMessage apiMessage = new APIMessage();
		
		if(!StringUtils.isEmpty(groupName)){
		
			CustomerGroupData data = customerGroupFacade.searchUserGroupByNameWithPageIndex(groupName, 1, 1);
			if(null == data || null == data.getId()){
				customerGroupFacade.createNewUserGroup(groupName);
				data = customerGroupFacade.searchUserGroupByNameWithPageIndex(groupName, 1, 1);
				
				if(null != data ){
					apiMessage.setStatus(1);
					apiMessage.setMessage("");
					apiMessage.setContent(data);
				}else{
					apiMessage.setStatus(0);
					apiMessage.setMessage("添加失败");
				}
			}else{
				apiMessage.setStatus(-1);
				apiMessage.setMessage("用户组已存在");
			}
			
		}else{
			apiMessage.setStatus(-2);
			apiMessage.setMessage("用户组名称为空");
		}
		
		return JSON.toJSONString(apiMessage);
	}
	
	@RequestMapping(value="/addNewCusToGroup",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addNewCusToGroup(@RequestParam("groupId")String groupId,
			@RequestParam("mobile")String mobile,
			Model model){
		APIMessage apiMessage = new APIMessage();
		
		CustomerModel customerModel= iCustomerSevice.getCustomerModelByMobile(mobile);
		if(null == customerModel || null == customerModel.getId()){
			//临时插入用户表数据，同时JOB更新
			customerModel=new CustomerModel(); 
			customerModel.setCmMobile1(mobile);
			iCustomerSevice.addTempCustomer(customerModel);
			customerModel= iCustomerSevice.getCustomerModelByMobile(mobile);
		}
		
		
		int flag = customerFacade.addUserGroupRelation(customerModel.getId(), Integer.parseInt(StringUtils.trimWhitespace(groupId)));
		apiMessage.setStatus(1);
		apiMessage.setMessage("");
		
		return JSON.toJSONString(apiMessage);
	}
		
	
	@RequestMapping(value="/removeCusFromGroup",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String removeCusFromGroup(@RequestParam("groupId")String groupId,
			@RequestParam("mobile")String mobile,
			Model model){
		APIMessage apiMessage = new APIMessage();
		
		CustomerData cusData = customerFacade.searchUserByMobileWithPageIndex(mobile, 1, 1);
		
		if(null != cusData && null != cusData.getId()){
			int flag = customerFacade.delUserGroupRelation(cusData.getId(), Integer.parseInt(StringUtils.trimWhitespace(groupId)));
			apiMessage.setStatus(1);
			apiMessage.setMessage("");
		}else{
			apiMessage.setStatus(-1);
			apiMessage.setMessage("用户不存在");
		}
		
		return JSON.toJSONString(apiMessage);
	}
		
}
