package com.ldps.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ldps.data.APIMessage;
import com.ldps.model.NodeModel;
import com.ldps.model.UserModel;
import com.ldps.service.INodeService;
import com.ldps.service.IUserService;

@Controller
@RequestMapping(value = "manage")
public class BackendUserController {
	
	@Resource(name="userService")
	private IUserService iUserService;
	
	@ResponseBody
	@RequestMapping(value="changePassword.json",method=RequestMethod.GET)
	public String showNode( HttpServletRequest request, String curPassword,
				String newPassword, String newPasswordConfirm){
		
		APIMessage apiMessage = new APIMessage();
		
		if(StringUtils.isEmpty(curPassword.trim())){
			apiMessage.setStatus(-1);
		}else if(StringUtils.isEmpty(newPassword.trim())){
			apiMessage.setStatus(-2);
		}else if(StringUtils.isEmpty(newPasswordConfirm.trim()) || !newPasswordConfirm.trim().equals(newPassword.trim())){
			apiMessage.setStatus(-3);
		}else {
			//修改密码
			UserModel uModel = (UserModel)request.getSession().getAttribute("user");
			if(uModel == null){
				apiMessage.setStatus(-4);
			}else if(!curPassword.trim().equals(uModel.getPassword())){
				apiMessage.setStatus(-5);
			}else{
				int flag = iUserService.changePassword(uModel, newPasswordConfirm.trim());
				apiMessage.setStatus(flag);
				if(flag == 1){
					uModel.setPassword(newPasswordConfirm.trim());
					request.getSession().setAttribute("user", uModel);
				}
			}
		}
		return JSON.toJSONString(apiMessage);
	}
}
