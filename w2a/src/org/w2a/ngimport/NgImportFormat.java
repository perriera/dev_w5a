package org.w2a.ngimport;

import java.io.File;
import java.util.HashMap;
import org.w0a.IniFile;
import org.w0a.interfaces.SourceExtractorInterface;
import org.w2a.W2AIniFile;

@SuppressWarnings("serial")
abstract public class NgImportFormat extends HashMap<File,String> implements SourceExtractorInterface {

	W2AIniFile iniFile;
	
	public NgImportFormat(W2AIniFile iniFile) {
		this.iniFile = iniFile;
	}
	
	@Override
	public IniFile getXIniFile() {
		return this.iniFile;
	}

	@Override
	public void input() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void output() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
