package org.w0a.webflow.types;


import java.io.InputStream;
import org.w0a.HTMLEdit;
import org.w0a.IniFile;
import org.w0a.exceptions.FeatureNotImplementedException;
import org.w0a.types.SourceTextFileEntry;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.io.ZipInputStream;
import net.lingala.zip4j.model.FileHeader;

public class WebflowTextFileEntry extends WebflowFileEntry implements SourceTextFileEntry {

	String content;
	
	public WebflowTextFileEntry(ZipFile zipFile, FileHeader fileHeader, IniFile iniFile) {
		super(zipFile,fileHeader,iniFile);
	}
	
	
	public void input(InputStream is) throws Exception {
		StringBuffer sBuffer = new StringBuffer();
		int i;
		char c;
		while ((i = is.read()) != -1) {
			c = (char) i;
			sBuffer.append(c);
		}
		this.content = sBuffer.toString();
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public void setContent(String text) {
		this.content = text;
	}


	@Override
	public void input(String filename) throws Exception {
		ZipInputStream is = zipFile.getInputStream(fileHeader);
		input(is);
		is.close();
	}
	
	@Override
	public void output(String filename) throws Exception {
		HTMLEdit.output(content, filename, iniFile);
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
