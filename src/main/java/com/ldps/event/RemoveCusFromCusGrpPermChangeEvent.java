package com.ldps.event;

import org.springframework.context.ApplicationEvent;

import com.ldps.data.CusAndCusGrpRelChangeEventData;

public class RemoveCusFromCusGrpPermChangeEvent extends ApplicationEvent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RemoveCusFromCusGrpPermChangeEvent(CusAndCusGrpRelChangeEventData source) {
		super(source);
	}
}
