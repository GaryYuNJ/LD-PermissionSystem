package com.ldps.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String permissionVerfy(@RequestParam("mobile")String mobile,
			@RequestParam("resourceKey")String mac,
			Model model){
		APIMessage apiMessage = new APIMessage();
		
		//String result = customerFacade.verification(mobile, mac);
		 List<ResourceData> datas = customerFacade.queryPermissionValidResByMobileAndMac(mobile, mac);
		
		if(null != datas && datas.size()>0){
			apiMessage.setStatus(1);
			apiMessage.setMessage("");
			apiMessage.setContent(datas);
		}else{
			apiMessage.setStatus(0);
			apiMessage.setMessage("用户没有权限");
		}
		
		return JSON.toJSONString(apiMessage);
	}
	
	/*
	获取building里的公共资源
	 */
	@RequestMapping(value="/queryPubResByBuildingId",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryPubResByBuildingId(@RequestParam("buildingId")Integer buildingId,
			Model model){
		
		APIMessage apiMessage = new APIMessage();
		List<ResourceData> rDatas = null;
		
		try{
			rDatas = customerFacade.queryPubResWithKeysByBuildingId(buildingId);
			
			if(null== rDatas || rDatas.size() == 0){
				apiMessage.setStatus(0);
				apiMessage.setMessage("没有公共资源数据");
			}else{
				apiMessage.setStatus(1);
				apiMessage.setMessage("");
				apiMessage.setContent(rDatas);
			}
		}catch(Exception e){
			
			apiMessage.setStatus(-1);
			apiMessage.setMessage("系统异常");
		}
		
		return JSON.toJSONString(apiMessage);
	}
	
	/*
	获取building里用户有权限设备(不含公共资源)
	 */
	@RequestMapping(value="/queryPrivateResByBIdAndMobile",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryPubResByBuildingId(@RequestParam("buildingId")Integer buildingId,
			@RequestParam("mobile")String mobile, Model model){
		
		APIMessage apiMessage = new APIMessage();
		List<ResourceData> rDatas = null;
		
		try{
			rDatas = customerFacade.queryPrivateResWithKeysByBIdAndMobile(buildingId, mobile);
			
			if(null== rDatas || rDatas.size() == 0){
				apiMessage.setStatus(0);
				apiMessage.setMessage("没有资源数据");
			}else{
				apiMessage.setStatus(1);
				apiMessage.setMessage("");
				apiMessage.setContent(rDatas);
			}
		}catch(Exception e){
			apiMessage.setStatus(-1);
			apiMessage.setMessage("系统异常");
			e.printStackTrace();
		}
		return JSON.toJSONString(apiMessage);
	}

	//连带资源授权接口(对一个资源授权，需要连带授权上层所有基础资源(要使用授权资源的前提资源)。
	//by mobile、resourceKey
	@RequestMapping(value="/jointAuthResPermissionByMobile",method = { RequestMethod.GET,
			RequestMethod.POST },  produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String jointAuthResPermissionByMobile(@RequestParam("mobile")String mobile,
			@RequestParam("mac")String mac, @RequestParam("startDate")String startDateStr,
			@RequestParam("endDate")String endDateStr, Model model){
		
		APIMessage apiMessage = new APIMessage();
		
		if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(mac)){
			apiMessage.setStatus(-2);
			apiMessage.setMessage("入参mobile、resourceKey为空");
		}else{
			
			SimpleDateFormat df  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				//startDate\endDate为null，表示不限制权限时间
				Date startDate = null;
				Date endDate = null;
				if(!StringUtils.isEmpty(startDateStr)){
					startDate = df.parse(startDateStr);
				}
				if(!StringUtils.isEmpty(endDateStr)){
					endDate = df.parse(endDateStr); 
				}
				int result = 
						customerFacade.jointAuthResPermissionByMobile(mobile, mac, startDate, endDate,0L);
				apiMessage.setStatus(result);
			} catch (ParseException e) {
				apiMessage.setStatus(-2);
				apiMessage.setMessage("日期格式错误");
				e.printStackTrace();
			} catch (Exception e) {
				apiMessage.setStatus(-1);
				apiMessage.setMessage("系统异常");
				e.printStackTrace();
			}
		}
		return JSON.toJSONString(apiMessage);
	}
	

	//连带资源授权接口(对一个资源授权，需要连带授权上层所有基础资源(要使用授权资源的前提资源)。
	//by customerId、resourceId
	@RequestMapping(value="/jointAuthResPermissionByCusId",method = { RequestMethod.GET,
			RequestMethod.POST },  produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String jointAuthResPermissionByCusId(@RequestParam("customerId")Long customerId,
			@RequestParam("resourceId")Integer resourceId, @RequestParam("startDate")String startDateStr,
			@RequestParam("endDate")String endDateStr, Model model){
		
		APIMessage apiMessage = new APIMessage();
		
		if(StringUtils.isEmpty(customerId)||StringUtils.isEmpty(resourceId)){
			apiMessage.setStatus(-2);
			apiMessage.setMessage("入参customerId、resourceId为空");
		}else{
			
			SimpleDateFormat df  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				//startDate\endDate为null，表示不限制权限时间
				Date startDate = null;
				Date endDate = null;
				if(!StringUtils.isEmpty(startDateStr)){
					startDate = df.parse(startDateStr);
				}
				if(!StringUtils.isEmpty(endDateStr)){
					endDate = df.parse(endDateStr); 
				}
				int result = 
						customerFacade.jointAuthResPermissionByCusId(customerId, resourceId, startDate, endDate,0L);
				apiMessage.setStatus(result);
			} catch (ParseException e) {
				apiMessage.setStatus(-2);
				apiMessage.setMessage("日期格式错误");
				e.printStackTrace();
			} catch (Exception e) {
				apiMessage.setStatus(-1);
				apiMessage.setMessage("系统异常");
				e.printStackTrace();
			}
		}
		return JSON.toJSONString(apiMessage);
	}
	
	
	//单个资源授权接口
	//by mobile、resourceKey
	@RequestMapping(value="/authResPermissionByMobile",method = { RequestMethod.GET,
			RequestMethod.POST },  produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String authResPermissionByMobile(@RequestParam("mobile")String mobile,
			@RequestParam("resourceKey")String resourceKey, @RequestParam("startDate")String startDateStr,
			@RequestParam("endDate")String endDateStr, Model model){
		
		APIMessage apiMessage = new APIMessage();
		
		if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(resourceKey)){
			apiMessage.setStatus(-2);
			apiMessage.setMessage("入参mobile、resourceKey为空");
		}else{
			
			SimpleDateFormat df  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				//startDate\endDate为null，表示不限制权限时间
				Date startDate = null;
				Date endDate = null;
				if(!StringUtils.isEmpty(startDateStr)){
					startDate = df.parse(startDateStr);
				}
				if(!StringUtils.isEmpty(endDateStr)){
					endDate = df.parse(endDateStr); 
				}
				int result = 
						customerFacade.authResPermissionByMobile(mobile, resourceKey, startDate, endDate,0L);
				apiMessage.setStatus(result);
			} catch (ParseException e) {
				apiMessage.setStatus(-2);
				apiMessage.setMessage("日期格式错误");
				e.printStackTrace();
			} catch (Exception e) {
				apiMessage.setStatus(-1);
				apiMessage.setMessage("系统异常");
				e.printStackTrace();
			}
		}
		return JSON.toJSONString(apiMessage);
	}
	
	//单个资源授权接口.by customerId、resourceId
	@RequestMapping(value="/authResPermissionByCusId",method = { RequestMethod.GET,
			RequestMethod.POST },  produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String authResPermissionByCusId(@RequestParam("customerId")Long customerId,
			@RequestParam("resourceId")Integer resourceId,  @RequestParam("startDate")String startDateStr,
			@RequestParam("endDate")String endDateStr, Model model){
		
		APIMessage apiMessage = new APIMessage();
		
		if(StringUtils.isEmpty(customerId)||StringUtils.isEmpty(resourceId)){
			apiMessage.setStatus(-2);
			apiMessage.setMessage("入参customerId、resourceId为空");
		}else{
			
			SimpleDateFormat df  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				//startDate\endDate为null，表示不限制权限时间
				Date startDate = null;
				Date endDate = null;
				if(!StringUtils.isEmpty(startDateStr)){
					startDate = df.parse(startDateStr);
				}
				if(!StringUtils.isEmpty(endDateStr)){
					endDate = df.parse(endDateStr); 
				}
				int result = 
						customerFacade.authResPermissionByCusId(customerId, resourceId, startDate, endDate,0L);
				apiMessage.setStatus(result);
			} catch (ParseException e) {
				apiMessage.setStatus(-2);
				apiMessage.setMessage("日期格式错误");
				e.printStackTrace();
			} catch (Exception e) {
				apiMessage.setStatus(-1);
				apiMessage.setMessage("系统异常");
				e.printStackTrace();
			}
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
			RequestMethod.POST },produces = "application/json; charset=utf-8")
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
			RequestMethod.POST },produces = "application/json; charset=utf-8")
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
