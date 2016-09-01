package com.ldps.data;

import java.util.Date;

public class CustomerData {
    private Long id;

    private String memid;

    private String custid;

    private String mobile;
    
    private String relation;

    private String name;

    private String password;

    private String sex;

    private Date birthday;
    
    private String birthdayStr;

    private String email;


    private Integer extendSpecificFlag;
    
	public Integer getExtendSpecificFlag() {
		return extendSpecificFlag;
	}

	public void setExtendSpecificFlag(Integer extendSpecificFlag) {
		this.extendSpecificFlag = extendSpecificFlag;
	}

	
	public String getMemid() {
		return memid;
	}

	public void setMemid(String memid) {
		this.memid = memid;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getBirthdayStr() {
 		return birthdayStr;
 	}

 	public void setBirthdayStr(String birthdayStr) {
 		this.birthdayStr = birthdayStr;
 	}
}