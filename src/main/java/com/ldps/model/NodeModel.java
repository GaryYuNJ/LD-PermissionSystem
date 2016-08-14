package com.ldps.model;

import java.sql.Date;

public class NodeModel {
    private Integer id;

    private String name;
    
    private Integer grade; //资源节点级别。根节点为0
    
    private Integer int2;
    private String vc1;
    private String char1;
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
		this.name = name;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Integer getInt2() {
		return int2;
	}
	public void setInt2(Integer int2) {
		this.int2 = int2;
	}
	public String getVc1() {
		return vc1;
	}
	public void setVc1(String vc1) {
		this.vc1 = vc1;
	}
	public String getChar1() {
		return char1;
	}
	public void setChar1(String char1) {
		this.char1 = char1;
	}
    
    
}