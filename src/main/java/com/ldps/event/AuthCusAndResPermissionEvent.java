package com.ldps.event;

import org.springframework.context.ApplicationEvent;

import com.ldps.data.AuthCusAndResPermissionEventData;

public class AuthCusAndResPermissionEvent extends ApplicationEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AuthCusAndResPermissionEvent(AuthCusAndResPermissionEventData source) {
		super(source);
	}
	
}
