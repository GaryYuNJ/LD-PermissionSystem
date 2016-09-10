package com.ldps.event;

import org.springframework.context.ApplicationEvent;

import com.ldps.data.AddResToResGroupPermChangeEventData;

public class AddResToResGroupPermChangeEvent extends ApplicationEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AddResToResGroupPermChangeEvent(AddResToResGroupPermChangeEventData source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	
}
