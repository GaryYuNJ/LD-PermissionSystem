package com.ldps.model;

public class BuildingModel {
    private Integer id;

    private String name;
    
    private String floor;

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getFloor() {
  		return floor;
  	}

  	public void setFloor(String floor) {
  		this.floor = floor;
  	}
  	
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}