package org.w4a.keys;

import java.io.File;

import org.w4a.exceptions.BadRequestKeyException;

public class UploadKey {
	
	String request;
	String key;
	UploadRequests uploadRequest;


	public UploadKey() {
	}
	
	public UploadKey(String lockedkey) throws BadRequestKeyException {
//		if ( !new File(lockedkey).exists() )
		if ( lockedkey.contains(".part") )
			uploadRequest = UploadRequests.UPLOADING;
	}
	
	public UploadKey(File file) throws BadRequestKeyException {
		this(file.getName());
	}
	
	public UserRequest getRequest() {
		return ( request.equals("F") ? UserRequest.FORWARD : UserRequest.REVERSE );
	}
	
}
