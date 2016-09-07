package com.ldps.event.listener;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;  
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.ldps.dao.CusGroupResGroupRelModelMapper;
import com.ldps.dao.CustomerResGroupRelModelMapper;
import com.ldps.data.AddResToResGroupPermChangeEventData;
import com.ldps.data.AuthCusAndResPermissionEventData;
import com.ldps.event.AddResToResGroupPermChangeEvent;
import com.ldps.event.AuthCusAndResPermissionEvent;
import com.ldps.model.CusGroupResGroupRelModel;
import com.ldps.model.CusGrpResourceRelModel;
import com.ldps.model.CusResourceRelModel;
import com.ldps.model.CustomerResGroupRelModel;
import com.ldps.model.PermissionRecordModel;
import com.ldps.service.ICusGrpResourceRelService;
import com.ldps.service.ICusResourceRelService;


/*
	资源组添加资源带来的用户与资源关系更改 异步 监听
*/
@Component
public class AddResToResGroupPermChangeListener implements ApplicationListener<AddResToResGroupPermChangeEvent> {

	@Resource
	ICusResourceRelService iCusResourceRelService;
	@Resource
	CusGroupResGroupRelModelMapper cusGroupResGroupRelDao;
	@Resource
	ICusGrpResourceRelService iCusGrpResourceRelService;
	@Resource
	CustomerResGroupRelModelMapper customerResGroupRelDao;
	
	@Async
	@Override
	public void onApplicationEvent(AddResToResGroupPermChangeEvent event) {
		// TODO Auto-generated method stub
		AddResToResGroupPermChangeEventData eData = (AddResToResGroupPermChangeEventData)event.getSource();
		
		//用户对资源的关系
		PermissionRecordModel permRecordModel = new PermissionRecordModel();
		permRecordModel.setObjectRelation(6); // 资源组添加资源
		permRecordModel.setActionType(1);
		permRecordModel.setCreateDate(new Date());
		permRecordModel.setResourceId(eData.getResourceId());
		permRecordModel.setRgroupId(eData.getResGroupId());
		
		//获取资源组关联的用户组
		List<CusGroupResGroupRelModel> cusGrpResGrpRelModels = cusGroupResGroupRelDao.selectByResGroupId(eData.getResGroupId());
		//授权用户组与资源
		CusGrpResourceRelModel cusGrpResRelModel = new CusGrpResourceRelModel();
		for(CusGroupResGroupRelModel  cusGrpResGrpRelModel: cusGrpResGrpRelModels){
			try{
				cusGrpResRelModel.setCgroupId(cusGrpResGrpRelModel.getCgroupId());
				cusGrpResRelModel.setCreateDate(new Date());
				cusGrpResRelModel.setCreateUser(0);
				cusGrpResRelModel.setEnable("Y");
				cusGrpResRelModel.setEndDate(cusGrpResGrpRelModel.getEndDate());
				cusGrpResRelModel.setResourceId(eData.getResourceId());
				cusGrpResRelModel.setStartDate(cusGrpResGrpRelModel.getStartDate());
				
				iCusGrpResourceRelService.jointAuthCusGrpResPermission(cusGrpResRelModel, permRecordModel);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		//获取资源组关联的用户
		List<CustomerResGroupRelModel> cusResGrpRelModels = customerResGroupRelDao.selectByResGroupId(eData.getResGroupId());
		CusResourceRelModel relModelTemp = null;
		//授权用户与资源
		for(CustomerResGroupRelModel cusResGrpRelModel:  cusResGrpRelModels){
			try{
				relModelTemp = new CusResourceRelModel();
				relModelTemp.setEnable("Y");
				relModelTemp.setEndDate(cusResGrpRelModel.getStartDate());
				relModelTemp.setStartDate(cusResGrpRelModel.getEndDate());
				relModelTemp.setResourceId(eData.getResourceId());
				relModelTemp.setCustomerId(cusResGrpRelModel.getCustomerId());
				relModelTemp.setFromShared("N");
				relModelTemp.setCreateDate(new Date());
				relModelTemp.setCreateUser(0L);
				
				iCusResourceRelService.authorizeResPermission(relModelTemp, permRecordModel);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
