package com.ldps.model;

import java.sql.Date;

public class PermissionRecModel {
    private Integer objectType; //'授权对象；1 用户；2 用户组',

    private String cGroupname; //用户组
    
    private String customerId; //用户
    
    private String resourceName; //资源名称
    
    private String actionType; //1 授权；0 撤销权限

    private Date createDate;
    
    private Integer createUser;

	public Integer getObjectType() {
		return objectType;
	}

	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}

	public String getcGroupname() {
		return cGroupname;
	}

	public void setcGroupname(String cGroupname) {
		this.cGroupname = cGroupname;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
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