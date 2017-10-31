package org.w8a.server;

import java.util.concurrent.LinkedBlockingQueue;

@SuppressWarnings("serial")
public class PortManager extends LinkedBlockingQueue<Integer> {

	private static PortManager portManager = null;
	
	public static PortManager getInstance() throws InterruptedException {
		if ( portManager==null ) {
			portManager = new PortManager(2048,1024);
		}
		return portManager;
	}
	
	private PortManager(int base, int range) throws InterruptedException {
		for (int i=0; i<range; i++)
			this.put(new Integer(base+i));
	}

}
