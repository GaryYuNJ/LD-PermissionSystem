package com.ldps.model;

import java.util.Date;

public class PermissonRecordModel {
    private Integer objectType;

    private String cgroupName;

    private String resourceName;

    private Long customerId;

    private Integer actionType;

    private Date createDate;

    private Long createUser;

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public String getCgroupName() {
        return cgroupName;
    }

    public void setCgroupName(String cgroupName) {
        this.cgroupName = cgroupName == null ? null : cgroupName.trim();
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
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