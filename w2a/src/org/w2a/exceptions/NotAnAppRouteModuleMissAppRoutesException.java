package org.w2a.exceptions;

import org.w0a.exceptions.NgExceptions;

@SuppressWarnings("serial")
public class NotAnAppRouteModuleMissAppRoutesException extends NgExceptions {
	String component;
	String page;
	public NotAnAppRouteModuleMissAppRoutesException(String component, String page) {
		super(component);
		this.component = component;
		this.page = page;
	}
	
	@Override
	public String getIssue() {
		return "You need to assign an ng-router ( ex. <div ng-router=\"home\"> ) as a Custom Attribute on page: "+page;
	}
	
}
