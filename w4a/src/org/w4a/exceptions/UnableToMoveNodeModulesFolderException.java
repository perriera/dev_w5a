package org.w4a.exceptions;


@SuppressWarnings("serial")
public class UnableToMoveNodeModulesFolderException extends W4AException {
	String iniFilename;
	public UnableToMoveNodeModulesFolderException(String iniFilename) {
		super(iniFilename);
		this.iniFilename = iniFilename;
	}
	
	@Override
	public String getIssue() {
		return "Unable to move node_modules folder for : "+iniFilename;
	}
	
}
