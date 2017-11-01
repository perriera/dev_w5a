package org.w5a.requests;

abstract public class FileRequest extends FreeportRequest implements RequestInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1525486380440006521L;
	
	public String filename;
	public byte[] buffer;

	public FileRequest(String filename) {
		this.filename = filename;
	}
	
	public FileRequest() {
		
	}
	
}
