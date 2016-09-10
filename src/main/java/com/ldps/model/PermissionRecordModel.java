package com.ldps.model;

import java.util.Date;

public class PermissionRecordModel {
    private Integer objectRelation;

    private Long customerId;

    private Integer resourceId;

    private Integer cgroupId;

    private Integer rgroupId;

    private Integer actionType;

    private Date createDate;

    private Long createUser;

    private Date startDate;

    private Date endDate;

    public Integer getObjectRelation() {
        return objectRelation;
    }

    public void setObjectRelation(Integer objectRelation) {
        this.objectRelation = objectRelation;
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

    public Integer getCgroupId() {
        return cgroupId;
    }

    public void setCgroupId(Integer cgroupId) {
        this.cgroupId = cgroupId;
    }

    public Integer getRgroupId() {
        return rgroupId;
    }

    public void setRgroupId(Integer rgroupId) {
        this.rgroupId = rgroupId;
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
}