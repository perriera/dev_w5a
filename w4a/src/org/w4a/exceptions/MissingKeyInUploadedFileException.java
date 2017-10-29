package org.w4a.exceptions;

import java.io.File;

@SuppressWarnings("serial")
public class MissingKeyInUploadedFileException extends W4AException {
	String zipEntry;
	public MissingKeyInUploadedFileException(String zipEntry) {
		super(zipEntry);
		this.zipEntry = zipEntry;
	}
	
	@Override
	public String getIssue() {
		File f = new File(zipEntry);
		String zipName = f.getName().replace(".ini", ".zip");
		return "MissingKeyInUploadedFileException : "+zipName;
	}
	
}
