package com.ldps.data;

import java.util.Date;

public class CusResourceRelData {
    private Long customerId;

    private Integer resourceId;

    private String enable;

    private String fromShared;

    private Date createDate;

    private Long createUser;
    
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


    public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
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
        this.enable = enable == null ? null : enable.trim();
    }

    public String getFromShared() {
        return fromShared;
    }

    public void setFromShared(String fromShared) {
        this.fromShared = fromShared == null ? null : fromShared.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

}