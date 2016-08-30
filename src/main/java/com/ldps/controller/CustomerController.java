package com.ldps.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.ldps.model.CusResourceRelModel;
import com.ldps.model.CustomerModel;
import com.ldps.model.ResourceModel;
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
	public String showUserList( String mobile, String userName, @RequestParam("limit") Integer limit, 
			@RequestParam("offset") Integer offset, ModelMap model){
		
		BootstrapTableData bData = new BootstrapTableData();
		
		if(StringUtils.isEmpty(mobile) && StringUtils.isEmpty(userName)){
			List<CustomerData> cDatas = customerFacade.queryAllUserListWithPageIndex(offset, limit);
			if(null != cDatas && cDatas.size()>0){
				bData.setRows(cDatas);
				bData.setPage(offset/limit +1);
				//get total
				bData.setTotal(customerFacade.queryCustomerTotalCount());
			}
		}else{
			List<CustomerData>  cDatas = customerFacade.searchByMobileAndNameWithPageIndex(mobile,userName , offset, limit);
			
			if(null != cDatas && cDatas.size()>0){
				bData.setRows(cDatas);
				bData.setPage(offset/limit +1);
				//get total
				bData.setTotal(customerFacade.queryTotalCountByMobileAndName(mobile,userName));
			}
		}
		if(null == bData.getRows()){
			bData.setPage(0);
			bData.setRows(new Object());
			bData.setTotal(0);
		}
		return JSON.toJSONString(bData);
	}
	

	@RequestMapping(value="showUserListWithGroupId.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String showUserListWithGroupId( @RequestParam("userGroupId") String userGroupId, @RequestParam("bindUserFlag") Integer bindUserFlag, 
			String mobile, String userName, @RequestParam("limit") Integer limit, @RequestParam("offset") Integer offset, ModelMap model){
		
		BootstrapTableData bData = new BootstrapTableData();
		List<CustomerData> cDatas = new ArrayList<CustomerData>();
		
		//标志位，只搜索与groupId绑定的用户
		if(bindUserFlag == 1){
			cDatas = customerFacade.searchWithBindGrpIdByNameAndMobile(userGroupId,mobile,userName,offset, limit);
			if(null != cDatas && cDatas.size()>0){
				bData.setRows(cDatas);
				bData.setPage(offset/limit +1);
				//get total
				bData.setTotal(customerFacade.queryTotalCountWithBindGrpId(userGroupId,mobile,userName));
			}
			
		}else{
			//根据 mobile，name搜索用户列表，并加上与指定groupId的依赖关系
			cDatas = customerFacade.searchWithGrpIdFlagByNameAndMobile(userGroupId, mobile, userName, offset, limit);
			if(null != cDatas && cDatas.size()>0){
				bData.setRows(cDatas);
				bData.setPage(offset/limit +1);
				//根据mobile，name 查用户总数
				bData.setTotal(customerFacade.queryTotalCountByMobileAndUserName(mobile,userName));
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

	
	//用户组添加用户
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
	
	//后台页面上更新\添加用户资源权限
	@RequestMapping(value="authCusResPermission.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String authCusResPermission( @RequestParam("modelJsonStr") String modelJsonStr ,
			String jointAuthFlag, String startDateStr, String endDateStr, ModelMap model) throws ParseException{
		APIMessage apiMessage = new APIMessage();
		CusResourceRelModel cusResourceRelModel= JSON.parseObject(modelJsonStr, CusResourceRelModel.class);
		
		SimpleDateFormat sf = new SimpleDateFormat("yyy-MM-dd HH:mm");
		
		if(!StringUtils.isEmpty(startDateStr)){
			cusResourceRelModel.setStartDate(sf.parse(startDateStr));
		}
		if(!StringUtils.isEmpty(endDateStr)){
			cusResourceRelModel.setEndDate(sf.parse(endDateStr));
		}
		
		if("on".equals(cusResourceRelModel.getEnable())){
			cusResourceRelModel.setEnable("Y");
		}else{
			cusResourceRelModel.setEnable("N");
		}
		
		cusResourceRelModel.setFromShared("N");
		cusResourceRelModel.setCreateUser(0L);
		
		int flag = 0;
		//联合授权
		if("on".equals(jointAuthFlag)){
			flag = customerFacade.jointAuthCusResPermission(cusResourceRelModel);
		//单个资源授权
		}else{
			flag = customerFacade.authCusResPermission(cusResourceRelModel);
		}
		
		apiMessage.setStatus(flag);
		
		return JSON.toJSONString(apiMessage);
	}
	
	//禁用资源
	@RequestMapping(value="disableResourcePermission.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String disableResourcePermission( @RequestParam("userId") Long userId , 
			@RequestParam("resourceId") Integer resourceId , ModelMap model){
		
		APIMessage apiMessage = new APIMessage();
		
		int flag = customerFacade.disableResourcePermission(userId, resourceId);
		apiMessage.setStatus(flag);
		
		return JSON.toJSONString(apiMessage);
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
