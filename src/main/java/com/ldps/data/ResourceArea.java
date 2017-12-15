package com.ldps.data;

import java.util.List;

public class ResourceArea {
	
	 private Integer buildingId;

	 private String buildingName;
	
	 private List<ResourceFloor> floors;

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public List<ResourceFloor> getFloors() {
		return floors;
	}

	public void setFloors(List<ResourceFloor> floors) {
		this.floors = floors;
	}
	
}
