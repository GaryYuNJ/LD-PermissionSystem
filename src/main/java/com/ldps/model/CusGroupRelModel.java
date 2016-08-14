package com.ldps.model;

import java.util.Date;

public class CusGroupRelModel {
    private Integer cgroupId;

    private String cid;

    private Date createDate;

    private Integer createUser;

    public Integer getCgroupId() {
        return cgroupId;
    }

    public void setCgroupId(Integer cgroupId) {
        this.cgroupId = cgroupId;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
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