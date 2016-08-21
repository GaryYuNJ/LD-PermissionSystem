package com.ldps.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "manage")
public class ResourceControl {
	
	@RequestMapping(value="resouceManage",method=RequestMethod.GET)
	public String resouceManage(ModelMap model){
		//页面菜单样式需要
		model.put("pageIndex", 2);
		return "resourceManage";
	}


}
