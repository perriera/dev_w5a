package org.w8a.task;

import java.util.Date;

import org.w6a.request.RequestInterface;
import org.w6a.request.TimestampRequest;

@SuppressWarnings("serial")
public class ResolveTimestampRequest  implements TaskInterface {

	TimestampRequest request;
	
	public ResolveTimestampRequest(TimestampRequest request) {
		this.request = request;
	}

	@Override
	public void resolve(RequestInterface ignore) {
		request.setTimestamp(new Date().toString());
	}

	@Override
	public void printStatus() {
		request.printStatus();
	}

	@Override
	public RequestInterface makeRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequestInterface resolveRequest(RequestInterface request) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

