package com.ldps.controller;

import java.net.URLEncoder;
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
import com.alibaba.fastjson.JSONObject;
import com.ldps.data.APIMessage;
import com.ldps.data.CusResourceRelData;
import com.ldps.data.ResourceArea;
import com.ldps.data.ResourceData;
import com.ldps.facade.CustomerFacade;
import com.ldps.model.BuildingModel;
import com.ldps.model.MessageRecord;
import com.ldps.service.IBuildingModelService;
import com.ldps.service.ICustomerService;
import com.ldps.service.IMessageService;
import com.ldps.service.IResourceService;
import com.ldps.tools.MessageTool;

@Controller
@RequestMapping(value = "appApi")
public class CustomerAPPAPIController {
	private static Logger logger = Logger
			.getLogger(CustomerAPPAPIController.class);

	@Resource
	private IMessageService iMessageService;
	
	@Resource
	private CustomerFacade customerFacade;
	@Resource
	private ICustomerService iCustomerSevice;
	@Resource
	private IResourceService iResourceService;
	@Resource(name = "iBuildingModelService")
	private IBuildingModelService iBuildingModelService;
	
	@RequestMapping(value="/permissionVerfy",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String permissionVerfy(@RequestParam("mobile")String mobile,
			@RequestParam("resourceKey")String mac,
			Model model){
		APIMessage apiMessage = new APIMessage();
		
		//String result = customerFacade.verification(mobile, mac);
		 ResourceData data = customerFacade.queryPermissionValidResByMobileAndMac(mobile, mac);
		
		if(null != data ){
			apiMessage.setStatus(1);
			apiMessage.setMessage("");
			apiMessage.setContent(data);
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
	public String queryPrivateResByBIdAndMobile(@RequestParam("buildingId")Integer buildingId,
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
	
	/*
	获取building里用户所有有权限设备(含私有、公共资源)
	 */
	@RequestMapping(value="/queryAvaiableResByBIdAndMobile",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryAvaiableResByBIdAndMobile(@RequestParam("buildingId")Integer buildingId,
			@RequestParam("mobile")String mobile, Model model){
		APIMessage apiMessage = new APIMessage();
		List<ResourceData> rDatas = null;
		
		try{
			//楼栋不传的话查询所有的共有线索
			rDatas = customerFacade.queryPubResWithKeysByBuildingId(buildingId);
			
			if(null != rDatas && rDatas.size() > 0){
				List<ResourceData> privateDatas = customerFacade.queryPrivateResWithKeysByBIdAndMobile(buildingId, mobile);
				
				if(null != privateDatas && privateDatas.size() > 0){
					rDatas.addAll(privateDatas);
				}
			}else{
				rDatas = customerFacade.queryPrivateResWithKeysByBIdAndMobile(buildingId, mobile);
			}
			
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
			@RequestParam("resourceKey")String mac, @RequestParam("startDate")String startDateStr,
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
				Long customerId = iCustomerSevice.getCustomerIdByMobile(mobile);
				Integer resourceId = iResourceService.queryResourceIdByMAC(mac);
				if(null == customerId){
					apiMessage.setStatus(-4);
					apiMessage.setMessage("用户不存在");
				}else{
					if(null == resourceId){
						apiMessage.setStatus(-5);
						apiMessage.setMessage("资源不存在");
					}else{
						int result = 
								customerFacade.jointAuthResPermissionWithCreateUserId(customerId, resourceId, startDate, endDate,0L);
						apiMessage.setStatus(result);
					}
				}
				
			} catch (ParseException e) {
				apiMessage.setStatus(-3);
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
						customerFacade.jointAuthResPermissionByCusId(customerId, resourceId, startDate, endDate);
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
						customerFacade.authResPermissionByCusId(customerId, resourceId, startDate, endDate);
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
	
	//通过手机号码获取楼栋和层级
	@RequestMapping(value="/querySharableResBuilding",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String querySharableResBuilding(@RequestParam("mobile")String mobile){
		APIMessage apiMessage = new APIMessage();
		apiMessage.setStatus(-1);
		if(StringUtils.isEmpty(mobile)){
			apiMessage.setStatus(0);
			apiMessage.setMessage("手机号码不能为空");
		}
		List<ResourceArea> resourceDatas = customerFacade.querySharableResourceArea(mobile);
		if(null==resourceDatas||resourceDatas.size()==0){
			apiMessage.setStatus(0);
			apiMessage.setMessage("没有可授权数据");
		}else{
			apiMessage.setStatus(1);
			apiMessage.setContent(resourceDatas);
		}
		return JSON.toJSONString(apiMessage);
	}
	
	//分享权限
	@RequestMapping(value="/permissionShare",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String permissionShare(@RequestParam("fromMobile")String fromMobile,
			@RequestParam("toMobile")String toMobile,
			@RequestParam("toName")String toName,
			@RequestParam("startDate")String startDate,
			@RequestParam("endDate")String endDate,
			@RequestParam("buildingId")String buildingId,
			@RequestParam("floor")String floor){
		APIMessage apiMessage = new APIMessage();
		apiMessage.setStatus(-1);
		if(StringUtils.isEmpty(fromMobile)||StringUtils.isEmpty(toMobile)){
			apiMessage.setMessage("被授权人和分享人不能为空");
		}
		
		int i=customerFacade.permissionShare(fromMobile, toMobile, toName, startDate, endDate, buildingId, floor);
		
		if(i==-1){
			apiMessage.setMessage("数据格式错误");
		}else if(i==-3){
			apiMessage.setMessage("分享用户不存在");
		}else if(i==-4){
			apiMessage.setMessage("系统异常，请联系管理员");
		}else if(i==3){
			apiMessage.setMessage("被授权人手机号码不正确");
		}else{
			apiMessage.setStatus(1);
			apiMessage.setMessage("授权成功");
			//发送短信
			/*尾号xxxx访客您好，尾号XXXX邀请您访问南京绿地之窗C-5栋14层，可通过下载荟生活APP并注册会员，一键通行智能门禁，更多智慧办公体验，请点击链接·····
			尾号xxxx用户您好，您已获得南京绿地之窗C-5栋14层智能门禁权限，点击“open”开启智慧办公之旅！荟生活APP，从工作到生活，您的一站式服务管家！下载链接。。。
			*/
			String buildString="";
			if(!StringUtils.isEmpty(buildingId)){
				int buildingIdInteger =new Integer(buildingId);
				BuildingModel buildingModel=iBuildingModelService.queryBuilding(buildingIdInteger);
				buildString+=buildingModel.getName();
				if(!StringUtils.isEmpty(floor)){
					buildString+=buildingModel.getName()+floor+"层";
				}
			}
			String message="";
			if(i==2){//新用户
				message="尾号"+toMobile.substring(7, 11)+"访客您好，尾号"+fromMobile.substring(7, 11)+"邀请您访问南京"+buildString+"，可通过下载荟生活APP并注册会员，一键通行智能门禁，更多智慧办公体验，请点击链接http://dwz.cn/3OJRnK";
			}else{//老用户
				message="尾号"+toMobile.substring(7, 11)+"用户您好，您已获得南京"+buildString+"智能门禁权限，点击“open”开启智慧办公之旅！荟生活APP，从工作到生活，您的一站式服务管家！下载链接http://dwz.cn/3OJRnK";
			}
			iMessageService.sendMessage(message, toMobile);
		}
		//调用Service
		return JSON.toJSONString(apiMessage);
	}
	
	/*
	获取building里用户所有有权限设备(含私有、公共资源)
	 */
	@RequestMapping(value="/queryAvaiableResByMobile",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryAvaiableResByMobile(@RequestParam("buildingId")Integer buildingId,
			@RequestParam("mobile")String mobile, Model model){
		
		APIMessage apiMessage = new APIMessage();
		try{
			List<BuildingModel> buildingModels =new ArrayList<BuildingModel>();
			//楼栋不传的话查询所有的共有线索
			if(StringUtils.isEmpty(buildingId)){
				buildingId=null;
				buildingModels=iBuildingModelService.queryAll();
				//rDatas = customerFacade.queryPubResWithKeys();
			}else{
				BuildingModel tempBuilding=iBuildingModelService.queryBuilding(new Integer(buildingId));
				if(null!=tempBuilding){
					buildingModels.add(tempBuilding);
				}
			}

			apiMessage.setStatus(0);
			apiMessage.setMessage("没有资源数据");
			
			for(int i=0;i<buildingModels.size();i++){
				List<ResourceData> rDatas = null;
				rDatas = customerFacade.queryPubResWithKeysByBuildingId(buildingModels.get(i).getId());
				if(null != rDatas && rDatas.size() > 0){
					//获取有权限的私有资源
					List<ResourceData> privateDatas = customerFacade.queryPrivateResWithKeysByBIdAndMobile(buildingModels.get(i).getId(), mobile);
					
					if(null != privateDatas && privateDatas.size() > 0){
						rDatas.addAll(privateDatas);
					}
				}else{
					rDatas = customerFacade.queryPrivateResWithKeysByBIdAndMobile(buildingModels.get(i).getId(), mobile);
				}
				
				if(null!= rDatas&&rDatas.size() > 0){
					apiMessage.setStatus(1);
					apiMessage.setMessage("");
					//apiMessage.setContent(rDatas);
					buildingModels.get(i).setResourceDatas(rDatas);
				}else{
					buildingModels.remove(i);
					--i;
				}
			}
			if(apiMessage.getStatus()==1){
				apiMessage.setContent(buildingModels);
			}
		}catch(Exception e){
			apiMessage.setStatus(-1);
			apiMessage.setMessage("系统异常");
			e.printStackTrace();
		}
		return JSON.toJSONString(apiMessage);
	}
	/*
	获取building里用户所有有权限设备(含私有、公共资源)
	 */
	@RequestMapping(value="/checkMobile",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String checkMobile(@RequestParam("mobile")String mobile, Model model){
		
		APIMessage apiMessage = new APIMessage();
		try{
			if(iCustomerSevice.queryTotalCountByMobileAndName(mobile, null)>0){
				apiMessage.setStatus(1);
				apiMessage.setMessage("已注册");
			}else{
				apiMessage.setStatus(0);
				apiMessage.setMessage("未注册");
			}
		}catch(Exception e){
			apiMessage.setStatus(-1);
			apiMessage.setMessage("系统异常");
			e.printStackTrace();
		}
		return JSON.toJSONString(apiMessage);
	}
	
}


