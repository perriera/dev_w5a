package org.w7a.tasks;

import org.w5a.requests.FreeportRequest;
import org.w7a.managers.PortManager;

@SuppressWarnings("serial")
public class FreeportTask extends FreeportRequest implements TaskInterface {

	public FreeportTask(FreeportRequest request) throws InterruptedException {
		int freeport = PortManager.getInstance().take();
		request.setFreeport(freeport); 
	}

	@Override
	public void resolve() {
	}

}




