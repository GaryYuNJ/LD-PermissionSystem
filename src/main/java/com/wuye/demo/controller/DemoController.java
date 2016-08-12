package com.wuye.demo.controller;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wuye.demo.model.User;
import com.wuye.demo.service.IUserService;

@Controller
public class DemoController {

	User user;
	@Resource
	IUserService userService;


	@RequestMapping(value="/demo",method=RequestMethod.GET)
	public String demo(@ModelAttribute User user,Model model){
		model.addAttribute("User", userService.getUserById(user.getId()));
		return "demo";
	}
	
	@RequestMapping(value="/demo2",method=RequestMethod.GET)
	public String demo2(@RequestParam("id")Integer id,Model model){
		model.addAttribute("User", userService.getUserById(id));
		return "demo";
	}
	
	@RequestMapping(value="/demo3",method=RequestMethod.GET)
	public String demo3(Model model){
		model.addAttribute("User", userService.getUserById(user.getId()));
		return "demo";
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	
}
