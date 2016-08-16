package com.ldps.model;

import java.util.Date;

public class ResourceGrpRelModel {
    private Integer rgroupId;

    private Integer resourceId;

    private Date createDate;

    private Integer createUser;

    public Integer getRgroupId() {
        return rgroupId;
    }

    public void setRgroupId(Integer rgroupId) {
        this.rgroupId = rgroupId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
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