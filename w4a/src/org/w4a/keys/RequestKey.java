package org.w4a.keys;

import java.io.File;

import org.w4a.exceptions.BadRequestKeyException;

public class RequestKey {
	
	String request;
	String key;
	UserRequest userRequest;

	public static boolean isForward(File zipFile) {
		return zipFile.getName().charAt(0) == 'F';
	}
	
	public static String format(String key, UserRequest userRequest) {
		if  (userRequest == UserRequest.FORWARD ) 
			return "F-"+key;
		else 
			return "R-"+key;
	}
	
	public RequestKey() {
	}
	
	boolean isValidKey(String part) {
		return part.length()==1 && ( part.charAt(0) == 'F' || part.charAt(0) == 'R' );
	}
	
	public RequestKey(String lockedkey) throws BadRequestKeyException {
		String[] parts = lockedkey.split("-");
		if ( parts.length<1 || lockedkey.length()<3 )
			throw new BadRequestKeyException(lockedkey);
		else {
			if ( !isValidKey(parts[0]) || lockedkey.charAt(1) != '-' ) 
				throw new BadRequestKeyException(lockedkey);
			else {
				this.request = parts[0];
				this.key = lockedkey.substring(2);
			}
		}
	}
	
	public RequestKey(File file) throws BadRequestKeyException {
		this(file.getName());
	}
	
	public RequestKey(UploadedKey uploaded) throws BadRequestKeyException {
		this(uploaded.getKey());
	}

	public UserRequest getRequest() {
		return ( request.equals("F") ? UserRequest.FORWARD : UserRequest.REVERSE );
	}
	
	public String getKey() {
		return key;
	}
	
	
}
