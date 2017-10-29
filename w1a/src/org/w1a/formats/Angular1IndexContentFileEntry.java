package org.w1a.formats;


import org.w0a.types.SourceIndexFileEntry;
import org.w1a.HTMLEdit;


public class Angular1IndexContentFileEntry extends Angular1HTMLContentFileEntry {

	public Angular1IndexContentFileEntry(SourceIndexFileEntry entry) {
		super(entry);
	}

	protected String processIndexFile(String content) throws Exception {
		String[] parts = getSplitPage(content);
		String added = "<!DOCTYPE html>\n";
		added += "<html lang='en-us' ng-app='webFlowApp'>\n";
		added += parts[0] + "\n";
		added += "</html>\n";
		String edited = HTMLEdit.convertLinks(added);
		return edited;
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
