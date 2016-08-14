package com.ldps.model;

import java.sql.Date;

public class CustomerGroupModel {
    private Integer id;

    private String cGPName;

    private String status;
    
    private Date createDate;
    
    private Integer createUser;
    
    private Double cGPNum1;
    private Double cGPNum2;
    private String cGPVc1;
    private String cGPVc2;
    private String cGPChar1;
    private String cGPChar2;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getcGPName() {
		return cGPName;
	}
	public void setcGPName(String cGPName) {
		this.cGPName = cGPName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public Double getcGPNum1() {
		return cGPNum1;
	}
	public void setcGPNum1(Double cGPNum1) {
		this.cGPNum1 = cGPNum1;
	}
	public Double getcGPNum2() {
		return cGPNum2;
	}
	public void setcGPNum2(Double cGPNum2) {
		this.cGPNum2 = cGPNum2;
	}
	public String getcGPVc1() {
		return cGPVc1;
	}
	public void setcGPVc1(String cGPVc1) {
		this.cGPVc1 = cGPVc1;
	}
	public String getcGPVc2() {
		return cGPVc2;
	}
	public void setcGPVc2(String cGPVc2) {
		this.cGPVc2 = cGPVc2;
	}
	public String getcGPChar1() {
		return cGPChar1;
	}
	public void setcGPChar1(String cGPChar1) {
		this.cGPChar1 = cGPChar1;
	}
	public String getcGPChar2() {
		return cGPChar2;
	}
	public void setcGPChar2(String cGPChar2) {
		this.cGPChar2 = cGPChar2;
	}



}