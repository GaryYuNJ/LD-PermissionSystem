package com.ldps.data;

import java.util.Date;

public class CusResourceRelData {
    private String cid;

    private Integer resourceId;

    private String enable;

    private String fromShared;

    private String sharedUser;

    private Date createDate;

    private Integer createUser;
    
    private ResourceData resourceData;
    
    private Date startDate;
    
    private Date endDate;

    public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public ResourceData getResourceData() {
		return resourceData;
	}

	public void setResourceData(ResourceData resourceData) {
		this.resourceData = resourceData;
	}

	public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
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
        this.enable = enable == null ? null : enable.trim();
    }

    public String getFromShared() {
        return fromShared;
    }

    public void setFromShared(String fromShared) {
        this.fromShared = fromShared == null ? null : fromShared.trim();
    }

    public String getSharedUser() {
        return sharedUser;
    }

    public void setSharedUser(String sharedUser) {
        this.sharedUser = sharedUser == null ? null : sharedUser.trim();
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