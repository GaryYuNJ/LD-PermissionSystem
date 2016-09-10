package com.ldps.event;

import org.springframework.context.ApplicationEvent;
import com.ldps.data.RemoveCusFromCusGrpPermChangeEventData;

public class RemoveCusFromCusGrpPermChangeEvent extends ApplicationEvent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RemoveCusFromCusGrpPermChangeEvent(RemoveCusFromCusGrpPermChangeEventData source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
}
