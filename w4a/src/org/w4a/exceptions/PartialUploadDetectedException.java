package org.w4a.exceptions;

import java.io.File;

@SuppressWarnings("serial")
public class PartialUploadDetectedException extends W4AException {
	
	static String msg = "Only part of the download was received : ";
	String zipEntry;
	
	public PartialUploadDetectedException(String zipEntry) {
		super(zipEntry);
		this.zipEntry = zipEntry;
	}
	
	@Override
	public String getIssue() {
		File f = new File(zipEntry);
		String zipName = f.getName().replace(".ini", ".zip");
		return msg+zipName;
	}
	
	@Override
	public String getPublicIssue() {
		File f = new File(zipEntry);
		String zipName = f.getName().replace(".ini", ".zip");
		String[] parts = zipName.split("_");
		if ( parts.length>1 ) 
			zipName = parts[1];
		return msg+zipName;
	}
	
}
