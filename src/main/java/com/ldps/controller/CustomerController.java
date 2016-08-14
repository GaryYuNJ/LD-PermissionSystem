package com.ldps.controller;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ldps.model.CustomerModel;
import com.ldps.service.ICustomerService;

@Controller
public class CustomerController {

	CustomerModel customer;
	
	@Resource
	ICustomerService customerService;


	@RequestMapping(value="/queryCustomer1",method=RequestMethod.GET)
	public String demo(@ModelAttribute CustomerModel customer,Model model){
		model.addAttribute("customer", customerService.getUserByCId(customer.getCid()));
		return "demo";
	}
	
	@RequestMapping(value="/queryCustomer2",method=RequestMethod.GET)
	public String demo2(@RequestParam("id")String id,Model model){
		CustomerModel cModel= customerService.getUserByCId(id);
		model.addAttribute("customer", cModel);
		return "demo";
	}
	
	@RequestMapping(value="/queryCustomer3",method=RequestMethod.GET)
	public String demo3(Model model){
		model.addAttribute("customer", customerService.getUserByCId(customer.getCid()));
		return "demo";
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public ICustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}
	

}
