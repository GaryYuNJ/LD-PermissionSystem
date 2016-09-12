package com.ldps.event.listener;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;  
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.ldps.dao.CusGroupResGroupRelModelMapper;
import com.ldps.data.CusAndCusGrpRelChangeEventData;
import com.ldps.event.AddCusToCusGroupPermChangeEvent;
import com.ldps.model.CusGroupResGroupRelModel;
import com.ldps.model.CusGrpResourceRelModel;
import com.ldps.model.CusResourceRelModel;
import com.ldps.model.PermissionRecordModel;
import com.ldps.service.ICusGrpResourceRelService;
import com.ldps.service.ICusResourceRelService;
import com.ldps.service.IUserService;


/*
	用户组添加用户带来的用户与资源关系更改 异步 监听
*/
@Component
public class AddCusToCusGroupPermChangeListener implements ApplicationListener<AddCusToCusGroupPermChangeEvent> {
 
	@Resource
	ICusResourceRelService iCusResourceRelService;
	@Resource
	ICusGrpResourceRelService iCusGrpResourceRelService;
	@Resource
	private CusGroupResGroupRelModelMapper cusGroupResGroupRelDao;
	@Resource
	private IUserService userService;
	
	@Async
	@Override
	public void onApplicationEvent(AddCusToCusGroupPermChangeEvent event) {
		// TODO Auto-generated method stub
		CusAndCusGrpRelChangeEventData eData = (CusAndCusGrpRelChangeEventData)event.getSource();

		//用户对资源的关系
		PermissionRecordModel permRecordModel = new PermissionRecordModel();
		permRecordModel.setObjectRelation(5); // 用户组添加用户
		permRecordModel.setActionType(1);
		permRecordModel.setCreateDate(new Date());
		permRecordModel.setCgroupId(eData.getCusGroupId());
		permRecordModel.setCustomerId(eData.getCustomerId());
		
		//获取用户组对应的资源组权限
		//用户对资源组的关系
		List<CusGroupResGroupRelModel> cusGrpResGrpRelModels = cusGroupResGroupRelDao.selectByCusGroupId(eData.getCusGroupId());
		for(CusGroupResGroupRelModel cusGrpResGrpRelModel: cusGrpResGrpRelModels){
			try{
				iCusResourceRelService.jointAuthorizeResGrpPermission(eData.getCustomerId(), cusGrpResGrpRelModel.getRgroupId(), 
						cusGrpResGrpRelModel.getStartDate(), cusGrpResGrpRelModel.getEndDate(), 
						userService.getSessionUserId(), permRecordModel);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		//获取用户组对应的资源权限
		List<CusGrpResourceRelModel> groupRelModels = iCusGrpResourceRelService.queryByCusGroupId(eData.getCusGroupId());
		CusResourceRelModel relModelTemp = null;
		for(CusGrpResourceRelModel cusGrpResRelmodel : groupRelModels){
			try{
				relModelTemp = new CusResourceRelModel();
				relModelTemp.setEnable(cusGrpResRelmodel.getEnable());
				relModelTemp.setEndDate(cusGrpResRelmodel.getStartDate());
				relModelTemp.setStartDate(cusGrpResRelmodel.getEndDate());
				relModelTemp.setResourceId(cusGrpResRelmodel.getResourceId());
				relModelTemp.setCustomerId(eData.getCustomerId());
				relModelTemp.setFromShared("N");
				relModelTemp.setCreateDate(new Date());
				relModelTemp.setCreateUser(userService.getSessionUserId());
				
				iCusResourceRelService.authorizeResPermission(relModelTemp, permRecordModel);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
