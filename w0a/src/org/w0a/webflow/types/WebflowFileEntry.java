package org.w0a.webflow.types;

import org.w0a.IniFile;
import org.w0a.types.SourceFileEntry;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.FileHeader;

abstract public class WebflowFileEntry implements SourceFileEntry {

	ZipFile zipFile;
	FileHeader fileHeader;
	IniFile iniFile;
	
	public WebflowFileEntry(ZipFile zipFile, FileHeader fileHeader, IniFile iniFile) {
		this.zipFile = zipFile;
		this.fileHeader = fileHeader;
		this.iniFile = iniFile;
	}

	@Override
	public void input(String filename) throws Exception {
		// handled by net.lingala.zip4j.model.FileHeader;
	}

	@Override
	public void output(String filename) throws Exception {
		fileHeader.setFileName(filename);
		String desDir = iniFile.getDestination();
		zipFile.extractFile(fileHeader, desDir);
	}

	@Override
	public IniFile getXIniFile() {
		return iniFile;
	}

}
