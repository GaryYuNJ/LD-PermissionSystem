package com.ldps.event;

import org.springframework.context.ApplicationEvent;

import com.ldps.data.AddCusToCusGroupPermChangeEventData;

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
