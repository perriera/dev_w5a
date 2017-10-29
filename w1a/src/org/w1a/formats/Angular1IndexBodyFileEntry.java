package org.w1a.formats;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.w0a.types.SourceIndexFileEntry;


public class Angular1IndexBodyFileEntry extends Angular1HTMLBodyFileEntry implements SourceIndexFileEntry {

	public Angular1IndexBodyFileEntry(SourceIndexFileEntry entry) {
		super(entry);
	}

	protected String processIndexFile(String html) throws Exception {
		Document doc = Jsoup.parse(html);
		Element head = doc.head();
		String added = "<!DOCTYPE html>\n";
		added += "<html lang='en-us' ng-app='webFlowApp'>\n";
		added += head.toString() + "\n";
		added += "<body>\n";
		added += "    <div ng-view></div>\n";
		added += "</body>\n";
		added += "</html>\n";
		return added;
	}
	
	@Override
	public void output(String filename) throws Exception {
		String original = entry.getContent();
		String head = processIndexFile(original);
		entry.setContent(head);
		entry.output(filename);
		filename = filename.replace(".html", ".htm");
		String body = processPageFile(original);
		entry.setContent(body);
		entry.output(filename);
	}
	
}
