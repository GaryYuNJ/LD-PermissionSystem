package com.ldps.model;

import java.sql.Date;

public class CustResourceRelModel {
    private String customerId;

    private Integer resourceId;
    
    private String enable; //是否有权限
    
    private String fromShare; //是否通过普通用户权限分享得到的. 默认 N
    
    private String shareUser; //分享人

    private Date createDate;
    
    private Integer createUser; //创建人(如果是后台用户创建的)
    

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

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

	public String getFromShare() {
		return fromShare;
	}

	public void setFromShare(String fromShare) {
		this.fromShare = fromShare;
	}

	public String getShareUser() {
		return shareUser;
	}

	public void setShareUser(String shareUser) {
		this.shareUser = shareUser;
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