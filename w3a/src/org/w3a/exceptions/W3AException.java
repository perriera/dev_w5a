package org.w3a.exceptions;

import org.w0a.exceptions.NgExceptions;

@SuppressWarnings("serial")
public class W3AException extends NgExceptions {
	String iniFilename;
	public W3AException(String iniFilename) {
		super(iniFilename);
		this.iniFilename = iniFilename;
	}
	
	@Override
	public String getIssue() {
		return "IniFile missing for : "+iniFilename;
	}
	
}
