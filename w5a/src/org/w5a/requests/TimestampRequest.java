package org.w5a.requests;


public class TimestampRequest implements RequestInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5144319838721960635L;
	
	String timestamp;

	public void setTimestamp(String string) {
		this.timestamp = string;
	}

	@Override
	public void resolve() throws Exception {
		System.out.println(timestamp);
	}

	@Override
	public boolean isSuccessful() throws Exception {
		return true;
	}

	@Override
	public void request() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSuccessful(boolean state) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
