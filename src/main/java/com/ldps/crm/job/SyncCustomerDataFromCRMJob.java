package com.ldps.crm.job;

import java.util.Date;

import javax.annotation.Resource;

import com.ldps.crm.dao.CRMCustmemberModelMapper;
import com.ldps.crm.model.CRMCustmemberModel;



public class SyncCustomerDataFromCRMJob {

	@Resource
	private CRMCustmemberModelMapper cRMCustmemberDao;

	public void syncData(){
		CRMCustmemberModel modle = cRMCustmemberDao.selectByPrimaryKey("0000791552");
		System.out.println(new Date());
		System.out.println(modle.getCmmobile1());
	}

}
