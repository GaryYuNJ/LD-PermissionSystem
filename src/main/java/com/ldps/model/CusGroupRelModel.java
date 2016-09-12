package com.ldps.model;

import java.util.Date;

public class CusGroupRelModel {
    private Integer cgroupId;

    private Long customerId;

    private Date createDate;

    private Long createUser;

    public Integer getCgroupId() {
        return cgroupId;
    }

    public void setCgroupId(Integer cgroupId) {
        this.cgroupId = cgroupId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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