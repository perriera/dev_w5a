package org.w2a.ngtypes;

import java.io.InputStream;

import org.w2a.HTMLEdit;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w0a.types.SourceJSFileEntry;
import org.w2a.W2AIniFile;
import org.w2a.ngfile.NgFile;
import org.w2a.ngfile.NgFileInterface;

public class NgJSFile implements SourceJSFileEntry, NgFileInterface {

	String key;
	NgFile ngFile;
	protected SourceJSFileEntry entry;
	static String assetsDir = NgBinaryFile.assetsDir;


	public NgJSFile(String key, SourceJSFileEntry entry, W2AIniFile iniFile) {
		this.key = key;
		this.entry = entry;
		this.ngFile = new NgFile(key, assetsDir, iniFile);
	}
	
	@Override
	public W2AIniFile getXIniFile() {
		return (W2AIniFile)entry.getXIniFile();
	}
	
	@Override
	public NgFile getNgFile() throws Exception {
		return this.ngFile;
	}

	@Override
	public String getHTMLAssetFilename() throws Exception {
		String filename = HTMLEdit.append(this.ngFile.getAssetsDirectory(), key);
		return filename;
	}
	
	/**
	 * input()
	 * 
	 * Read the contents of the Webflow entity via the entity.input();
	 * By default the current underlying implementation depends upon
	 * the Zip4J framework which handles the input and output process.
	 * 
	 * @throws Exception 
	 * 
	 */
	
	@Override
	public void input() throws Exception {
	}

	/**
	 * output()
	 * 
	 * Read the contents of the Webflow entity via the entity.input();
	 * By default the current underlying implementation depends upon
	 * the Zip4J framework which handles the input and output process.
	 * 
	 */
	
	@Override
	public void output() throws Exception {
		output(this.key);
	}
	
	@Override
	public void output(String filename) throws Exception {
		String assetsDir = this.getXIniFile().getAssetsDir();
		entry.output(HTMLEdit.append(assetsDir, filename));
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
		entry.input(filename);
	}

	@Override
	public void input(InputStream is) throws Exception {
		entry.input(is);
	}

	// 
	// Static Members below ... 
	//
	
	public static String adjustJSLinks(String html, W2AIniFile w2aIniFile) throws Exception {
		String assetsDir = w2aIniFile.getAssetsDir();
		Document doc = HTMLEdit.parse(html);
		Element head = doc.head();
		Elements links = head.select("script[src$=.js]");
		for (Element link : links) {
			String src = link.attr("src");
			if ( !src.startsWith("http"))
				link.attr("src", HTMLEdit.append(assetsDir, src));
		}
		String editted = doc.toString();
		return editted;
	}
	
	
}

