package com.ldps.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ldps.data.APIMessage;
import com.ldps.model.NodeModel;
import com.ldps.service.INodeService;

@Controller
@RequestMapping(value = "manage")
public class NodeControl {
	
	@Resource
	private INodeService iNodeService;
	
	@ResponseBody
	@RequestMapping(value="showNode.json",method=RequestMethod.GET)
	public String showNode(ModelMap model){
		return JSON.toJSON(iNodeService.getAllNodeTree()).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="addorUpdateNode.json")
	public String addorUpdateNode(@ModelAttribute NodeModel nodeModel){
		APIMessage am=new APIMessage();
		am.setStatus(1);
		Integer i=iNodeService.addNode(nodeModel);
		if(i>0){
			am.setStatus(0);
			am.setMessage(nodeModel.getId().toString());
		}
		//返回ID
		return JSON.toJSON(am).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="updateNode.json")
	public String updateNode(@ModelAttribute NodeModel nodeModel){
		APIMessage am=new APIMessage();
		am.setStatus(1);
		int i=iNodeService.updateNode(nodeModel);
		if(i>0){
			am.setStatus(0);
		}
		return JSON.toJSON(am).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="deleteNode.json")
	public String deleteNode(@RequestParam("nodeId") Integer nodeId){
		APIMessage am=new APIMessage();
		am.setStatus(1);
		int i=iNodeService.deleteNode(nodeId);
		if(i>0){
			am.setStatus(0);
		}
		return JSON.toJSON(am).toString();
	}
}
