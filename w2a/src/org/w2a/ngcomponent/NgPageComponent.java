package org.w2a.ngcomponent;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.w0a.extractor.SourceExtractor;
import org.w0a.types.SourceHTMLFileEntry;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.exceptions.IndexFileMissingNgRouterException;
import org.w2a.ngexternal.NgExternalPage;
import org.w2a.ngtypes.NgImageFile;
import org.w2a.ngtypes.NgTextFile;

public class NgPageComponent extends NgBaseComponent implements SourceHTMLFileEntry {

	SourceHTMLFileEntry entry;
	SourceExtractor extractor;
	
	public NgPageComponent(String key, SourceHTMLFileEntry e, W2AIniFile iniFile, SourceExtractor extractor) throws Exception {
		super(key,e.getContent(),iniFile);
		this.entry = e;
		this.extractor = extractor;
	}
	
	/*
	 * Use the ng-router attribute to determine the contents.
	 * Also, add <router-outlet></router-outlet> to the 
	 * app.app.component.html file.
	 */
	
	String implementRouterOutlet(String before) throws IndexFileMissingNgRouterException {
		Document doc = HTMLEdit.parse(before);
		Element link = doc.select("div[ng-router]").first();
		// TODO: develope a new test suite or new policy on ng-router
		if ( link==null ) {
			link = doc.body();
		}
		String after = link.toString();
		return after;
	}
	
	@Override
	public void input() throws Exception {
		super.input();
		String homeComponentHtml = implementRouterOutlet(this.getContent());
		// TODO: find out which of the below are not required
		homeComponentHtml = NgImageFile.adjustImageLinks(homeComponentHtml,iniFile);
		homeComponentHtml = NgComponentExtractor.removeHeaderAndBody(homeComponentHtml,iniFile);
		homeComponentHtml = NgComponentExtractor.outlineSource(homeComponentHtml, this.iniFile);
		this.setContent(homeComponentHtml);
	}
	
	@Override
	public void output() throws Exception {
		
		if ( entry.isExternalPage() && extractor!=null ) {
			String content = entry.getContent();
			NgExternalPage nep = new NgExternalPage(entry,iniFile,extractor);
			nep.input(content);
			String output = nep.output();
			nep.writePage(output,this.getHTMLAssetFilename());
		} else {
			super.output();
		}
		
	}
	
}
