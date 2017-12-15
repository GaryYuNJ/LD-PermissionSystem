package com.ldps.model;

public class ResourceKeyModel {
    private Integer id;

    private String mac;

    private String password;

    private Integer manufacturerId;

    private Integer resourceId;

    private Integer rInt1;

    private Integer rInt2;

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

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getRInt1() {
        return rInt1;
    }

    public void setRInt1(Integer rInt1) {
        this.rInt1 = rInt1;
    }

    public Integer getRInt2() {
        return rInt2;
    }

    public void setRInt2(Integer rInt2) {
        this.rInt2 = rInt2;
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