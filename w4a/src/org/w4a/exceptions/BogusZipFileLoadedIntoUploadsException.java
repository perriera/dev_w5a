package org.w4a.exceptions;

import java.io.File;

@SuppressWarnings("serial")
public class BogusZipFileLoadedIntoUploadsException extends W4AException {
	String zipEntry;
	public BogusZipFileLoadedIntoUploadsException(String zipEntry) {
		super(zipEntry);
		this.zipEntry = zipEntry;
	}
	
	@Override
	public String getIssue() {
		File f = new File(zipEntry);
		String zipName = f.getName().replace(".ini", ".zip");
		return "Only zip files from either Webflow.com or your zipped Angular 2 /src folder are supported : "+zipName;
	}
	
	@Override
	public String getPublicIssue() {
		File f = new File(zipEntry);
		String zipName = f.getName().replace(".ini", ".zip");
		String[] parts = zipName.split("_");
		if ( parts.length>1 ) 
			zipName = parts[1];
		return "Only zip files from either Webflow.com or your zipped Angular 2 /src folder are supported : "+zipName;
	}
	
}
