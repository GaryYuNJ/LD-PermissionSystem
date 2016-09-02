package com.ldps.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ldps.data.APIMessage;
import com.ldps.data.BootstrapTableData;
import com.ldps.model.CustomerModel;
import com.ldps.model.ResourceModel;
import com.ldps.service.IBuildingModelService;
import com.ldps.service.IResourceService;

@Controller
@RequestMapping(value = "manage")
public class ResourceControl {
	@Resource(name = "iResourceService")
	private IResourceService iResourceService;
	@Resource(name = "iBuildingModelService")
	private IBuildingModelService iBuildingModelService;

	@RequestMapping(value = "resourceManagePage", method = RequestMethod.GET)
	public String resouceManage(ModelMap model) {
		// 页面菜单样式需要
		model.put("pageIndex", 1);
		return "resourceManage";
	}

	@ResponseBody
	@RequestMapping(value = "getResourceById.json")
	public String getResourceById(@RequestParam("id") Integer id) {
		return JSON.toJSONString(iResourceService.queryModelById(id));
	}

	@ResponseBody
	@RequestMapping(value = "allBuildings.json")
	public String getBuildings() {
		return JSON.toJSONString(iBuildingModelService.queryAll());
	}

	@ResponseBody
	@RequestMapping(value = "delResource.json")
	public String deleteResource(@RequestParam("resourceId") Integer resourceId) {
		APIMessage am = new APIMessage();
		am.setStatus(1);
		if (iResourceService.deleteResource(resourceId) > 0) {
			am.setStatus(0);
		}
		return JSON.toJSONString(am);
	}

	@ResponseBody
	@RequestMapping(value = "updateResource.json")
	public String updateResource(@ModelAttribute ResourceModel resourceModel) {
		APIMessage am = new APIMessage();
		am.setStatus(1);
		if (iResourceService.updateResource(resourceModel) > 0) {
			am.setStatus(0);
		}
		return JSON.toJSONString(am);
	}

	@ResponseBody
	@RequestMapping(value = "addResource.json")
	public String addResource(
			@RequestParam("resourceModelJson") String resourceModelJson) {
		ResourceModel resourceModel = JSON.parseObject(resourceModelJson,
				ResourceModel.class);
		APIMessage am = new APIMessage();
		am.setStatus(1);
		if (StringUtils.isEmpty(resourceModel.getName())) {
			am.setMessage("name is null");
		}
		// type字段暂时没用， 所以直接这只值为1
		if (StringUtils.isEmpty(resourceModel.getTypeId())) {
			resourceModel.setTypeId(1);
		}
		// 没选择节点， 默认为根节点
		if (StringUtils.isEmpty(resourceModel.getNodeId())) {
			resourceModel.setNodeId(1);
			resourceModel.setNodePath("根节点");
			;
		}
		// 没选择节点， 默认为根节点
		if (StringUtils.isEmpty(resourceModel.getCreateUser())) {
			resourceModel.setCreateUser(1000);
		}

		if (StringUtils.isEmpty(resourceModel.getStatus())
				|| resourceModel.getStatus().equals("on")) {
			resourceModel.setStatus("Y");
		} else {
			resourceModel.setStatus("N");
		}

		if (StringUtils.isEmpty(resourceModel.getShareEnable())
				|| resourceModel.getShareEnable().equals("on")) {
			resourceModel.setShareEnable("Y");
		} else {
			resourceModel.setShareEnable("N");
		}

		if (iResourceService.createResource(resourceModel) == 1) {
			am.setStatus(0);
		}
		return JSON.toJSONString(am);
	}

	@ResponseBody
	@RequestMapping(value = "resourceSearch.json")
	public String resourceSearch(String search,
			@RequestParam("limit") Integer limit,
			@RequestParam("offset") Integer offset) {

		ResourceModel resourceModel = JSON.parseObject(search,
				ResourceModel.class);
		BootstrapTableData bData = new BootstrapTableData();

		List<ResourceModel> resourceList = iResourceService
				.queryBasicResByCondition(resourceModel, offset, limit);
		if (null != resourceList && resourceList.size() > 0) {
			bData.setRows(resourceList);
			bData.setPage(offset / limit + 1);
			bData.setTotal(iResourceService
					.queryCountByCondition(resourceModel));
		} else {
			bData.setTotal(0);
		}
		return JSON.toJSONString(bData);
	}

	// 关联用户查询资源，返回用户是否有权限标识
	@ResponseBody
	@RequestMapping(value = "resourceSearchWithCusId.json")
	public String resourceSearchWithCusId(String search,
			@RequestParam("limit") Integer limit,
			@RequestParam("offset") Integer offset) {

		ResourceModel resourceModel = JSON.parseObject(search,
				ResourceModel.class);
		BootstrapTableData bData = new BootstrapTableData();

		List<ResourceModel> resourceList = iResourceService
				.queryResByConditionWithCusId(resourceModel, offset, limit);
		if (null != resourceList && resourceList.size() > 0) {
			bData.setRows(resourceList);
			bData.setPage(offset / limit + 1);
			bData.setTotal(iResourceService
					.queryCountByCondition(resourceModel));
		} else {
			bData.setTotal(0);
		}
		return JSON.toJSONString(bData);
	}

	// 关联用户组查询资源，返回用户组是否有权限标识
	@ResponseBody
	@RequestMapping(value = "resSearchWithCusGroupId.json")
	public String resSearchWithCusGroupId(String search,
			@RequestParam("limit") Integer limit,
			@RequestParam("offset") Integer offset) {

		ResourceModel resourceModel = JSON.parseObject(search,
				ResourceModel.class);
		BootstrapTableData bData = new BootstrapTableData();

		List<ResourceModel> resourceList = iResourceService
				.queryResByConditionWithCusGroupId(resourceModel, offset, limit);
		if (null != resourceList && resourceList.size() > 0) {
			bData.setRows(resourceList);
			bData.setPage(offset / limit + 1);
			bData.setTotal(iResourceService
					.queryCountByCondition(resourceModel));
		} else {
			bData.setTotal(0);
		}
		return JSON.toJSONString(bData);
	}

	public IResourceService getiResourceService() {
		return iResourceService;
	}

	public void setiResourceService(IResourceService iResourceService) {
		this.iResourceService = iResourceService;
	}

	public IBuildingModelService getiBuildingModelService() {
		return iBuildingModelService;
	}

	public void setiBuildingModelService(
			IBuildingModelService iBuildingModelService) {
		this.iBuildingModelService = iBuildingModelService;
	}

}
