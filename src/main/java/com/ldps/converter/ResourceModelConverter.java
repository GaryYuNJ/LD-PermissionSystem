package com.ldps.converter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ldps.dao.BuildingModelMapper;
import com.ldps.dao.ResourceTypeModelMapper;
import com.ldps.data.ResourceData;
import com.ldps.model.BuildingModel;
import com.ldps.model.ResourceModel;
import com.ldps.model.ResourceTypeModel;

@Service("resourceModelConverter")
public class ResourceModelConverter {
	private static Logger logger = Logger
			.getLogger(ResourceModelConverter.class);
	@Resource
	ResourceGroupModelConverter resourceGroupModelConverter;
	
	@Resource
	BuildingModelMapper buildingModelDao;
	@Resource
	ResourceTypeModelMapper resourceTypeModelDao;
	
	public List<ResourceData> processList(List<ResourceModel> sourceList){
		
		List<ResourceData> dataList = new ArrayList<ResourceData>();
		if(null != sourceList){
			for(ResourceModel source : sourceList){
				ResourceData data = new ResourceData();
				dataList.add(this.process(source, data));
			}
		}
		
		return dataList;
	}
	
	public ResourceData process(ResourceModel source,ResourceData data){
		if(null == data){
			data = new ResourceData();
		}
		if(null != source){
			data.setBuildingId(source.getBuildingId());
			try{
				BuildingModel bModel = buildingModelDao.selectByPrimaryKey(source.getBuildingId());
				if(null != bModel){
					data.setBuildingName(bModel.getName());
				}else
					data.setBuildingName("");
			}catch(Exception e){
				logger.error("ResourceModelConverter.process error."+e);
			}
			data.setChr1(source.getChr1());
			data.setChr2(source.getChr2());
			data.setCreateDate(source.getCreateDate());
			data.setCreateUser(source.getCreateUser());
			data.setFloor(source.getFloor());
			data.setId(source.getId());
			data.setMac(source.getMac());
			data.setName(source.getName());
			data.setNum1(source.getNum1());
			data.setNum2(source.getNum2());
			data.setPassword(source.getPassword());
			try{
				data.setResourceGroups(resourceGroupModelConverter.processList(source.getResourceGroups()));
			}catch(Exception e){
				logger.error("ResourceModelConverter.process error."+e);
			}
			data.setrInt1(source.getrInt1());
			data.setrInt2(source.getrInt2());
			data.setSequence(source.getSequence());
			data.setShareEnable(source.getShareEnable());
			data.setStatus(source.getStatus());
			data.setTypeId(source.getTypeId());
			try{
				ResourceTypeModel rTModel = resourceTypeModelDao.selectByPrimaryKey(source.getTypeId());
				if(null != rTModel){
					data.setTypeName(rTModel.getName());
				}else
					data.setTypeName("");
			}catch(Exception e){
				logger.error("ResourceModelConverter.process error."+e);
			}
			data.setVc1(source.getVc1());
			data.setVc2(source.getVc2());		
		}
		
		
		return data;
	}
	
}
