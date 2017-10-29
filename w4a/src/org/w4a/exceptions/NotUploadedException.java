package org.w4a.exceptions;

import java.io.File;

@SuppressWarnings("serial")
public class NotUploadedException extends W4AException {
	String zipEntry;
	public NotUploadedException(String zipEntry) {
		super(zipEntry);
		this.zipEntry = zipEntry;
	}
	
	@Override
	public String getIssue() {
		File f = new File(zipEntry);
		String zipName = f.getName().replace(".ini", ".zip");
		return "File not uploaded : "+zipName;
	}
	
}
