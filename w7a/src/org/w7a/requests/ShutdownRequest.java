package org.w7a.requests;

import org.w5a.requests.RequestInterface;
import org.w5a.requests.UploadRequest;

public class ShutdownRequest extends UploadRequest implements RequestInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -325838185099451063L;

	public ShutdownRequest() {
	}

	@Override
	public void resolve() throws Exception {

		this.setSuccessful(true);

	}

}
