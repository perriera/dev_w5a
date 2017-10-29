package org.w1a.formats;

import java.io.IOException;
import java.io.InputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.w0a.exceptions.FeatureNotImplementedException;
import org.w0a.exceptions.FeatureNotRequiredException;
import org.w0a.types.SourceHTMLFileEntry;
import org.w1a.HTMLEdit;
import org.w1a.W1AIniFile;

public class Angular1HTMLContentFileEntry implements SourceHTMLFileEntry {

	@SuppressWarnings("serial")
	static public class ContentDivNotSpecifiedException extends IOException {};
	
	SourceHTMLFileEntry entry;
	
	public Angular1HTMLContentFileEntry(SourceHTMLFileEntry entry) {
		this.entry = entry;
	}
	
	@Override
	public W1AIniFile getXIniFile() {
		return (W1AIniFile)entry.getXIniFile();
	}
	
	public String[] getSplitPage(String html) throws Exception {
		Document doc = Jsoup.parse(html);
		Element head = doc.head();
		Element body = doc.body();
		String contentId = getXIniFile().getContentDiv();
		Element content =  doc.select("div#"+contentId).first();
		if ( content==null )
			throw new ContentDivNotSpecifiedException();
		String bodyContentText  = content.toString();
		String bodyText  = body.toString();
		String ngViewText = "\t<div ng-view></div>";
		bodyContentText = HTMLEdit.stripSpaces(bodyContentText);
		String modifiedBodyText = HTMLEdit.stripSpaces(bodyText);
		modifiedBodyText = modifiedBodyText.replace(bodyContentText, ngViewText);
		modifiedBodyText = HTMLEdit.stripScriptTags(modifiedBodyText);
		String[] parts = new String[2];
		Document doc2 = Jsoup.parse(head.toString()+"\n"+modifiedBodyText);
		parts[0] = doc2.toString();
		parts[1] = content.toString();
		return parts;
	}
	
	protected String processPageFile(String content) throws Exception {
		String[] parts = getSplitPage(content);
		String edited = HTMLEdit.convertLinks(parts[1]);
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

