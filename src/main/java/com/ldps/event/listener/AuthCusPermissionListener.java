package com.ldps.event.listener;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;  
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.ldps.data.AuthCusAndResPermissionEventData;
import com.ldps.event.AuthCusAndResPermissionEvent;
import com.ldps.model.CusResourceRelModel;
import com.ldps.service.ICusResourceRelService;


/*
	批量向用户授权异步监听
*/
@Component
public class AuthCusPermissionListener implements ApplicationListener<AuthCusAndResPermissionEvent> {

	@Resource
	ICusResourceRelService iCusResourceRelService;
	
	@Async
	@Override
	public void onApplicationEvent(AuthCusAndResPermissionEvent event) {
		// TODO Auto-generated method stub
		AuthCusAndResPermissionEventData eData = (AuthCusAndResPermissionEventData)event.getSource();

		for(CusResourceRelModel rModel : eData.getCusResourceRelModel()){
			try{
				iCusResourceRelService.authorizeResPermission(rModel, eData.getPermRecordModel());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
