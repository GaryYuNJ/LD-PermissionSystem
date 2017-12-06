package com.ldps.controller;
import java.util.HashMap;
import java.util.Map;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ldps.data.APIMessage;

@Controller
@RequestMapping(value = "style")
public class CustomerDemoController {


	@RequestMapping(value="userDemo.json", method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String userDemo( @RequestParam("mobile") String mobile,@RequestParam("token") String token){
		APIMessage apiMessage = new APIMessage();
		
		if(!"dtue82xiw92187ctn".equals(token)){
			
			apiMessage.setStatus(2);
			apiMessage.setContent("token不匹配");
			
		}else{
			apiMessage.setStatus(0);
			Map<String,String> userMap = new HashMap<String,String>();
			userMap.put("mobile", mobile);
			userMap.put("name", "测试");
			userMap.put("birthday", "2010-10-10");
			apiMessage.setContent(userMap);
		}
		
		return JSON.toJSONString(apiMessage);
	}

}
