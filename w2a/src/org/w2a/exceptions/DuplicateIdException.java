package org.w2a.exceptions;

import org.w0a.exceptions.NgExceptions;

@SuppressWarnings("serial")
public class DuplicateIdException extends NgExceptions {
	String component;
	String id;
	String tagName;
	public DuplicateIdException(String component, String id, String tagName) {
		super(component);
		this.component = component;
		this.id = id;
		this.tagName = tagName;
	}
	
	@Override
	public String getIssue() {
		return "You have used the same id for more than one HTML element: <"+tagName+" id='"+id+"'/> for "+component;
	}
	
}
