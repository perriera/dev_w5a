package org.w2a.exceptions;

import org.w0a.exceptions.NgExceptions;

@SuppressWarnings("serial")
public class NgComponentNeedsAnIdException extends NgExceptions {
	String component;
	String page;
	public NgComponentNeedsAnIdException(String component, String page) {
		super(component);
		this.component = component;
		this.page = page;
	}
	
	@Override
	public String getIssue() {
		return "You need to assign an Id for ng-component=\""+component+"\" on page: "+page;
	}
	
}
