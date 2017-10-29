package org.w1a.formats;

import java.io.InputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.w0a.exceptions.FeatureNotImplementedException;
import org.w0a.exceptions.FeatureNotRequiredException;
import org.w0a.types.SourceHTMLFileEntry;
import org.w1a.HTMLEdit;
import org.w1a.W1AIniFile;

public class Angular1HTMLBodyFileEntry implements SourceHTMLFileEntry {

	protected SourceHTMLFileEntry entry;
	
	public Angular1HTMLBodyFileEntry(SourceHTMLFileEntry entry) {
		this.entry = entry;
	}
	
	@Override
	public W1AIniFile getXIniFile() {
		return (W1AIniFile)entry.getXIniFile();
	}
	
	protected String processPageFile(String html) throws Exception {
		Document doc = Jsoup.parse(html);
		Element body = doc.body();
		String edited = HTMLEdit.stripFirstAndLast(body.toString());
		edited = HTMLEdit.stripScriptTags(edited);
		edited = HTMLEdit.convertLinks(edited);
		return edited;
	}
	
	@Override
	public void output(String filename) throws Exception {
		String body = processPageFile(entry.getContent());
		entry.setContent(body);
		entry.output(filename);
	}
	
	@Override
	public String getContent() {
		return entry.getContent();
	}

	@Override
	public void setContent(String text) {
		entry.setContent(text);
	}

	@Override
	public void input(String filename) throws Exception {
		throw new FeatureNotRequiredException();
	}

	@Override
	public void input(InputStream is) throws Exception {
		throw new FeatureNotRequiredException();
	}

	@Override
	public void input() throws Exception {
		throw new FeatureNotImplementedException();
	}

	@Override
	public void output() throws Exception {
		throw new FeatureNotImplementedException();
	}

	@Override
	public boolean isExternalPage() {
		// TODO Auto-generated method stub
		return entry.isExternalPage();
	}
	
}

