package com.ldps.converter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;






import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ldps.data.CustomerGroupData;
import com.ldps.model.CustomerGroupModel;


@Service("customerGroyupModelConverter")
public class CustomerGroupModelConverter {
	private static Logger logger = Logger
			.getLogger(CustomerGroupModelConverter.class);
	
	
	public List<CustomerGroupData> processList(List<CustomerGroupModel> sourceList){
		
		List<CustomerGroupData> dataList = new ArrayList<CustomerGroupData>();
		if(null != sourceList){
			for(CustomerGroupModel source : sourceList){
				CustomerGroupData data = new CustomerGroupData();
				dataList.add(this.process(source, data));
			}
		}
		return dataList;
	}
	
	public CustomerGroupData process(CustomerGroupModel source, CustomerGroupData data){
		if(null == data){
			data = new CustomerGroupData();
		}
		if(null != source){
			data.setId(source.getId());
			data.setName(source.getName());
			data.setStatus(source.getStatus());
			data.setCreateDate(source.getCreateDate());
			data.setCreateUser(source.getCreateUser());
			if(null != source.getCreateDate()){
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				data.setCreateDateStr(sf.format(source.getCreateDate()));
			}
		}
		return data;
	}
	
}
