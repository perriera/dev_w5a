package org.w4a.exceptions;

import org.w0a.exceptions.NgExceptions;

@SuppressWarnings("serial")
abstract public class W4AException extends NgExceptions {
	String iniFilename;
	public W4AException(String iniFilename) {
		super(iniFilename);
		this.iniFilename = iniFilename;
	}
	
	@Override
	abstract public String getIssue();
	
	public String getPublicIssue() {
		return getIssue();
	}
	
}
