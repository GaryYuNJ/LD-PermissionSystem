package com.ldps.controller;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import com.ldps.model.UserModel;
import com.ldps.service.IUserService;

@Controller
public class BackendUserController {

	@Resource(name = "userService")
	private IUserService iUserService;

	@ResponseBody
	@RequestMapping(value = "manage/changePassword.json", method = RequestMethod.GET)
	public String showNode(HttpServletRequest request, String curPassword,
			String newPassword, String newPasswordConfirm) {

		APIMessage apiMessage = new APIMessage();

		if (StringUtils.isEmpty(curPassword.trim())) {
			apiMessage.setStatus(-1);
		} else if (StringUtils.isEmpty(newPassword.trim())) {
			apiMessage.setStatus(-2);
		} else if (StringUtils.isEmpty(newPasswordConfirm.trim())
				|| !newPasswordConfirm.trim().equals(newPassword.trim())) {
			apiMessage.setStatus(-3);
		} else {
			// 修改密码
			UserModel uModel = (UserModel) request.getSession().getAttribute(
					"user");
			if (uModel == null) {
				apiMessage.setStatus(-4);
			} else if (!curPassword.trim().equals(uModel.getPassword())) {
				apiMessage.setStatus(-5);
			} else {
				int flag = iUserService.changePassword(uModel,
						newPasswordConfirm.trim());
				apiMessage.setStatus(flag);
				if (flag == 1) {
					uModel.setPassword(newPasswordConfirm.trim());
					request.getSession().setAttribute("user", uModel);
				}
			}
		}
		return JSON.toJSONString(apiMessage);
	}

	@RequestMapping(value = "admin/backendUserManage", method = RequestMethod.GET)
	public String backendUserManage(ModelMap model) {
		// 页面菜单样式需要
		model.put("pageIndex", 5);
		return "backendUserManage";
	}

	@ResponseBody
	@RequestMapping(value = "admin/backendUserSearch.json")
	public String backendUserSearch(@RequestParam("search") String name,
			@RequestParam("limit") Integer limit,
			@RequestParam("offset") Integer offset) {
		if (StringUtils.isEmpty(name))
			name = null;
		BootstrapTableData bData = new BootstrapTableData();

		List<UserModel> userList = iUserService.queryUserWithPageIndex(name,
				offset, limit);
		if (null != userList && userList.size() > 0) {
			bData.setRows(userList);
			bData.setPage(offset / limit + 1);
			bData.setTotal(iUserService.queryCountByCondition(name));
		} else {
			bData.setPage(0);
			bData.setRows(new Object());
			bData.setTotal(0);
		}
		return JSON.toJSONString(bData);
	}

	@ResponseBody
	@RequestMapping(value = "admin/saveBackEndUser.json")
	public String saveBackEndUser(@RequestParam("name") String name,
			@RequestParam("status") String status,@RequestParam("roleId") Long roleId) {
		APIMessage apiMessage = new APIMessage();
		apiMessage.setStatus(-1);
		if (!StringUtils.isEmpty(name)) {
			if (iUserService.getUserByName(name) > 0)
				apiMessage.setStatus(2);
			else {
				UserModel userModel = new UserModel();
				userModel.setCreateDate(new Date());
				userModel.setName(name);
				userModel.setPassword("888888");// 默认密码
				userModel.setStatus(status);
				userModel.setRoleId(roleId);
				userModel.setCreateUser(iUserService.getSessionUserId());
				if (iUserService.saveOrUpdate(userModel) > 0){
					apiMessage.setStatus(1);
				}
			}
		}
		return JSON.toJSONString(apiMessage);
	}
	
	@ResponseBody
	@RequestMapping(value = "admin/updateEndUser.json")
	public String updateEndUser(@RequestParam("id") Long id,
			@RequestParam("status") String status,@RequestParam("roleId") Long roleId) {
		APIMessage apiMessage = new APIMessage();
		apiMessage.setStatus(-1);
		if (null!=id) {
				UserModel userModel =iUserService.getUserById(id);
				if(null!=userModel){
					userModel.setStatus(status);
					userModel.setRoleId(roleId);
					if (iUserService.saveOrUpdate(userModel) > 0) {
						apiMessage.setStatus(1);
					}
				}else{
					apiMessage.setStatus(2);
				}
		}
		return JSON.toJSONString(apiMessage);
	}

	@ResponseBody
	@RequestMapping(value = "admin/checkLoginIdExists.json")
	public String checkLoginIdExists(@RequestParam("name") String name) {
		APIMessage apiMessage = new APIMessage();
		apiMessage.setStatus(-1);
		if (!StringUtils.isEmpty(name)) {
			if (iUserService.getUserByName(name) > 0)
				apiMessage.setStatus(2);
			else
				apiMessage.setStatus(1);
		}
		return JSON.toJSONString(apiMessage);
	}
	
	//刪除后台用户
	@ResponseBody
	@RequestMapping(value = "admin/delBackUser.json")
	public String delBackUser(@RequestParam("bUserId") Long bUserId) {
		APIMessage apiMessage = new APIMessage();
		apiMessage.setStatus(iUserService.delBuser(bUserId));
		return JSON.toJSONString(apiMessage);
	}
}
