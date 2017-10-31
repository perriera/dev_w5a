package org.w8a.task;

import org.w6a.request.FreeportRequest;
import org.w6a.request.RequestInterface;
import org.w8a.server.PortManager;

@SuppressWarnings("serial")
public class ResolveFreeportRequest extends SocketTask  {

	int port;
	FreeportRequest request;
	
	public ResolveFreeportRequest(int port) {
		super(port);
		this.request = null;
		this.port = port;
	}

	@Override
	public void printStatus() {
		request.printStatus();
	}

	@Override
	public void resolve(RequestInterface request) throws Exception {
		this.request = (FreeportRequest)request;
		super.resolve(request);
	}
	
	@Override
	public void dowork() throws InterruptedException {
		int port = PortManager.getInstance().take();
		request.setFreeport(port);
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

