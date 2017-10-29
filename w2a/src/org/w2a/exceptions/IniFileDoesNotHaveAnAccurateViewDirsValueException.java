package org.w2a.exceptions;

import org.w0a.exceptions.NgExceptions;
import org.w2a.W2AIniFile;

@SuppressWarnings("serial")
public class IniFileDoesNotHaveAnAccurateViewDirsValueException extends NgExceptions {
	
	W2AIniFile iniFile;
	String viewsDir;
	
	public IniFileDoesNotHaveAnAccurateViewDirsValueException(W2AIniFile iniFile) throws Exception {
		super(iniFile.getFilename());
		this.iniFile = iniFile;
		this.viewsDir = iniFile.getViewsDir();
	}
	
	@Override
	public String getIssue() {
		return "In the iniFile: "+iniFile.getFilename()+" you have either not specified a viewsDir value or the one you have specified is not accurate: "+viewsDir;
	}
	
}
