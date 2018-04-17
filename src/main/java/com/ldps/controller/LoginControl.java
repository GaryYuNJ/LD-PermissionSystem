package com.ldps.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ldps.model.Role;
import com.ldps.model.UserModel;
import com.ldps.service.IRoleBuildingService;
import com.ldps.service.IRoleService;
import com.ldps.service.IUserService;

@Controller
public class LoginControl {
	
	final static Logger log = Logger.getLogger(LoginControl.class);
	
	@Resource(name="userService")
	private IUserService iUserService;
	
	@Resource
	private IRoleBuildingService roleBuildingService;
	
	@Resource(name = "iRoleService")
	private IRoleService roleService;
	
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(HttpServletRequest request,ModelMap model) {
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		UserModel userModel=iUserService.getUserByUP(userName, password);
		//登陆时写入用户权限
		if(null!=userModel&&userModel.getRoleId()!=null){
			Role role =roleService.queryRoleById(userModel.getRoleId());
			if(null!=role){
				userModel.setUserRole(role);
				if(userModel.getUserRole().getRoleType()!=1){
					userModel.setBuildings(roleBuildingService.queryBuildingByRoleId(userModel.getUserRole().getId()));
				}
			}
			request.getSession().setAttribute("user", userModel);
			//暂时跳转到资源页面
			if(userModel.getUserRole().getRoleType()>2){
				return "redirect:user/userManage";
			}
			return "redirect:manage/resourceManagePage";
		}
		return "redirect:login";
	}

	@RequestMapping(value = "logOut", method = RequestMethod.GET)
	public String loginOut(HttpServletRequest request,ModelMap model) {
		request.getSession().removeAttribute("user");
		//暂时跳转到资源页面
		return "redirect:login";
	}

	public IUserService getiUserService() {
		return iUserService;
	}

	public void setiUserService(IUserService iUserService) {
		this.iUserService = iUserService;
	}

}
