package org.w5a.requests;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

abstract public class FreeportRequest implements RequestInterface {

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
	
}
