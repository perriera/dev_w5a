package org.w0a.webflow.types;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.w0a.HTMLEdit;
import org.w0a.IniFile;
import org.w0a.types.SourceHTMLFileEntry;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.FileHeader;

public class WebflowHTMLFileEntry extends WebflowTextFileEntry implements SourceHTMLFileEntry {

	boolean ng_external = false;
	
	public WebflowHTMLFileEntry(ZipFile zipFile, FileHeader fileHeader, IniFile iniFile) {
		super(zipFile, fileHeader, iniFile);
	}

	@Override
	public void input(String filename) throws Exception {
		super.input(filename);
		if ( this.content.contains("ng-external")) {
			Document doc = HTMLEdit.parse(this.content);
			Element body = doc.head();
			String test = body.attr("ng-external");
			if ( test!=null && test.equals("true") )
				this.ng_external = true;
		}
	}

	@Override
	public boolean isExternalPage() {
		return ng_external;
	}
	
}

