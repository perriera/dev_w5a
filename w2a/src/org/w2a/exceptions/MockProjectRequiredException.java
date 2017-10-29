package org.w2a.exceptions;

import org.w0a.exceptions.NgExceptions;

@SuppressWarnings("serial")
public class MockProjectRequiredException extends NgExceptions {
	
	public MockProjectRequiredException() {
		super("no w2a/mock-project found");
	}
	
	@Override
	public String getIssue() {
		return "You need to setup a mock-project (ex. ng new mock-project ) in the w2a directory!";
	}
	
}

