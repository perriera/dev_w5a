package org.w6a.request;

import java.io.Serializable;

public interface RequestInterface extends Serializable {

	public void resolve(RequestInterface request) throws Exception;
	
	public RequestInterface makeRequest();
	public RequestInterface resolveRequest(RequestInterface request);

	public void printStatus();
	
}
