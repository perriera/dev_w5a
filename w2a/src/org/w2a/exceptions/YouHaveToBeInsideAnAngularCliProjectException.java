package org.w2a.exceptions;

import org.w0a.exceptions.NgExceptions;

@SuppressWarnings("serial")
public class YouHaveToBeInsideAnAngularCliProjectException extends NgExceptions {
	
	public YouHaveToBeInsideAnAngularCliProjectException() {
		super("no w2a/mock-project found");
	}
	
	@Override
	public String getIssue() {
		return "You have to be inside an angular-cli project in order to use the generate command.";
	}
	
}

