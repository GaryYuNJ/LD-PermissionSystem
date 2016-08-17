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
			@RequestParam("resourceKey")String resourceKey,
			Model model){
		APIMessage apiMessage = new APIMessage();
		
		String result = customerFacade.verification(cId, resourceKey);
		
		if("0".equals(result)){
			apiMessage.setStatus(1);
			apiMessage.setMessage("");
		}else{
			apiMessage.setStatus(0);
			apiMessage.setMessage(result);
		}
		
		return JSON.toJSONString(apiMessage);
	}
	
	@RequestMapping(value="/querySharableResource",method = { RequestMethod.GET,
			RequestMethod.POST },  produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String querySharableResource(@RequestParam("cId")String cId,
			Model model){
		List<ResourceData> resourceDatas = customerFacade.querySharableResource(cId);
		return JSON.toJSONString(resourceDatas);
	}
	
	@RequestMapping(value="/queryResByShareCId",method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String queryResByShareCId(@RequestParam("cId")String cId,
			Model model){
		List<CusResourceRelData> cusResourceRelData = customerFacade.queryResourceRelByShareCustomerId(cId);
		return JSON.toJSONString(cusResourceRelData);
	}
}
