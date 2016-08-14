package com.ldps.model;

import java.sql.Date;

public class CustomerModel {
    private Integer id;    		//'会员ID'
    private String customerId;	//会员帐号
    private String customerType ;	//会员类型
    private String customerStatus ; //会员状态
    private Double customerCurrentJF ; //当期积分
    private Double customerHisJF ; //历史积分
    private Double customerTotalJF ; //总积分
    private Double customerCurrentXFJF ; //当期消费金额
    private Double customerHisXFJF ; //历史消费金额
    private Double customerTotalXFJF ; //总消费金额
    private String customerVipNo ; //申办卡号
    private String customerName ; //申办姓名
    private String customerCreator ; //创建人
    private Date customerCreaterDate ; //创建日期
    private String customerMaintor ; //最近维护人
    private Date customerMaintDate ; //最近维护日期
    private Date cDate1 ; //最近定期处理的日期
    private Date cDate2 ;
    private Date cDate3 ;
    private Date cDate4 ;
    private Date cDate5 ;
    private Double cNum1 ;
    private Double cNum2 ;
    private Double cNum3 ;
    private Double cNum4 ; //有效期内有效积分
    private Double cNum5 ; //有效期内消费金额
    private Double cNum6 ;
    private Double cNum7 ;
    private Double cNum8 ;
    private Double cNum9 ;
    private Double cNum10 ; //成长值
    private String cVC1 ; //客服顾问
    private String cVC2 ; //升降级未换卡前的卡类别
    private String cVC3 ;
    private String cVC4 ;
    private String cVC5 ;
    private String cVC6 ;
    private String cVC7 ;
    private String cVC8 ;
    private String cChr1 ;
    private String cChr2 ;
    private String cChr3 ;
    private String cChr4 ;
    private String cChr5 ;
    private String cChr6 ; //租客所属的主成员会员号custno
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getCustomerStatus() {
		return customerStatus;
	}
	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}
	public Double getCustomerCurrentJF() {
		return customerCurrentJF;
	}
	public void setCustomerCurrentJF(Double customerCurrentJF) {
		this.customerCurrentJF = customerCurrentJF;
	}
	public Double getCustomerHisJF() {
		return customerHisJF;
	}
	public void setCustomerHisJF(Double customerHisJF) {
		this.customerHisJF = customerHisJF;
	}
	public Double getCustomerTotalJF() {
		return customerTotalJF;
	}
	public void setCustomerTotalJF(Double customerTotalJF) {
		this.customerTotalJF = customerTotalJF;
	}
	public Double getCustomerCurrentXFJF() {
		return customerCurrentXFJF;
	}
	public void setCustomerCurrentXFJF(Double customerCurrentXFJF) {
		this.customerCurrentXFJF = customerCurrentXFJF;
	}
	public Double getCustomerHisXFJF() {
		return customerHisXFJF;
	}
	public void setCustomerHisXFJF(Double customerHisXFJF) {
		this.customerHisXFJF = customerHisXFJF;
	}
	public Double getCustomerTotalXFJF() {
		return customerTotalXFJF;
	}
	public void setCustomerTotalXFJF(Double customerTotalXFJF) {
		this.customerTotalXFJF = customerTotalXFJF;
	}
	public String getCustomerVipNo() {
		return customerVipNo;
	}
	public void setCustomerVipNo(String customerVipNo) {
		this.customerVipNo = customerVipNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerCreator() {
		return customerCreator;
	}
	public void setCustomerCreator(String customerCreator) {
		this.customerCreator = customerCreator;
	}
	public Date getCustomerCreaterDate() {
		return customerCreaterDate;
	}
	public void setCustomerCreaterDate(Date customerCreaterDate) {
		this.customerCreaterDate = customerCreaterDate;
	}
	public String getCustomerMaintor() {
		return customerMaintor;
	}
	public void setCustomerMaintor(String customerMaintor) {
		this.customerMaintor = customerMaintor;
	}
	public Date getCustomerMaintDate() {
		return customerMaintDate;
	}
	public void setCustomerMaintDate(Date customerMaintDate) {
		this.customerMaintDate = customerMaintDate;
	}
	public Date getcDate1() {
		return cDate1;
	}
	public void setcDate1(Date cDate1) {
		this.cDate1 = cDate1;
	}
	public Date getcDate2() {
		return cDate2;
	}
	public void setcDate2(Date cDate2) {
		this.cDate2 = cDate2;
	}
	public Date getcDate3() {
		return cDate3;
	}
	public void setcDate3(Date cDate3) {
		this.cDate3 = cDate3;
	}
	public Date getcDate4() {
		return cDate4;
	}
	public void setcDate4(Date cDate4) {
		this.cDate4 = cDate4;
	}
	public Date getcDate5() {
		return cDate5;
	}
	public void setcDate5(Date cDate5) {
		this.cDate5 = cDate5;
	}
	public Double getcNum1() {
		return cNum1;
	}
	public void setcNum1(Double cNum1) {
		this.cNum1 = cNum1;
	}
	public Double getcNum2() {
		return cNum2;
	}
	public void setcNum2(Double cNum2) {
		this.cNum2 = cNum2;
	}
	public Double getcNum3() {
		return cNum3;
	}
	public void setcNum3(Double cNum3) {
		this.cNum3 = cNum3;
	}
	public Double getcNum4() {
		return cNum4;
	}
	public void setcNum4(Double cNum4) {
		this.cNum4 = cNum4;
	}
	public Double getcNum5() {
		return cNum5;
	}
	public void setcNum5(Double cNum5) {
		this.cNum5 = cNum5;
	}
	public Double getcNum6() {
		return cNum6;
	}
	public void setcNum6(Double cNum6) {
		this.cNum6 = cNum6;
	}
	public Double getcNum7() {
		return cNum7;
	}
	public void setcNum7(Double cNum7) {
		this.cNum7 = cNum7;
	}
	public Double getcNum8() {
		return cNum8;
	}
	public void setcNum8(Double cNum8) {
		this.cNum8 = cNum8;
	}
	public Double getcNum9() {
		return cNum9;
	}
	public void setcNum9(Double cNum9) {
		this.cNum9 = cNum9;
	}
	public Double getcNum10() {
		return cNum10;
	}
	public void setcNum10(Double cNum10) {
		this.cNum10 = cNum10;
	}
	public String getcVC1() {
		return cVC1;
	}
	public void setcVC1(String cVC1) {
		this.cVC1 = cVC1;
	}
	public String getcVC2() {
		return cVC2;
	}
	public void setcVC2(String cVC2) {
		this.cVC2 = cVC2;
	}
	public String getcVC3() {
		return cVC3;
	}
	public void setcVC3(String cVC3) {
		this.cVC3 = cVC3;
	}
	public String getcVC4() {
		return cVC4;
	}
	public void setcVC4(String cVC4) {
		this.cVC4 = cVC4;
	}
	public String getcVC5() {
		return cVC5;
	}
	public void setcVC5(String cVC5) {
		this.cVC5 = cVC5;
	}
	public String getcVC6() {
		return cVC6;
	}
	public void setcVC6(String cVC6) {
		this.cVC6 = cVC6;
	}
	public String getcVC7() {
		return cVC7;
	}
	public void setcVC7(String cVC7) {
		this.cVC7 = cVC7;
	}
	public String getcVC8() {
		return cVC8;
	}
	public void setcVC8(String cVC8) {
		this.cVC8 = cVC8;
	}
	public String getcChr1() {
		return cChr1;
	}
	public void setcChr1(String cChr1) {
		this.cChr1 = cChr1;
	}
	public String getcChr2() {
		return cChr2;
	}
	public void setcChr2(String cChr2) {
		this.cChr2 = cChr2;
	}
	public String getcChr3() {
		return cChr3;
	}
	public void setcChr3(String cChr3) {
		this.cChr3 = cChr3;
	}
	public String getcChr4() {
		return cChr4;
	}
	public void setcChr4(String cChr4) {
		this.cChr4 = cChr4;
	}
	public String getcChr5() {
		return cChr5;
	}
	public void setcChr5(String cChr5) {
		this.cChr5 = cChr5;
	}
	public String getcChr6() {
		return cChr6;
	}
	public void setcChr6(String cChr6) {
		this.cChr6 = cChr6;
	}
    
    
    
}