package com.ldps.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.ldps.data.APIMessage;
import com.ldps.model.CustomerModel;
import com.ldps.service.ICustomerService;

@Controller
@RequestMapping(value = "crmApi")
public class CustomerAPIController {
	private static Logger logger = Logger
			.getLogger(CustomerAPIController.class);
	@Resource
	private ICustomerService iCustomerSevice;

	// 新增会员API接口
	@RequestMapping(value = "add", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String addCustomer(@RequestParam String customerStr) {
		// 解析json
		APIMessage apiMessage = new APIMessage();
		try {
			CustomerModel customer = JSON.parseObject(customerStr,
					CustomerModel.class);
			// 验证数据
			String addVerificatString = iCustomerSevice
					.addVerification(customer);
			if (!StringUtils.isEmpty(addVerificatString)) {
				apiMessage.setStatus(2);
				apiMessage.setMessage(addVerificatString);
			} else {
				// 进行入库
				if(iCustomerSevice.addCustomer(customer)==-1){
					apiMessage.setStatus(2);
					apiMessage.setMessage("该会员已存在");
				}else{
					apiMessage.setStatus(1);
					apiMessage.setMessage("添加数据成功");
				}
			}
		} catch (JSONException e) {
			apiMessage.setStatus(3);
			apiMessage.setMessage("传入Json字符串错误");
		}catch(Exception e){
			apiMessage.setStatus(-1);
			apiMessage.setMessage("系统异常");
		}
		// 返回信息
		return JSON.toJSONString(apiMessage);
	}

	// 删除API接口
	@RequestMapping(value = "delete", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String deleteCustomer(@RequestParam String cmMemid) {
		APIMessage apiMessage = new APIMessage();
		apiMessage.setStatus(2);
		apiMessage.setMessage("删除失败");
		try{
			if (!StringUtils.isEmpty(cmMemid)) {
				int i = iCustomerSevice.deleteCustomerByCmMemid(cmMemid);
				if (i > 0) {
					apiMessage.setStatus(1);
					apiMessage.setMessage("删除成功");
				}
			}
		}catch(Exception e){
			apiMessage.setStatus(-1);
			apiMessage.setMessage("系统异常");
		}
		
		return JSON.toJSONString(apiMessage);
	}

	// 更新API接口
	@RequestMapping(value = "update", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String UpdateCustomer(@RequestParam String customerStr) {
		APIMessage apiMessage = new APIMessage();
		apiMessage.setStatus(2);
		apiMessage.setMessage("更新失败");
		try {
			CustomerModel customer = JSON.parseObject(customerStr,
					CustomerModel.class);
			if (!StringUtils.isEmpty(customer) && !StringUtils.isEmpty(customer.getCmMemid())) {
				int i = iCustomerSevice.updateCustomerByCmMemid(customer);
				if (i > 0) {
					apiMessage.setStatus(1);
					apiMessage.setMessage("更新成功");
				}
			}
		} catch (JSONException e) {
			apiMessage.setStatus(3);
			apiMessage.setMessage("传入Json字符串错误");
		}catch(Exception e){
			apiMessage.setStatus(-1);
			apiMessage.setMessage("系统异常");
		}
		// 返回信息
		return JSON.toJSONString(apiMessage);
	} 
}
