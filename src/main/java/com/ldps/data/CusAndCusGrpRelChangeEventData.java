package com.ldps.data;


//用户组添加、删除用户事件 data
public class CusAndCusGrpRelChangeEventData {
	
	private Long customerId;
	private Integer cusGroupId;
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Integer getCusGroupId() {
		return cusGroupId;
	}
	public void setCusGroupId(Integer cusGroupId) {
		this.cusGroupId = cusGroupId;
	}
	
}
