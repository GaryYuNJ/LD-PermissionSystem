package com.ldps.data;

import java.util.List;

import com.ldps.model.CusResourceRelModel;
import com.ldps.model.PermissionRecordModel;


public class AuthCusAndResPermissionEventData {
	
	List<CusResourceRelModel> cusResourceRelModel;
	PermissionRecordModel permRecordModel;

	public List<CusResourceRelModel> getCusResourceRelModel() {
		return cusResourceRelModel;
	}
	public void setCusResourceRelModel(List<CusResourceRelModel> cusResourceRelModel) {
		this.cusResourceRelModel = cusResourceRelModel;
	}
	public PermissionRecordModel getPermRecordModel() {
		return permRecordModel;
	}
	public void setPermRecordModel(PermissionRecordModel permRecordModel) {
		this.permRecordModel = permRecordModel;
	}
	
	
	
}
