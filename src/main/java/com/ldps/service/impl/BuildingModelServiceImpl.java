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

	@Override
	public BuildingModel queryBuilding(Integer id) {
		return buildingModelMapper.selectByPrimaryKey(id);
	}

	@Override
	public int save(BuildingModel buildingModel) {
		if(buildingModel.getId()==null){
			return buildingModelMapper.insert(buildingModel);			
		}else{
			return buildingModelMapper.updateByPrimaryKeySelective(buildingModel);
		}

	}

	@Override
	public int delete(Integer id) {
		return buildingModelMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<BuildingModel> queryBuildingWithPageIndex(String buildingName,
			Integer pageNo, Integer pageSize) {
		return buildingModelMapper.selectBuildingWithCondition(buildingName, pageNo, pageSize);
	}

	@Override
	public int queryCountByCondition(String buildingName) {
		
		return buildingModelMapper.selectCountWithCondition(buildingName);
	}
}
