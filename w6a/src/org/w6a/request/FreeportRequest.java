package org.w6a.request;


public class FreeportRequest extends SocketRequest implements RequestInterface {

	public FreeportRequest(int port) {
		super(port);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2290194954275601763L;
	
	int freeport;

	public int getFreeport() {
		return freeport;
	}

	public void setFreeport(int freeport) {
		this.freeport = freeport;
	}

	@Override
	public void printStatus() {
		System.out.println(this.freeport);
	}

	@Override
	public RequestInterface makeRequest() {
		return this;
	}

	@Override
	public RequestInterface resolveRequest(RequestInterface request) {
		// TODO Auto-generated method stub
		return null;
	}

}

