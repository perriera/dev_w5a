package org.w2a.ngtypes;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w0a.types.SourceFileEntry;
import org.w0a.types.SourceHTMLFileEntry;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;


public class NgImageFile extends NgBinaryFile {

	public NgImageFile(String key, SourceFileEntry entry, W2AIniFile iniFile) {
		super(key,entry,iniFile);
	}
	
	//
	// Static Members below ... 
	//
	
	private static void tweak(Element link, String attr, W2AIniFile iniFile) throws Exception {
		String assetsDir = iniFile.getAssetsDir();
		String value = link.attr(attr);
		if ( value.startsWith("../") ) {
			int pos = value.indexOf('i');
			value = value.substring(pos);
		}
		if ( !value.startsWith(assetsDir) ) {
			String oldImgDir = "images/";
			String newImgDir = HTMLEdit.append(assetsDir, oldImgDir);
			String newValue = value.replace(oldImgDir, newImgDir);
			link.attr(attr, newValue);
		}
	}
	
	public static String adjustImageLinks(String html, W2AIniFile iniFile) throws Exception {
		Document doc = HTMLEdit.parse(html);
		Elements links = doc.select("img");
		for (Element link : links) {
			tweak(link,"src",iniFile);
			tweak(link,"srcset",iniFile);
		} 
		String editted = doc.toString();
		return editted;
	}
	
	public static void adjustImageLinks(SourceHTMLFileEntry entry, W2AIniFile w2aIniFile) throws Exception {
		entry.setContent(adjustImageLinks(entry.getContent(),w2aIniFile));
	}
	
	public static String adjustHRefLinks(String html, W2AIniFile iniFile, String search) throws Exception {
		String assetsDir = iniFile.getAssetsDir();
		Document doc = HTMLEdit.parse(html);
		Elements links = doc.select(search);
		for (Element link : links) {
			String href = link.attr("href");
			if ( href.startsWith("images/") ) {
				href = HTMLEdit.append(assetsDir,href);
			}
			link.attr("href", href);
		} 
		String editted = doc.toString();
		return editted;
	}
	
	public static String adjustHRefLinks(String html, W2AIniFile iniFile) throws Exception {
		html = adjustHRefLinks(html,iniFile,"link[href$=.png]");
		html = adjustHRefLinks(html,iniFile,"link[href$=.jpg]");
		html = adjustHRefLinks(html,iniFile,"link[href$=.jpeg]");
		return html;
	}
	
	public static String adjustImageReferencesInCSSStyles(String cssContent, W2AIniFile iniFile) throws Exception {
		String assetsDir = iniFile.getAssetsDir();
		String link = HTMLEdit.append(assetsDir, "images");
		String newUrl = "url('"+link;
		String updatedCSS = cssContent.replace("url('../images", newUrl);
		return updatedCSS;
	}
	
}
