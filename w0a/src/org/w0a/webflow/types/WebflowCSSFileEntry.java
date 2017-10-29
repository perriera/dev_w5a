package org.w0a.webflow.types;

import org.w0a.IniFile;
import org.w0a.types.SourceCSSFileEntry;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.FileHeader;

public class WebflowCSSFileEntry extends WebflowTextFileEntry implements SourceCSSFileEntry {

	public WebflowCSSFileEntry(ZipFile zipFile, FileHeader fileHeader, IniFile iniFile) {
		super(zipFile, fileHeader, iniFile);
	}

}
