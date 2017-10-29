package org.w0a.webflow.types;

import org.w0a.IniFile;
import org.w0a.types.SourceDirectoryEntry;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.FileHeader;

public class WebflowDirectoryEntry implements SourceDirectoryEntry {

	IniFile iniFile;
	FileHeader fileHeader;
	
	public WebflowDirectoryEntry(ZipFile zipFile, FileHeader fileHeader, IniFile iniFile) {
		this.iniFile = iniFile;
		this.fileHeader = fileHeader;
	}

	@Override
	public void input(String filename) throws Exception {
	}

	@Override
	public void output(String filename) throws Exception {
	}

	@Override
	public void input() throws Exception {
	}

	@Override
	public void output() throws Exception {
	}

	@Override
	public IniFile getXIniFile() {
		return iniFile;
	}

}
