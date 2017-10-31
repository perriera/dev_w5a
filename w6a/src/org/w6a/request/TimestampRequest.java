package org.w6a.request;


public class TimestampRequest  implements RequestInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1200555403550163758L;
	
	String timestamp;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public void printStatus() {
		System.out.println(this.timestamp);
	}

	@Override
	public void resolve(RequestInterface request) throws Exception {
		// TODO Auto-generated method stub
		
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

