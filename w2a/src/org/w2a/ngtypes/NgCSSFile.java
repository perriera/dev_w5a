package org.w2a.ngtypes;

import java.io.InputStream;

import org.w0a.IniFile;
import org.w0a.exceptions.FeatureNotImplementedException;
import org.w0a.types.SourceCSSFileEntry;

public class NgCSSFile implements SourceCSSFileEntry {

	SourceCSSFileEntry entry;
	
	public NgCSSFile(SourceCSSFileEntry e) {
		this.entry = e;
	}

	@Override
	public IniFile getXIniFile() {
		return this.entry.getXIniFile();
	}

	@Override
	public void input(String filename) throws Exception {
		this.entry.input(filename);
	}

	@Override
	public void output(String filename) throws Exception {
		// this.entry.output(filename);
	}

	@Override
	public void input() throws Exception   {
		try {
			this.entry.input();
		} catch (FeatureNotImplementedException e) {
		}
	}

	@Override
	public void output() throws Exception {
		// this.entry.output();
	}

	@Override
	public void input(InputStream is) throws Exception {
		this.entry.input(is);
	}

	@Override
	public String getContent() {
		return this.entry.getContent();
	}

	@Override
	public void setContent(String text) {
		this.setContent(text);
	}

}
