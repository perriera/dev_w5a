package org.w7a.tasks;

import org.w5a.requests.UploadRequest;
import org.w7a.managers.PortManager;

@SuppressWarnings("serial")
public class ShutdownTask extends UploadTask {

	public ShutdownTask(UploadRequest request) throws InterruptedException {
		super(request);
	}

	@Override
	public void resolve() throws Exception {
		
		this.setSuccessful(true);
		
		PortManager.getInstance().put(this.request.getFreeport());
		
	}
	
}
