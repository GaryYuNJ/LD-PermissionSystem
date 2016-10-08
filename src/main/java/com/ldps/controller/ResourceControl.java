package com.ldps.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.ldps.data.APIMessage;
import com.ldps.data.BootstrapTableData;
import com.ldps.model.ResourceModel;
import com.ldps.service.IBuildingModelService;
import com.ldps.service.IResourceService;
import com.ldps.service.IUserService;

@Controller
@RequestMapping(value = "manage")
public class ResourceControl {
	@Resource(name = "iResourceService")
	private IResourceService iResourceService;
	@Resource(name = "iBuildingModelService")
	private IBuildingModelService iBuildingModelService;
	@Resource
	private IUserService userService;
	
	@Value("#{configProperties['upload.dir']}")
	private String uploadDir; 
	
	@RequestMapping(value = "resourceManagePage", method = RequestMethod.GET)
	public String resouceManage(ModelMap model) {
		// 页面菜单样式需要
		model.put("pageIndex", 0);
		return "resourceManage";
	}

	@ResponseBody
	@RequestMapping(value = "getResourceById.json")
	public String getResourceById(@RequestParam("id") Integer id) {
		return JSON.toJSONString(iResourceService.getReourceAndKeyById(id));
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
	public String updateResource(
			@RequestParam("resourceModelJson") String resourceModelJson) {
		ResourceModel resourceModel = JSON.parseObject(resourceModelJson,
				ResourceModel.class);
		APIMessage am = new APIMessage();
		am.setStatus(1);
		
		if(null !=  resourceModel.getStatus() 
				|| "on".equals(resourceModel.getStatus())){
			resourceModel.setStatus("Y");
		}else{
			resourceModel.setStatus("N");
		}
		
		if(null !=  resourceModel.getShareEnable() 
				|| "on".equals(resourceModel.getShareEnable())){
			resourceModel.setShareEnable("Y");
		}else{
			resourceModel.setShareEnable("N");
		}
		
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
			resourceModel.setCreateUser(userService.getSessionUserId());
		}
		resourceModel.setCreateDate(new Date());

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
			am.setMessage(resourceModel.getId().toString());
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
			bData.setPage(0);
			bData.setRows(new Object());
			bData.setTotal(0);
		}
		return JSON.toJSONString(bData);
	}

	@ResponseBody
	@RequestMapping(value = "resourceSearchWithGroup.json")
	public String resourceSearchWithGroup(String search,
			@RequestParam("limit") Integer limit,
			@RequestParam("offset") Integer offset) {

		ResourceModel resourceModel = JSON.parseObject(search,
				ResourceModel.class);
		BootstrapTableData bData = new BootstrapTableData();

		List<ResourceModel> resourceList = iResourceService
				.queryBasicResByConditionWithGId(resourceModel, offset, limit);
		if (null != resourceList && resourceList.size() > 0) {
			bData.setRows(resourceList);
			bData.setPage(offset / limit + 1);
			bData.setTotal(iResourceService
					.selectCountConditionWithGId(resourceModel));
		} else {
			bData.setPage(0);
			bData.setRows(new Object());
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
			bData.setRows(new Object());
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
			bData.setRows(new Object());
			bData.setTotal(0);
		}
		System.out.println(JSON.toJSONString(bData));
		return JSON.toJSONString(bData);
	}

	//资源导入功能
		@ResponseBody
		@RequestMapping(value = "uploadFile")
	    public String uploadFileHandler(@RequestParam("file") MultipartFile file, 
	    		@RequestParam("nodeId") Integer nodeId) throws IOException {
	    
			APIMessage message = new APIMessage();
	    	if (!file.isEmpty()) {
	    		//--上传--
	            InputStream in = null;
	            OutputStream out = null;

	            try {
	                // 获得在tomcat中项目的路径， 需要在web.xml配置ft.webapp
	            	if(StringUtils.isEmpty(uploadDir)){
	            		String webRootPath = System.getProperty("ft.webapp");
	            		uploadDir = webRootPath + File.separator + "uploadFiles";
	            	}
	            	File dir = new File(uploadDir);
	                if (!dir.exists())
	                    dir.mkdirs();
	                
	                File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
	                in = file.getInputStream();
	                out = new FileOutputStream(serverFile);
	                byte[] b = new byte[1024];
	                int len = 0;
	                while ((len = in.read(b)) > 0) {
	                    out.write(b, 0, len);
	                }
	                out.close();
	                in.close();
	                System.out.println("Server File Location=" + serverFile.getAbsolutePath());
	                
	                //--解析、导入--
	                message = iResourceService.importResFromExcel(serverFile.getAbsolutePath(),nodeId);

	            } catch (Exception e) {
	            	e.printStackTrace();
	            	message.setStatus(-1);
	            	message.setMessage("文件解析失败！");
	            } finally {
	                if (out != null) {
	                    out.close();
	                    out = null;
	                }

	                if (in != null) {
	                    in.close();
	                    in = null;
	                }
	            }
	        } else {
	        	message.setStatus(-1);
	        	message.setMessage("文件没有资源数据！");
	        }
	    	
	    	return JSON.toJSONString(message);
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
