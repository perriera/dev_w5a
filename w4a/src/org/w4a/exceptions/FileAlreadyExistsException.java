package org.w4a.exceptions;

import java.io.File;

@SuppressWarnings("serial")
public class FileAlreadyExistsException extends W4AException {
	String zipEntry;
	public FileAlreadyExistsException(String zipEntry) {
		super(zipEntry);
		this.zipEntry = zipEntry;
	}
	
	@Override
	public String getIssue() {
		File f = new File(zipEntry);
		String zipName = f.getName().replace(".ini", ".zip");
		return "File already exists at location (and overwrite is set to false) : "+zipName;
	}
	
}
