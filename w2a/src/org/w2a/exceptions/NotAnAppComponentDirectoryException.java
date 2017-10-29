package org.w2a.exceptions;

import org.w0a.exceptions.NgExceptions;

@SuppressWarnings("serial")
public class NotAnAppComponentDirectoryException extends NgExceptions {
	String component;
	String desDir;
	public NotAnAppComponentDirectoryException(String component, String desDir) {
		super(component);
		this.component = component;
		this.desDir = desDir;
	}
	
	@Override
	public String getIssue() {
		return "You need to assign a path to an actual ng cli project 'src' directory ( ex. destination=~/dev/project/src ) in the ini file: "+desDir;
	}
	
}
