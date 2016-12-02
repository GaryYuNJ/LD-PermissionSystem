package com.ldps.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
		if(null!=userModel){
			List<Role> roleList =roleService.getRoleListByUserId(userModel.getId());
			if(null!=roleList&&!roleList.isEmpty()){
				//List<Integer>  roleType=new ArrayList<Integer>();
				//List<BuildingModel> buildings =new ArrayList<BuildingModel>();
				int i=1000;
				List<Long> roleIds=new ArrayList<Long>();
				for(Role role:roleList){
					roleIds.add(role.getId());
					if(i>role.getRoleType()){
						i=role.getRoleType();
					}
				}
				userModel.setRoleType(i);
				if(userModel.getRoleType()!=1){
					userModel.setBuildings(roleBuildingService.queryBuildingByRoles(roleIds));
				}
			}
		}
		request.getSession().setAttribute("user", userModel);
		//暂时跳转到资源页面
		if(null!=userModel&&userModel.getRoleType()>2){
			return "redirect:user/userManage";
		}
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
