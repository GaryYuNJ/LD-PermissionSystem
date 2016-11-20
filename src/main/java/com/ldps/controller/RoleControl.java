package com.ldps.controller;

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
import com.ldps.data.BootstrapTableData;
import com.ldps.model.Role;
import com.ldps.service.IRoleService;

@Controller
@RequestMapping(value = "manage")
public class RoleControl {
	
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
	public String resourceSearch(@RequestParam("search")String roleName,
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

}
