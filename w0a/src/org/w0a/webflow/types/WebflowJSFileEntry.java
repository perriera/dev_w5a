package org.w0a.webflow.types;

import org.w0a.IniFile;
import org.w0a.types.SourceJSFileEntry;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.FileHeader;

public class WebflowJSFileEntry extends WebflowTextFileEntry implements SourceJSFileEntry {

	public WebflowJSFileEntry(ZipFile zipFile, FileHeader fileHeader, IniFile iniFile) {
		super(zipFile, fileHeader, iniFile);
	}

}
