package org.w2a.exceptions;

import org.w0a.exceptions.NgExceptions;

@SuppressWarnings("serial")
public class Angular2CLINgTimedOutException extends NgExceptions {
	String line;
	String componentFilename;
	public Angular2CLINgTimedOutException(String line, String componentFilename) {
		super(line);
		this.line = line;
		this.componentFilename = componentFilename;
	}
	
	@Override
	public String getIssue() {
		return "Angular2 command timed out while processing: "+componentFilename +" line: "+line;
	}
	
}
