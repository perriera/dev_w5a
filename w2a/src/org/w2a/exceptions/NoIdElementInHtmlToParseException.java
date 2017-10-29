package org.w2a.exceptions;

import org.w0a.exceptions.NgExceptions;

@SuppressWarnings("serial")
public class NoIdElementInHtmlToParseException extends NgExceptions {
	String html;
	public NoIdElementInHtmlToParseException(String html) {
		super(html);
		this.html = html;
	}
	
	@Override
	public String getIssue() {
		return "The PrettyPepper parser cannot handle more than one <div> at the same time: "+html;
	}
	
}
