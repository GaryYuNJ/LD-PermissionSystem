package com.ldps.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ldps.data.BootstrapTableData;
import com.ldps.model.ResourceModel;

@Controller
@RequestMapping(value = "manage")
public class RoleControl {
	
	@Resource(name = "roleService")
	private IRoleService roleService;
	
	@RequestMapping(value = "roleManagePage", method = RequestMethod.GET)
	public String roleManage(ModelMap model) {
		// 页面菜单样式需要
		model.put("pageIndex", 4);
		return "roleManage";
	}
	
	@ResponseBody
	@RequestMapping(value = "roleSearch.json")
	public String resourceSearch(String roleName,
			@RequestParam("limit") Integer limit,
			@RequestParam("offset") Integer offset) {

		BootstrapTableData bData = new BootstrapTableData();

		List<ResourceModel> resourceList = iResourceService
				.queryBasicResByCondition(roleName, offset, limit);
		if (null != resourceList && resourceList.size() > 0) {
			bData.setRows(resourceList);
			bData.setPage(offset / limit + 1);
			bData.setTotal(iResourceService
					.queryCountByCondition(resourceModel));
		} else {
			bData.setPage(0);
			bData.setRows(new Object());
			bData.setTotal(0);
		}
		return JSON.toJSONString(bData);
	}

}
