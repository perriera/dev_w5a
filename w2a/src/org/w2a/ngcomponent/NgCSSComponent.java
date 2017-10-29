package org.w2a.ngcomponent;

import java.io.InputStream;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w0a.exceptions.FeatureNotRequiredException;
import org.w0a.extractor.SourceExtractor;
import org.w0a.types.SourceCSSFileEntry;
import org.w0a.types.SourceIndexFileEntry;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.ngfile.NgFile;
import org.w2a.ngfile.NgFileInterface;
import org.w2a.ngtypes.NgImageFile;
import org.w2a.ngtypes.NgTextFile;


public class NgCSSComponent implements SourceCSSFileEntry, NgFileInterface {

	SourceIndexFileEntry entry;
	SourceExtractor extractor;
	W2AIniFile iniFile;
	String stylesContents;
	NgFile ngFile;
	
	public NgCSSComponent(SourceIndexFileEntry entry, SourceExtractor extractor, W2AIniFile iniFile) {
		this.extractor = extractor;
		this.iniFile = iniFile;
		this.ngFile = new NgFile("styles.css", "src", iniFile);
	}

	@Override
	public String getHTMLAssetFilename() throws Exception {
		return this.getNgFile().getCSSFilename();
	}
	
	@Override
	public NgFile getNgFile() throws Exception {
		return this.ngFile;
	}
	
	@Override
	public W2AIniFile getXIniFile() {
		return iniFile;
	}
	
	@Override
	public String getContent() {
		return entry.getContent();
	}

	@Override
	public void setContent(String text) {
		entry.setContent(text);
	}
	
	/**
	 * input()
	 * 
	 * Read all CSS files referenced by the Webflow index.html and place them
	 * inside this.stylesContents.
	 * 
	 */
	
	public String getStylesFile(SourceExtractor extractor, W2AIniFile iniFile) throws Exception {
		SourceIndexFileEntry index = extractor.getIndex();
		Document doc = HTMLEdit.parse(index.getContent());
		Element head = doc.head();
		Elements links = head.select("link[href$=.css]");
		String stylesContent = "";
		for (Element link : links) {
			String href = link.attr("href");
			SourceCSSFileEntry css = (SourceCSSFileEntry) extractor.get(href);
			stylesContent += css.getContent();
		}
		stylesContent = NgImageFile.adjustImageReferencesInCSSStyles(stylesContent,iniFile);
		return stylesContent;
	}
	
	@Override
	public void input() throws Exception {
		this.stylesContents = getStylesFile(extractor,this.getXIniFile());
	}
	
	/**
	 * output()
	 * 
	 * Take the contents of this.stylesContents and write them into the src/
	 */
	@Override
	public void output() throws Exception {
		NgTextFile.output(this.stylesContents, getHTMLAssetFilename());
	}

	// 
	// Methods below are static members
	//
	
	/**
	 * removeCSSLinks()
	 * 
	 * Removes CSS links from a HTML file.
	 * 
	 * @param html
	 * @return
	 */
	
	public static String removeCSSLinks(String html) {
		Document doc = HTMLEdit.parse(html);
		Element head = doc.head();
		Elements links = head.select("link[href$=.css]");
		for (Element link : links) {
			link.remove();
		}
		String editted = doc.toString();
		return editted;
	}
	
	// 
	// Methods below throw FeatureNotRequiredException
	//
	
	@Override
	public void input(String filename) throws Exception {
		throw new FeatureNotRequiredException();
	}

	@Override
	public void input(InputStream is) throws Exception {
		throw new FeatureNotRequiredException();
	}

	@Override
	public void output(String filename) throws Exception {
		throw new FeatureNotRequiredException();
	}

	//
	// Static Members below ...
	//
	
	
}

