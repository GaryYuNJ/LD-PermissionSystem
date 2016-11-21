package com.ldps.controller;

import java.util.Date;
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
import com.ldps.model.Role;
import com.ldps.service.IRoleService;
import com.ldps.service.IUserService;

@Controller
@RequestMapping(value = "manage")
public class RoleControl {
	
	@Resource
	private IUserService userService;
	
	@Resource(name = "iRoleService")
	private IRoleService roleService;
	
	@RequestMapping(value = "roleManagePage", method = RequestMethod.GET)
	public String roleManage(ModelMap model) {
		// 页面菜单样式需要
		model.put("pageIndex", 4);
		return "roleManage";
	}
	
	@ResponseBody
	@RequestMapping(value = "roleSearch.json")
	public String roleSearch(@RequestParam("search")String roleName,
			@RequestParam("limit") Integer limit,
			@RequestParam("offset") Integer offset) {
		if(StringUtils.isEmpty(roleName))
			roleName=null;
		BootstrapTableData bData = new BootstrapTableData();

		List<Role> roleList = roleService.queryRoleWithPageIndex(roleName, offset, limit);
		if (null != roleList && roleList.size() > 0) {
			bData.setRows(roleList);
			bData.setPage(offset / limit + 1);
			bData.setTotal(roleService
					.queryCountByCondition(roleName));
		} else {
			bData.setPage(0);
			bData.setRows(new Object());
			bData.setTotal(0);
		}
		return JSON.toJSONString(bData);
	}

	@ResponseBody
	@RequestMapping(value = "saveRole.json")
	public String saveRole(@RequestParam("name")String name,
			@RequestParam("roleId") Long roleId,
			@RequestParam("roleBuildings") String roleBuildings) {
		APIMessage apiMessage=new APIMessage();
		apiMessage.setStatus(-1);
		Role role=new Role();
		if(!StringUtils.isEmpty(name)){
			role.setName(name);
			role.setCreateUser(userService.getSessionUserId());
			role.setCreateDate(new Date());
			role.setStatus("Y");
		}else{
			return JSON.toJSONString(apiMessage);
		}
		if(null!=roleId&&roleId>0){
			role.setId(roleId);
		}
		//if()
		apiMessage.setStatus(roleService.saveOrUpdate(role));
		
		return JSON.toJSONString(apiMessage);
	}
	
	@ResponseBody
	@RequestMapping(value = "deleteRole.json")
	public String deleteRole(@RequestParam("roleId") Long roleId) {
		APIMessage apiMessage=new APIMessage();
		apiMessage.setStatus(-1);
		if(null!=roleId)
		apiMessage.setStatus(roleService.deleteById(roleId));
		return JSON.toJSONString(apiMessage);
	}
}
