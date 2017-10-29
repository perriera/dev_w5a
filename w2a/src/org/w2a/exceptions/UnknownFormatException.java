package org.w2a.exceptions;

import org.w0a.exceptions.NgExceptions;

@SuppressWarnings("serial")
public class UnknownFormatException extends NgExceptions {
	String format;
	public UnknownFormatException(String format) {
		super(format);
		this.format = format;
	}
	
	@Override
	public String getIssue() {
		return "The format specified in the ini file is unknown to the parser: "+format;
	}
	
}
