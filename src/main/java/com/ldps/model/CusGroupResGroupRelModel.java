package com.ldps.model;

import java.util.Date;

public class CusGroupResGroupRelModel {
    private Integer cgroupId;

    private Integer rgroupId;

    private Date startDate;

    private Date endDate;

    private Date createDate;

    private Long createUser;

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