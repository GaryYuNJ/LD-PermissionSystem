package com.ldps.service;

import com.ldps.model.MessageRecord;

public interface IMessageService {

	int save(MessageRecord messageRecord);
	int sendMessage(String message,String toMobile);
	
}
