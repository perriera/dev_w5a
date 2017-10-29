package org.w2a.ngtypes;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.w0a.types.SourceIndexFileEntry;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.ngcomponent.NgCSSComponent;
import org.w2a.ngfile.NgFileInterface;
import org.w2a.ngcomponent.NgBaseComponent;

public class NgIndexFile extends NgBaseComponent  implements SourceIndexFileEntry, NgFileInterface {

	SourceIndexFileEntry entry;
	W2AIniFile iniFile;
	String content;

	public NgIndexFile(String key, SourceIndexFileEntry entry, W2AIniFile iniFile) throws Exception {
		super("index.html",entry.getContent(),iniFile);
		this.entry = entry;
		this.iniFile = iniFile;
	}
	
	@Override
	public String getHTMLAssetFilename() throws Exception {
		String filename = this.getNgFile().getIndexFilename();
		return filename;
	}
	
	/*
	 * Take the <head> portion of the index.html file and place
	 * it into the app/index.html replacing the <body> portion
	 * with the <app-root></app-root>
	 */
	
	@Override
	public void input() throws Exception {
		super.input();
		NgImageFile.adjustImageLinks(entry,iniFile);
		// TODO: Allow for a merge of existing index.html to be possible
		Document doc = HTMLEdit.parse(entry.getContent());
		Element head = doc.head();
		String indexHtml = "<!DOCTYPE html>\n";
		indexHtml += "<html lang='en-us'>\n";
		indexHtml += head.toString() + "\n";
		indexHtml += "<body>\n";
		indexHtml += "    <app-root></app-root>\n";
		indexHtml += "</body>\n";
		indexHtml += "</html>\n";
		indexHtml = NgImageFile.adjustImageLinks(indexHtml,iniFile);
		indexHtml = NgImageFile.adjustHRefLinks(indexHtml,iniFile);
		indexHtml = NgCSSComponent.removeCSSLinks(indexHtml);
		indexHtml = NgJSFile.adjustJSLinks(indexHtml,iniFile);
		this.setContent(indexHtml);
	}
	
	/*
	 * Override the default behavior of createComponent() as 
	 * app/app.component.html is already created by default.
	 * 
	 * (non-Javadoc)
	 * @see org.w2a.ngcomponent.NgBaseComponent#createComponent()
	 */
	
	public void createComponent() throws Exception {
		// override default behavior
		// app/app.component.html is created by default
	}
	
}
