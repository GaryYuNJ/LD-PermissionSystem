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
import com.ldps.data.APIMessage;
import com.ldps.data.BootstrapTableData;
import com.ldps.model.BuildingModel;
import com.ldps.service.IBuildingModelService;
import com.ldps.service.IResourceService;
import com.ldps.service.IRoleBuildingService;

@Controller
@RequestMapping(value = "admin")
public class BuildingControl {
	
	@Resource
	private IBuildingModelService buildingService;
	@Resource
	private IRoleBuildingService roleBuildingService;
	@Resource
	private IResourceService resourceService;
	
	@RequestMapping(value = "buildingManagePage", method = RequestMethod.GET)
	public String buildingManage(ModelMap model) {
		// 页面菜单样式需要
		model.put("pageIndex", 6);
		return "buildingManage";
	}
	
	@ResponseBody
	@RequestMapping(value = "buildingSearch.json")
	public String buildingSearch(@RequestParam("search")String buildingName,
			@RequestParam("limit") Integer limit,
			@RequestParam("offset") Integer offset) {
		if(StringUtils.isEmpty(buildingName))
			buildingName=null;
		BootstrapTableData bData = new BootstrapTableData();

		List<BuildingModel> buildingList = buildingService.queryBuildingWithPageIndex(buildingName, offset, limit);
		if (null != buildingList && buildingList.size() > 0) {
			bData.setRows(buildingList);
			bData.setPage(offset / limit + 1);
			bData.setTotal(buildingService
					.queryCountByCondition(buildingName));
		} else {
			bData.setPage(0);
			bData.setRows(new Object());
			bData.setTotal(0);
		}
		return JSON.toJSONString(bData);
	}
	
	@ResponseBody
	@RequestMapping(value = "buildingSave.json")
	public String save(@RequestParam("id")Integer id,
			@RequestParam("name") String name) {
		APIMessage am=new APIMessage();
		am.setStatus(-1);
		if(!StringUtils.isEmpty(name)){
			BuildingModel buildingModel=new BuildingModel();
			buildingModel.setId(id);
			buildingModel.setName(name);
			am.setStatus(buildingService.save(buildingModel));
		}
		return JSON.toJSONString(am);
	}
	
	@ResponseBody
	@RequestMapping(value = "buildingDelete.json")
	public String delete(@RequestParam("id")Integer id) {
		APIMessage am=new APIMessage();
		am.setStatus(-1);
		if(!StringUtils.isEmpty(id)){
			am.setStatus(buildingService.delete(id));
		}
		return JSON.toJSONString(am);
	}
}
