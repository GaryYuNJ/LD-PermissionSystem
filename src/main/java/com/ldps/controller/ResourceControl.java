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
		model.put("pageIndex", 2);
		return "resourceManage";
	}
	

	@ResponseBody
	@RequestMapping(value="allBuildings.json")
	public String getBuildings(){
		return JSON.toJSONString(iBuildingModelService.queryAll());
	}
	@ResponseBody
	@RequestMapping(value="resourceSearch.json")
	public String resourceSearch(String search,@RequestParam("limit") Integer limit, 
			@RequestParam("offset") Integer offset){

			ResourceModel resourceModel= JSON.parseObject(search,ResourceModel.class);
			BootstrapTableData bData = new BootstrapTableData();

			List<ResourceModel> resourceList = iResourceService.queryBasicResByCondition(resourceModel, offset, limit);
			if(null != resourceList && resourceList.size()>0){
				bData.setRows(resourceList);
				bData.setPage(offset/limit +1);
				bData.setTotal(iResourceService.queryCountByCondition(resourceModel));
			}else{
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

	public void setiBuildingModelService(IBuildingModelService iBuildingModelService) {
		this.iBuildingModelService = iBuildingModelService;
	}

}
