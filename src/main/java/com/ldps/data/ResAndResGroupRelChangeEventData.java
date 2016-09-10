package com.ldps.data;


//向资源组组添加、删除资源事件 data
public class ResAndResGroupRelChangeEventData {

	private Integer resourceId;
	private Integer resGroupId;
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	public Integer getResGroupId() {
		return resGroupId;
	}
	public void setResGroupId(Integer resGroupId) {
		this.resGroupId = resGroupId;
	}

	
	
}
