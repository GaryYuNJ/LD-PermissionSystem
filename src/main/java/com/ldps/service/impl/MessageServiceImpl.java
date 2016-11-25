package com.ldps.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ldps.dao.MessageRecordMapper;
import com.ldps.model.MessageRecord;
import com.ldps.service.IMessageService;
import com.ldps.tools.MessageTool;

@Service("IMessageService")
public class MessageServiceImpl implements IMessageService {

	@Resource
	private MessageRecordMapper messageDao;
	
	@Override
	public int save(MessageRecord messageRecord) {
		return messageDao.insert(messageRecord);
	}
	
	public int sendMessage(String message,String toMobile){
		String result=MessageTool.sendMessage(message, toMobile);
		MessageRecord mr=new MessageRecord();
		mr.setMobile(toMobile);
		mr.setCreateDate(new Date());
		mr.setContent(message);
		JSONObject resultObj= JSON.parseObject(result);
		if(null!=resultObj&null!=resultObj.get("status")){
			mr.setState((int)resultObj.get("status"));
		}else{
			mr.setState(-1000);
		}
		return save(mr);
	}
}
