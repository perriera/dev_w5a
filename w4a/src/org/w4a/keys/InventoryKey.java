package org.w4a.keys;

import java.io.File;

import org.w4a.exceptions.BadInventoryKeyException;

public class InventoryKey implements InventoryKeyInterface {
	
	String token;
	String key;

	public static String format(String token, String key) {
		return token+"_"+key;
	}
	
	public InventoryKey() {
	}
	
	public InventoryKey(String lockedkey) throws BadInventoryKeyException {
		String[] parts = lockedkey.split("_");
		if ( parts.length<1 )
			throw new BadInventoryKeyException(lockedkey);
		else {
			this.token = parts[0];
			this.key = lockedkey.substring(this.token.length()+1);
		}
	}
	
	public InventoryKey(File file) throws BadInventoryKeyException {
		this(file.getName());
	}
	
	public InventoryKey(RequestKey request) throws BadInventoryKeyException {
		this(request.getKey());
	}
	
	public String getKey() {
		return key;
	}

	public String getToken() {
		return token;
	}
	
}
