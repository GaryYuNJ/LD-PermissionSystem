package com.ldps.model;

import java.sql.Date;

public class ResourceGroupModel {
    private Integer id;

    private String cGPName;
    
    private String isPublic;

    private String status;
    
    private Date createDate;
    
    private Integer createUser;
    
    private Double num1;
    private Double num2;
    private String vc1;
    private String vc2;
    private String char1;
    private String char2;
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
	public String getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
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
	public Double getNum1() {
		return num1;
	}
	public void setNum1(Double num1) {
		this.num1 = num1;
	}
	public Double getNum2() {
		return num2;
	}
	public void setNum2(Double num2) {
		this.num2 = num2;
	}
	public String getVc1() {
		return vc1;
	}
	public void setVc1(String vc1) {
		this.vc1 = vc1;
	}
	public String getVc2() {
		return vc2;
	}
	public void setVc2(String vc2) {
		this.vc2 = vc2;
	}
	public String getChar1() {
		return char1;
	}
	public void setChar1(String char1) {
		this.char1 = char1;
	}
	public String getChar2() {
		return char2;
	}
	public void setChar2(String char2) {
		this.char2 = char2;
	}
    



}