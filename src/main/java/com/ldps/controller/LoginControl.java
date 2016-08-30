package com.ldps.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
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
	public String login(HttpServletRequest request) {
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		UserModel userModel=iUserService.getUserByUP(userName, password);
		request.getSession().setAttribute("user", userModel);
		//暂时跳转到资源页面
		return "resourceManage";
	}

	public IUserService getiUserService() {
		return iUserService;
	}

	public void setiUserService(IUserService iUserService) {
		this.iUserService = iUserService;
	}

}
