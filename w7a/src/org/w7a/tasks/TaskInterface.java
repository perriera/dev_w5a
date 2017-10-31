package org.w7a.tasks;

import org.w5a.requests.RequestInterface;

public interface TaskInterface extends RequestInterface {

	void resolve() throws Exception;

}
