package com.ldps.controller;

import java.util.List;

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
import com.alibaba.fastjson.JSONException;
import com.ldps.data.APIMessage;
import com.ldps.data.CusResourceRelData;
import com.ldps.data.ResourceData;
import com.ldps.facade.CustomerFacade;

@Controller
@RequestMapping(value = "appApi")
public class CustomerAPPAPIController {
	private static Logger logger = Logger
			.getLogger(CustomerAPPAPIController.class);
	@Resource
	private CustomerFacade customerFacade;

	@RequestMapping(value="/permissionVerfy",method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String permissionVerfy(@RequestParam("cId")String cId,
			@RequestParam("resourceKeyId")String resourceKeyId,
			Model model){
		APIMessage apiMessage = new APIMessage();
		
		String result = customerFacade.verification(cId, resourceKeyId);
		
		if("0".equals(result)){
			apiMessage.setStatus(1);
			apiMessage.setMessage("");
		}else{
			apiMessage.setStatus(0);
			apiMessage.setMessage(result);
		}
		
		return JSON.toJSONString(apiMessage);
	}
	
	//查询用户可分享使用权限的资源
	@RequestMapping(value="/querySharableResource",method = { RequestMethod.GET,
			RequestMethod.POST },  produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String querySharableResource(@RequestParam("cId")String cId,
			Model model){
		List<ResourceData> resourceDatas = customerFacade.querySharableResource(cId);
		return JSON.toJSONString(resourceDatas);
	}
	
	//查询用户已分享的资源数据
	@RequestMapping(value="/queryResByShareCId",method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String queryResByShareCId(@RequestParam("sharedFromUserId")String sharedFromUserId,
			Model model){
		List<CusResourceRelData> cusResourceRelData = customerFacade.queryResourceRelByShareCustomerId(sharedFromUserId);
		return JSON.toJSONString(cusResourceRelData);
	}
	
	//用户分享资源给其他人的权限
	@RequestMapping(value="/shareResource",method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String shareResource(@RequestParam("fromCId")String fromCId,
			@RequestParam("toCId")String toCId, @RequestParam("sourceKeyId")Integer sourceKeyId,
			@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate,
			Model model){

		String result = customerFacade.shareResource(fromCId,toCId,sourceKeyId,startDate,endDate);
		APIMessage apiMessage = new APIMessage();
		if("0".equals(result)){
			apiMessage.setStatus(1);
			apiMessage.setMessage("");
		}else{
			apiMessage.setStatus(0);
			apiMessage.setMessage(result);
		}
		
		return JSON.toJSONString(apiMessage);
		
	}
	
	//用户删除分享给其他人的权限
	@RequestMapping(value="/removeSharedResource",method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String removeSharedResource(@RequestParam("fromCId")String fromCId,
			@RequestParam("toCId")String toCId,@RequestParam("sourceKeyId")Integer sourceKeyId,
			Model model){
		String result = customerFacade.removeSharedResource(fromCId,toCId,sourceKeyId);
		
		APIMessage apiMessage = new APIMessage();
		if("0".equals(result)){
			apiMessage.setStatus(1);
			apiMessage.setMessage("");
		}else{
			apiMessage.setStatus(0);
			apiMessage.setMessage(result);
		}
		return JSON.toJSONString(apiMessage);
	}
}
