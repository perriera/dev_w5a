package org.w0a.webflow.types;


import org.w0a.IniFile;
import org.w0a.exceptions.FeatureNotImplementedException;
import org.w0a.types.SourceBinaryFileEntry;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.FileHeader;

public class WebflowBinaryFileEntry extends WebflowFileEntry implements SourceBinaryFileEntry {

	public WebflowBinaryFileEntry(ZipFile zipFile, FileHeader fileHeader, IniFile iniFile) {
		super(zipFile, fileHeader, iniFile);
	}


	@Override
	public byte[] getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setContent(byte[] bytesArray) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void input() throws Exception {
		throw new FeatureNotImplementedException();
	}
	
	@Override
	public void output() throws Exception {
		throw new FeatureNotImplementedException();
	}
	
}
