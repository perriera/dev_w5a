package org.w2a.exceptions;

import org.w0a.exceptions.NgExceptions;

@SuppressWarnings("serial")
public class Angular2CLINgException extends NgExceptions {
	String line;
	String component;
	public Angular2CLINgException(String line, String component) {
		super(line);
		this.line = line;
	}
	
	@Override
	public String getIssue() {
		return line+" : "+component;
	}
	
}
