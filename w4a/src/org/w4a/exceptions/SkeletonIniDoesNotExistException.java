package org.w4a.exceptions;

import java.io.File;

@SuppressWarnings("serial")
public class SkeletonIniDoesNotExistException extends W4AException {
	String zipEntry;
	public SkeletonIniDoesNotExistException(String zipEntry) {
		super(zipEntry);
		this.zipEntry = zipEntry;
	}
	
	@Override
	public String getIssue() {
		File f = new File(zipEntry);
		String zipName = f.getName().replace(".ini", ".zip");
		return "node_modules directory missing for : "+zipName;
	}
	
}
