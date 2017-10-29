package org.w0a.webflow.types;

import org.w0a.IniFile;
import org.w0a.types.SourceIndexFileEntry;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.FileHeader;

public class WebflowIndexFileEntry extends WebflowHTMLFileEntry implements SourceIndexFileEntry {

	public WebflowIndexFileEntry(ZipFile zipFile, FileHeader fileHeader, IniFile iniFile) {
		super(zipFile, fileHeader, iniFile);
	}

}
