package com.ldps.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ldps.model.UserModel;
import com.ldps.service.IUserService;

@Controller
public class LoginControl {
	
	@Resource(name="userService")
	private IUserService iUserService;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(HttpServletRequest request,ModelMap model) {
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		UserModel userModel=iUserService.getUserByUP(userName, password);
		request.getSession().setAttribute("user", userModel);
		//暂时跳转到资源页面
		return "redirect:manage/resourceManagePage";
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
