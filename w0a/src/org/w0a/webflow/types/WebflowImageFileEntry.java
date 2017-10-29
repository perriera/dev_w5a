package org.w0a.webflow.types;


import org.w0a.IniFile;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.FileHeader;

public class WebflowImageFileEntry extends WebflowBinaryFileEntry {

	public WebflowImageFileEntry(ZipFile zipFile, FileHeader fileHeader, IniFile iniFile) {
		super(zipFile, fileHeader, iniFile);
	}
	
}
