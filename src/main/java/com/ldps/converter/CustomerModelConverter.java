package com.ldps.converter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;




import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ldps.data.CustomerData;
import com.ldps.model.CustomerModel;

@Service("customerModelConverter")
public class CustomerModelConverter {
	private static Logger logger = Logger
			.getLogger(CustomerModelConverter.class);
	
	
	public List<CustomerData> processList(List<CustomerModel> sourceList){
		
		List<CustomerData> dataList = new ArrayList<CustomerData>();
		if(null != sourceList){
			for(CustomerModel source : sourceList){
				CustomerData data = new CustomerData();
				dataList.add(this.process(source, data));
			}
		}
		
		return dataList;
	}
	
	public CustomerData process(CustomerModel source, CustomerData data){
		if(null == data){
			data = new CustomerData();
		}
		if(null != source){
			data.setId(source.getId());
			data.setName(source.getCmName());
			data.setMobile(source.getCmMobile1());
			data.setBirthday(source.getCmBirthday());
			if(null != source.getCmBirthday()){
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				data.setBirthdayStr(sf.format(source.getCmBirthday()));
			}
			data.setSex(source.getCmSex());
			data.setRelation(source.getCmRelation());
			data.setPassword(source.getCmPwd());
			data.setEmail(source.getCmEmail());
			data.setExtendSpecificFlag(source.getExtendSpecificFlag());
		}
		
		
		return data;
	}
	
}
