package com.ldps.data;


//向用户组添加用户事件 data
public class AddCusToCusGroupPermChangeEventData {
	
	private Long CustomerId;
	private Integer cusGroupId;
	public Long getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(Long customerId) {
		CustomerId = customerId;
	}
	public Integer getCusGroupId() {
		return cusGroupId;
	}
	public void setCusGroupId(Integer cusGroupId) {
		this.cusGroupId = cusGroupId;
	}
	
	
}
