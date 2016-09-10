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
import com.ldps.model.ResourceGroupModel;
import com.ldps.model.ResourceGrpRelModel;
import com.ldps.service.IResourceGroupService;

@Controller
@RequestMapping(value = "manage")
public class ResourceGroupController {
	
	@Resource(name = "iResourceGroupService")
	private IResourceGroupService iResourceGroupService;
	
	
	@RequestMapping(value = "resourceGroupManagePage", method = RequestMethod.GET)
	public String resouceManage(ModelMap model) {
		// 页面菜单样式需要
		model.put("pageIndex", 2);
		return "resourceGroupManage";
	}
	
	//资源组查询
	@ResponseBody
	@RequestMapping(value = "resourceGroupSearch.json")
	public String resourceGroupSearch(String search,
			@RequestParam("limit") Integer limit,
			@RequestParam("offset") Integer offset) {

		ResourceGroupModel resourceGroupModel = JSON.parseObject(search,
				ResourceGroupModel.class);
		BootstrapTableData bData = new BootstrapTableData();

		List<ResourceGroupModel> resourceList = iResourceGroupService
				.queryBasicResGroupByCondition(resourceGroupModel, offset, limit);
		if (null != resourceList && resourceList.size() > 0) {
			bData.setRows(resourceList);
			bData.setPage(offset / limit + 1);
			bData.setTotal(iResourceGroupService
					.queryCountByCondition(resourceGroupModel));
		} else {
			bData.setTotal(0);
		}
		if(null == bData.getRows()){
			bData.setPage(0);
			bData.setRows(new Object());
			bData.setTotal(0);
		}
		return JSON.toJSONString(bData);
	}
	
	//添加资源组
	@ResponseBody
	@RequestMapping(value = "addResourceGroup.json")
	public String addResourceGroup(@RequestParam("resourceGroupName") String resourceGroupName) {
		APIMessage am = new APIMessage();
		am.setStatus(0);
		if(StringUtils.isEmpty(resourceGroupName))
			return JSON.toJSONString(am);
		ResourceGroupModel model=new ResourceGroupModel(); 
		model.setName(resourceGroupName);
		model.setCreateDate(new Date());
		model.setCreateUser(1);
		if (iResourceGroupService.addNewGroup(model) > 0) {
			am.setStatus(1);
		}
		return JSON.toJSONString(am);
	}
	
	//查看
	@ResponseBody
	@RequestMapping(value = "showResourceGroup.json")
	public String showResourceGroup(@RequestParam("resourceGroupId") Integer resourceGroupId) {
		return JSON.toJSONString(iResourceGroupService.queryReourceGroupModelById(resourceGroupId));
	}
		
	//查看
	@ResponseBody
	@RequestMapping(value = "updateResourceGroup.json")
	public String updateResourceGroup(@RequestParam("resourceGroupId") Integer resourceGroupId,@RequestParam("resourceGroupName") String resourceGroupName) {
		APIMessage am = new APIMessage();
		am.setStatus(0);
		if(StringUtils.isEmpty(resourceGroupName))
			return JSON.toJSONString(am);
		ResourceGroupModel model=new ResourceGroupModel(); 
		model.setId(resourceGroupId);
		model.setName(resourceGroupName);
		model.setCreateDate(new Date());
		model.setCreateUser(1);
		if (iResourceGroupService.updateResourceGroup(model) > 0) {
			am.setStatus(1);
		}
		return JSON.toJSONString(am);
	}
	
	@ResponseBody
	@RequestMapping(value = "deleteResourceGroupRel.json")
	public String deleteResourceGroupRel(@RequestParam("resourceId") Integer resourceId,@RequestParam("resourceGroupId") Integer resourceGroupId) {
		APIMessage am = new APIMessage();
		am.setStatus(0);
		if(resourceId==null||resourceGroupId==null)
			return JSON.toJSONString(am);
		ResourceGrpRelModel rgm=new ResourceGrpRelModel();
		rgm.setResourceId(resourceId);
		rgm.setRgroupId(resourceGroupId);
		if (iResourceGroupService.deleteResourceGroupRel(rgm) > 0) {
			am.setStatus(1);
		}
		return JSON.toJSONString(am);
	}
	@ResponseBody
	@RequestMapping(value = "addResourceGroupRel.json")
	public String addResourceGroupRel(@RequestParam("resourceId") Integer resourceId,@RequestParam("resourceGroupId") Integer resourceGroupId) {
		APIMessage am = new APIMessage();
		am.setStatus(0);
		if(resourceId==null||resourceGroupId==null)
			return JSON.toJSONString(am);
		
		ResourceGrpRelModel rgm=new ResourceGrpRelModel();
		rgm.setCreateDate(new Date());
		rgm.setCreateUser(1);
		rgm.setResourceId(resourceId);
		rgm.setRgroupId(resourceGroupId);
		if (iResourceGroupService.addResourceGroupRel(rgm) > 0) {
			am.setStatus(1);
		}
		return JSON.toJSONString(am);
	}
	
	//资源组查询 with customerId
	@ResponseBody
	@RequestMapping(value = "resGroupSearchWithCusId.json")
	public String resGroupSearchWithCusId(String search,
			@RequestParam("limit") Integer limit,
			@RequestParam("offset") Integer offset) {

		ResourceGroupModel resourceGroupModel = JSON.parseObject(search,
				ResourceGroupModel.class);
		BootstrapTableData bData = new BootstrapTableData();

		List<ResourceGroupModel> resourceList = iResourceGroupService
				.queryBasicResGroupWithCusId(resourceGroupModel, offset, limit);
		
		if (null != resourceList && resourceList.size() > 0) {
			bData.setRows(resourceList);
			bData.setPage(offset / limit + 1);
			bData.setTotal(iResourceGroupService
					.queryCountByCondition(resourceGroupModel));
		} else {
			bData.setTotal(0);
		}
		if(null == bData.getRows()){
			bData.setPage(0);
			bData.setRows(new Object());
			bData.setTotal(0);
		}
		return JSON.toJSONString(bData);
	}
	
	//资源组查询 with customerGroupId
	@ResponseBody
	@RequestMapping(value = "resGroupSearchWithCusGrpId.json")
	public String resGroupSearchWithCusGrpId(String search,
			@RequestParam("limit") Integer limit,
			@RequestParam("offset") Integer offset) {

		ResourceGroupModel resourceGroupModel = JSON.parseObject(search,
				ResourceGroupModel.class);
		BootstrapTableData bData = new BootstrapTableData();

		List<ResourceGroupModel> resourceList = iResourceGroupService
				.queryResGroupWithCusGrpId(resourceGroupModel, offset, limit);
		
		if (null != resourceList && resourceList.size() > 0) {
			bData.setRows(resourceList);
			bData.setPage(offset / limit + 1);
			bData.setTotal(iResourceGroupService
					.queryCountByCondition(resourceGroupModel));
		} else {
			bData.setTotal(0);
		}
		if(null == bData.getRows()){
			bData.setPage(0);
			bData.setRows(new Object());
			bData.setTotal(0);
		}
		System.out.println(JSON.toJSONString(bData));
		return JSON.toJSONString(bData);
	}
	

	//删除用户组与资源组权限关系
	@RequestMapping(value="disableResGroupPermission.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String disableCusGrpResGrpPermission( @RequestParam("userGrpId") Integer userGrpId , 
			@RequestParam("resGroupId") Integer resGroupId , ModelMap model){
		
		APIMessage apiMessage = new APIMessage();
		
		int flag = iResourceGroupService.deleteCusGrpResGrpPermission(userGrpId, resGroupId);
		apiMessage.setStatus(flag);
		
		return JSON.toJSONString(apiMessage);
	}

	
}
