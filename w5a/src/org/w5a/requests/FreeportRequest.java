package org.w5a.requests;

abstract public class FreeportRequest implements RequestInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3375455506878919228L;
	
	String address;
	int freeport;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setFreeport(int freeport) {
		this.freeport = freeport;
	}

	public int getFreeport() {
		return freeport;
	}
	
}
