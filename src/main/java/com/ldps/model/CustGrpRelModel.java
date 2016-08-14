package com.ldps.model;

import java.sql.Date;

public class CustGrpRelModel {
    private String customerId;

    private Integer customerGrpId;

    private Date createDate;
    
    private Integer createUser;
    

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Integer getCustomerGrpId() {
		return customerGrpId;
	}

	public void setCustomerGrpId(Integer customerGrpId) {
		this.customerGrpId = customerGrpId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}


}