package org.w3a.exceptions;

import java.io.File;

@SuppressWarnings("serial")
public class IniFileMissingException extends W3AException {
	String iniFilename;
	public IniFileMissingException(String iniFilename) {
		super(iniFilename);
		this.iniFilename = iniFilename;
	}
	
	@Override
	public String getIssue() {
		File f = new File(iniFilename);
		String zipName = f.getName().replace(".ini", ".zip");
		return "IniFile missing for : "+zipName;
	}
	
}
