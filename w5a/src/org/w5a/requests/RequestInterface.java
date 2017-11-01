package org.w5a.requests;

import java.io.Serializable;

public interface RequestInterface extends Serializable {

	void request() throws Exception;
	void resolve() throws Exception;
	boolean isSuccessful() throws Exception;
	void setSuccessful(boolean state) throws Exception;

}
