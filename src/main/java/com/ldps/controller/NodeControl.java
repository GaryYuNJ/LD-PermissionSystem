package com.ldps.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ldps.service.INodeService;

@Controller
@RequestMapping(value = "manage")
public class NodeControl {
	
	@Resource
	private INodeService iNodeService;
	
	@ResponseBody
	@RequestMapping(value="showNode.json",method=RequestMethod.GET)
	public String resouceManage(ModelMap model){
		return JSON.toJSON(iNodeService.getAllNodeTree()).toString();
	}
}
