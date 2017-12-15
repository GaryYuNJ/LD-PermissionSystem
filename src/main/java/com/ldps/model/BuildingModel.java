package com.ldps.model;

import java.util.List;

import com.ldps.data.ResourceData;

public class BuildingModel {
    private Integer id;

    private String name;
    
    private String floor;
    
    private List<ResourceData> resourceDatas;

	public List<ResourceData> getResourceDatas() {
		return resourceDatas;
	}

	public void setResourceDatas(List<ResourceData> resourceDatas) {
		this.resourceDatas = resourceDatas;
	}

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
    
    public boolean equals(Object obj)
    {
            if(!(obj instanceof Integer))
                    return false;
            return this.id.equals(obj);
    }
}