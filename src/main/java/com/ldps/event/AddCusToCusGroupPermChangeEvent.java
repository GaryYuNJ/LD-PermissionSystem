package com.ldps.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import com.ldps.data.AddCusToCusGroupPermChangeEventData;
import com.ldps.data.AuthCusAndResPermissionEventData;

public class AddCusToCusGroupPermChangeEvent extends ApplicationEvent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AddCusToCusGroupPermChangeEvent(AddCusToCusGroupPermChangeEventData source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
}
