package com.ldps.data;

import java.util.Date;
import java.util.List;

public class ResourceGroupData {
    private Integer id;

    private String name;

    private String status;

    private String isPublic;

    private Date createDate;

    private Long createUser;

    private Long num1;

    private Long num2;

    private String vc1;

    private String vc2;

    private String chr1;

    private String chr2;

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic == null ? null : isPublic.trim();
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

    public Long getNum1() {
        return num1;
    }

    public void setNum1(Long num1) {
        this.num1 = num1;
    }

    public Long getNum2() {
        return num2;
    }

    public void setNum2(Long num2) {
        this.num2 = num2;
    }

    public String getVc1() {
        return vc1;
    }

    public void setVc1(String vc1) {
        this.vc1 = vc1 == null ? null : vc1.trim();
    }

    public String getVc2() {
        return vc2;
    }

    public void setVc2(String vc2) {
        this.vc2 = vc2 == null ? null : vc2.trim();
    }

    public String getChr1() {
        return chr1;
    }

    public void setChr1(String chr1) {
        this.chr1 = chr1 == null ? null : chr1.trim();
    }

    public String getChr2() {
        return chr2;
    }

    public void setChr2(String chr2) {
        this.chr2 = chr2 == null ? null : chr2.trim();
    }
}