package com.ldps.crm.job;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ldps.controller.CustomerAPIController;
import com.ldps.crm.dao.CRMCustmemberModelMapper;
import com.ldps.crm.model.CRMCustmemberModel;
import com.ldps.dao.CustomerModelMapper;
import com.ldps.model.CustomerModel;

/*
	定时获取CRM新注册、修改用户
*/
public class SyncCustomerDataFromCRMJob {
	private static Logger logger = Logger
			.getLogger(SyncCustomerDataFromCRMJob.class);
	
	@Resource
	private CRMCustmemberModelMapper cRMCustmemberDao;
	
	@Resource
	CustomerModelMapper customerDao;
	
	public void syncData(){
		CustomerModel cModel = customerDao.selectLatestRecord();
		if(null != cModel && null != cModel.getCmMaintdate()){
			Date date = cModel.getCmMaintdate();
			List<CRMCustmemberModel> crmCusModels = cRMCustmemberDao.selectByMaintdate(date);
			//System.out.println("~~~~"+crmCusModels.size()+"~~~~");
			//循环处理。新增或者更新到mysql数据库
			if(null == crmCusModels || crmCusModels.size() >0){
				for(CRMCustmemberModel crmCusModel : crmCusModels){
					
					
					
				}
			}else{
				logger.warn("SyncCustomerDataFromCRMJob CRMCustmemberModelMapper.selectByMaintdate() return null.");
			}
			
		}else{
			logger.error("SyncCustomerDataFromCRMJob CRMCustmemberModelMapper selectLatestRecord  is null.");
		}
	}

}
