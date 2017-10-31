package org.w5a.requests;


public class FreeportRequest implements RequestInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1987888190397588968L;
	
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

	@Override
	public void resolve() throws Exception {
		System.out.println(freeport);
	}
	
}
