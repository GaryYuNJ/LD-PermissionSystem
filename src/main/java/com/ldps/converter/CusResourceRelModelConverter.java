package com.ldps.converter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ldps.data.CusResourceRelData;
import com.ldps.model.CusResourceRelModel;

@Service("cusResourceRelModelConverter")
public class CusResourceRelModelConverter {
	
	@Resource
	ResourceModelConverter resourceModelConverter;
	
	public List<CusResourceRelData> processList(List<CusResourceRelModel> sourceList){
		
		List<CusResourceRelData> dataList = new ArrayList<CusResourceRelData>();
		if(null != sourceList){
			for(CusResourceRelModel source : sourceList){
				CusResourceRelData data = new CusResourceRelData();
				dataList.add(this.process(source, data));
			}
		}
		return dataList;
	}
	
	public CusResourceRelData process(CusResourceRelModel source,CusResourceRelData data){
		if(null ==data){
			data = new CusResourceRelData();
		}
		
		if(null != source){
			data.setCustomerId(source.getCustomerId());
			data.setCreateDate(source.getCreateDate());
			data.setCreateUser(source.getCreateUser());
			data.setEnable(source.getEnable());
			data.setFromShared(source.getFromShared());
			data.setResourceData(resourceModelConverter.process(source.getResourceModel(), null));
			data.setResourceId(source.getResourceId());
			data.setStartDate(source.getStartDate());
			data.setEndDate(source.getEndDate());
		}
		
		return data;
	}
	
}
