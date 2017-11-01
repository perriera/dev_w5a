package org.w7a.tasks;

import org.w5a.requests.FreeportRequest;
import org.w5a.requests.UploadRequest;
import org.w7a.managers.PortManager;

@SuppressWarnings("serial")
abstract public class FreeportTask extends UploadRequest implements TaskInterface {
	
	FreeportRequest request;

	public FreeportTask(FreeportRequest request) throws InterruptedException {
		this.request = request;
		this.request.setAddress("localhost");
		this.request.setFreeport(PortManager.getInstance().take());
	}
	
	protected void finalize() throws Throwable {
		PortManager.getInstance().put(this.request.getFreeport());
		super.finalize();
	}
	
}
