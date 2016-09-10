package com.ldps.data;


//向资源组组添加资源事件 data
public class RemoveCusFromCusGrpPermChangeEventData {

	private Long customerId;
	private Integer cusGrpId;
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Integer getCusGrpId() {
		return cusGrpId;
	}
	public void setCusGrpId(Integer cusGrpId) {
		this.cusGrpId = cusGrpId;
	}

	
	
}
