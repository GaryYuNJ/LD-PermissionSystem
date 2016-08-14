package com.ldps.model;

import java.sql.Date;

public class CusGRPResourceRelModel {
    private Integer customerGRPId;

    private Integer resourceId;
    
    private String enable; //是否有权限;Y，N
    
    private Date createDate;
    
    private Integer createUser; //创建人(如果是后台用户创建的)
    
    private Date updateDate;


	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
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

	public Integer getCustomerGRPId() {
		return customerGRPId;
	}

	public void setCustomerGRPId(Integer customerGRPId) {
		this.customerGRPId = customerGRPId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


}