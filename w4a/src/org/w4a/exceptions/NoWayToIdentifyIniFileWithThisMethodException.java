package org.w4a.exceptions;


@SuppressWarnings("serial")
public class NoWayToIdentifyIniFileWithThisMethodException extends W4AException {
	String iniFilename;
	public NoWayToIdentifyIniFileWithThisMethodException(String iniFilename) {
		super(iniFilename);
		this.iniFilename = iniFilename;
	}
	
	@Override
	public String getIssue() {
		return "NoWayToIdentifyIniFileWithThisMethodException : "+iniFilename;
	}
}
