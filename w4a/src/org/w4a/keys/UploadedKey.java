package org.w4a.keys;

import java.io.File;

import org.w4a.exceptions.NotUploadedException;

public class UploadedKey {
	
	String key;

	public UploadedKey(String lockedkey) throws NotUploadedException {
		if ( lockedkey.contains(".part") )
			throw new NotUploadedException(lockedkey);
		this.key = lockedkey;
	}
	
	public UploadedKey(File file) throws NotUploadedException {
		this(file.getName());
	}
	
	public String getKey() {
		return key;
	}

}
