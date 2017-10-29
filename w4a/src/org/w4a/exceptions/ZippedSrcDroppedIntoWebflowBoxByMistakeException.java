package org.w4a.exceptions;

import java.io.File;

@SuppressWarnings("serial")
public class ZippedSrcDroppedIntoWebflowBoxByMistakeException extends W4AException {
	String zipEntry;
	public ZippedSrcDroppedIntoWebflowBoxByMistakeException(String zipEntry) {
		super(zipEntry);
		this.zipEntry = zipEntry;
	}
	
	@Override
	public String getIssue() {
		File f = new File(zipEntry);
		String zipName = f.getName().replace(".ini", ".zip");
		return "ZippedSrcDroppedIntoWebflowBoxByMistake : "+zipName;
	}

	public String getPublicIssue() {
		return "You mistakenly dropped a zipped /src directory in the Webflow box!";
	}
	
}
