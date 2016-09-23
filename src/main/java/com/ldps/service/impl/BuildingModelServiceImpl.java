package com.ldps.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ldps.dao.BuildingModelMapper;
import com.ldps.model.BuildingModel;
import com.ldps.service.IBuildingModelService;

@Service("iBuildingModelService")
public class BuildingModelServiceImpl implements IBuildingModelService {

	@Resource
	private BuildingModelMapper buildingModelMapper;
	
	@Override
	public List<BuildingModel> queryAll() {
		return buildingModelMapper.selectAll();
	}

	public BuildingModelMapper getBuildingModelMapper() {
		return buildingModelMapper;
	}

	public void setBuildingModelMapper(BuildingModelMapper buildingModelMapper) {
		this.buildingModelMapper = buildingModelMapper;
	}

}
