package com.ldps.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ldps.data.APIMessage;
import com.ldps.data.BootstrapTableData;
import com.ldps.data.CustomerGroupData;
import com.ldps.facade.CustomerGroupFacade;
import com.ldps.model.CusGroupResGroupRelModel;
import com.ldps.model.CusGrpResourceRelModel;
import com.ldps.model.CusResourceRelModel;
import com.ldps.model.CustomerModel;

@Controller
@RequestMapping(value = "userGroup")
public class CustomerGroupController {

	CustomerModel customer;
	
	@Resource
	CustomerGroupFacade customerGroupFacade;

	@RequestMapping(value="showUserGroupList.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String showUserGroups( String search, @RequestParam("limit") Integer limit, 
			@RequestParam("offset") Integer offset, ModelMap model){
		//页面菜单样式需要
		model.put("pageIndex", 3);
		
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
			
			if(null != cData && null != cData.getId()){
				List<CustomerGroupData> cDatas = new ArrayList<CustomerGroupData> ();
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
	
	@RequestMapping(value="showUserGroupJoinUserId.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String showUserGroupJoinUserId(@RequestParam("userId") Long userId, String search, 
			@RequestParam("limit") Integer limit, 
			@RequestParam("offset") Integer offset, ModelMap model){
		//页面菜单样式需要
		model.put("pageIndex", 3);
		
		BootstrapTableData bData = new BootstrapTableData();
		
		List<CustomerGroupData> cDatas = new ArrayList<CustomerGroupData> ();
		
		if(StringUtils.isEmpty(search)){
			cDatas = customerGroupFacade.queryJoinCustomerIdWithPageIndex(userId, offset, limit);
			
			if(null != cDatas && cDatas.size()>0){
				bData.setRows(cDatas);
				bData.setPage(offset/limit +1);
				//get total
				bData.setTotal(customerGroupFacade.queryCusGroupTotalCount());
			}
		}else{
			CustomerGroupData cData = customerGroupFacade.searchByNameJoinCusIdWithPageIndex(search, userId, offset, limit);
			if(null != cData && null != cData.getId()){
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

	@RequestMapping(value="createNewGroup.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String showUserGroupJoinUserId(@RequestParam("userGroupName") String userGroupName, ModelMap model){
		
		int flag = customerGroupFacade.createNewUserGroup(userGroupName);
		
		APIMessage apiMessage = new APIMessage();
		apiMessage.setStatus(flag);
		
		return JSON.toJSONString(apiMessage);
	}
	
	@RequestMapping(value="showUserGroupDetail.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String showUserGroupDetail(@RequestParam("userGroupId") Integer customerGroupId, ModelMap model){
		
		CustomerGroupData data = customerGroupFacade.showUserGroupDetail(customerGroupId);
		
		return JSON.toJSONString(data);
	}
	
	@RequestMapping(value="updateUserGroup.json",method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updateUserGroup(@RequestParam("userGroupId") Integer userGroupId,
			@RequestParam("userGroupName") String userGroupName, ModelMap model){
		
		int flag = customerGroupFacade.updateUserGroup(userGroupId, userGroupName);
		
		APIMessage apiMessage = new APIMessage();
		apiMessage.setStatus(flag);
		
		return JSON.toJSONString(apiMessage);
	}
	
	@RequestMapping(value="deleteUserGroupById.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String deleteUserGroupById(@RequestParam("customerGroupId") Integer customerGroupId, ModelMap model){
		
		int flag = customerGroupFacade.deleteUserGroupById(customerGroupId);
		
		APIMessage apiMessage = new APIMessage();
		apiMessage.setStatus(flag);
		
		return JSON.toJSONString(apiMessage);
	}
	
	
	@RequestMapping(value="userGroupManage",method=RequestMethod.GET)
	public String userGroupManage(ModelMap model){
		//页面菜单样式需要
		model.put("pageIndex", 3);
		return "userGroupManage";
	}
	
	
	//后台页面上更新\添加用户组资源权限
	@RequestMapping(value="authCusGrpResPermission.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String authCusGrpResPermission( @RequestParam("modelJsonStr") String modelJsonStr ,
			String jointAuthFlag, String startDateStr, String endDateStr, ModelMap model) throws ParseException{
		APIMessage apiMessage = new APIMessage();
		CusGrpResourceRelModel cusGrpResourceRelModel= JSON.parseObject(modelJsonStr, CusGrpResourceRelModel.class);
		
		SimpleDateFormat sf = new SimpleDateFormat("yyy-MM-dd HH:mm");
		
		if(!StringUtils.isEmpty(startDateStr)){
			cusGrpResourceRelModel.setStartDate(sf.parse(startDateStr));
		}
		if(!StringUtils.isEmpty(endDateStr)){
			cusGrpResourceRelModel.setEndDate(sf.parse(endDateStr));
		}
		
		if("on".equals(cusGrpResourceRelModel.getEnable())){
			cusGrpResourceRelModel.setEnable("Y");
		}else{
			cusGrpResourceRelModel.setEnable("N");
		}
		
		cusGrpResourceRelModel.setCreateUser(0);
		
		int flag = 0;
		//联合授权
		if("on".equals(jointAuthFlag)){
			flag = customerGroupFacade.jointAuthCusGrpResPermission(cusGrpResourceRelModel);
		//单个资源授权
		}else{
			flag = customerGroupFacade.authCusGrpResPermission(cusGrpResourceRelModel);
		}
		
		apiMessage.setStatus(flag);
		
		return JSON.toJSONString(apiMessage);
	}
	
	
	//禁止用户组访问资源
	@RequestMapping(value="disableCusGrpResPermission.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String disableCusGrpResPermission( @RequestParam("cusGrpId") Integer cusGrpId , 
			@RequestParam("resourceId") Integer resourceId , ModelMap model){
		
		APIMessage apiMessage = new APIMessage();
		
		int flag = customerGroupFacade.disableCusGrpResPermission(cusGrpId, resourceId);
		apiMessage.setStatus(flag);
		
		return JSON.toJSONString(apiMessage);
	}
	

	
	//后台页面上更新\添加资源组资源权限
	@RequestMapping(value="authResGrpPermission.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String authResGrpPermission( @RequestParam("modelJsonStr") String modelJsonStr ,
			String startDateStr, String endDateStr, ModelMap model) throws ParseException{
		APIMessage apiMessage = new APIMessage();
		CusGroupResGroupRelModel cusGrpResourceRelModel= JSON.parseObject(modelJsonStr, CusGroupResGroupRelModel.class);
		
		SimpleDateFormat sf = new SimpleDateFormat("yyy-MM-dd HH:mm");
		
		if(!StringUtils.isEmpty(startDateStr)){
			cusGrpResourceRelModel.setStartDate(sf.parse(startDateStr));
		}
		if(!StringUtils.isEmpty(endDateStr)){
			cusGrpResourceRelModel.setEndDate(sf.parse(endDateStr));
		}
		
		cusGrpResourceRelModel.setCreateUser(0L);
		
		int flag = 0;
		//联合授权
		flag = customerGroupFacade.jointAuthCusGrpResGrpPermission(cusGrpResourceRelModel);
		
		apiMessage.setStatus(flag);
		
		return JSON.toJSONString(apiMessage);
	}
	

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

}
