package com.ldps.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ldps.data.ResourceGroupData;
import com.ldps.model.ResourceGroupModel;

@Service("resourceGroupModelConverter")
public class ResourceGroupModelConverter {
	
	public List<ResourceGroupData> processList(List<ResourceGroupModel> sourceList){
		
		List<ResourceGroupData> dataList = new ArrayList<ResourceGroupData>();
		if(null !=sourceList){
			for(ResourceGroupModel source : sourceList){
				ResourceGroupData data = new ResourceGroupData();
				dataList.add(this.process(source, data));
			}
		}
		return dataList;
	}
	
	public ResourceGroupData process(ResourceGroupModel source,ResourceGroupData data){
		if(null ==data){
			data = new ResourceGroupData();
		}
		if(null !=source){
			data.setChr1(source.getChr1());
			data.setChr2(source.getChr2());
			data.setCreateDate(source.getCreateDate());
			data.setCreateUser(source.getCreateUser());
			data.setId(source.getId());
			data.setName(source.getName());
			data.setNum1(source.getNum1());
			data.setNum2(source.getNum2());
			data.setStatus(source.getStatus());
			data.setVc1(source.getVc1());
			data.setVc2(source.getVc2());	
			data.setIsPublic(source.getIsPublic());
		}
		return data;
	}
	
}
