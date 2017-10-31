package org.w5a.requests;


public class TimestampRequest implements RequestInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2444095163722856011L;
	String timestamp;

	public void setTimestamp(String string) {
		this.timestamp = string;
	}

	@Override
	public void resolve() throws Exception {
		System.out.println(timestamp);
	}

}
