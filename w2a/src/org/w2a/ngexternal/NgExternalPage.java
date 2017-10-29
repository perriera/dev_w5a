package org.w2a.ngexternal;

import java.io.File;
import java.util.Map.Entry;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w0a.extractor.SourceExtractor;
import org.w0a.interfaces.SourceCompilerInterface;
import org.w0a.interfaces.SourceEntryInterface;
import org.w0a.types.SourceCSSFileEntry;
import org.w0a.types.SourceHTMLFileEntry;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.ngfile.NgFile;
import org.w2a.ngtypes.NgTextFile;

public class NgExternalPage implements SourceCompilerInterface {

	SourceHTMLFileEntry entry;
	W2AIniFile iniFile;
	String content;
	SourceExtractor extractor;

	public NgExternalPage(SourceHTMLFileEntry entry, W2AIniFile iniFile, SourceExtractor extractor) {
		this.entry = entry;
		this.iniFile = iniFile;
		this.extractor = extractor;
	}

	@Override
	public void input(String before) throws Exception {
		this.content = before;
	}

	@Override
	public String output() throws Exception {
		return content;
	}

	// public void writePage(String output, String htmlAssetFilename) throws
	// Exception {
	//
	// String externalDir = iniFile.getExternalDir();
	// String srcDir = "src"; // iniFile.getSourceDir();
	// String pagesDir = iniFile.getViewsDir();
	// externalDir = HTMLEdit.append(externalDir, pagesDir);
	// String appDir = iniFile.getAppDir();
	// String target = HTMLEdit.append(srcDir,appDir,pagesDir);
	// String externalPage = htmlAssetFilename.replace(target, externalDir);
	//
	// output = copyCSS(output,externalPage);
	// output = redirectResources(output,externalPage);
	// output = copyHTML(output,externalPage);
	//
	// }

	String figureOutWhereTheExternalPageShouldGo(String htmlAssetFilename) throws Exception {
		String externalDir = iniFile.getExternalDir();
		String srcDir = "src"; // iniFile.getSourceDir();
		String appDir = iniFile.getAppDir();
		String pagesDir = iniFile.getViewsDir();
		String from = HTMLEdit.append(srcDir, appDir, pagesDir);
		String to = HTMLEdit.append(srcDir, appDir, externalDir);
		String externalPage = htmlAssetFilename.replace(from, to);
		return externalPage;
	}
	
	String figureOutWhereTheCSSFileShouldGo(String htmlAssetFilename, String cssFilename) throws Exception {
		String externalDir = iniFile.getExternalDir();
		String srcDir = "src"; // iniFile.getSourceDir();
		String appDir = iniFile.getAppDir();
		String pagesDir = iniFile.getViewsDir();
		String from = HTMLEdit.append(srcDir, appDir, externalDir);
		String to = HTMLEdit.append(srcDir, "assets/");
		String externalPage = htmlAssetFilename.replace(from, to);
		String[] parts1 = externalPage.split("assets/");
		String results = HTMLEdit.append(parts1[0], "assets/", cssFilename);
		return results;
	}
	
	public void writePage(String output, String htmlAssetFilename) throws Exception {
		String externalPage = figureOutWhereTheExternalPageShouldGo(htmlAssetFilename);
		output = copyCSS(output, externalPage);
		output = redirectResources(output, externalPage);
		output = copyHTML(output, externalPage);
	}

	String figureOutBaseDir(String fullpath, String desiredLocation) throws Exception {
		String externalDir = iniFile.getExternalDir();
		String[] parts = fullpath.split(externalDir);
		String newDir = HTMLEdit.append(parts[0], desiredLocation);
		return newDir;
	}

	String figureOutBackTrack(String externalPage, String whereTo) throws Exception {
		String[] parts1 = whereTo.split("src/");
		String path = HTMLEdit.append("/", parts1[1]);
		return path;
	}

	// private String copyCSS(String content, String externalPage) throws
	// Exception {
	// String externalDir = iniFile.getExternalDir();
	// Document doc = HTMLEdit.parse(content);
	// Elements elements = doc.select("link[href$=css]");
	// for (Element e : elements) {
	// String cssName = e.attr("href");
	// cssName = cssName.replace("../", "");
	// SourceCSSFileEntry cssContents = extractor.getCSSPage(cssName);
	// String newCssFilename = HTMLEdit.append(externalDir, "assets/", cssName);
	// String whereTo = figureOutBaseDir(externalPage,newCssFilename);
	// NgTextFile.output(cssContents.getContent(), whereTo);
	// String whereElse = figureOutBackTrack(externalPage,whereTo);
	// e.attr("href",whereElse);
	// }
	// return doc.toString();
	// }

	private String copyCSS(String content, String externalPage) throws Exception {
		Document doc = HTMLEdit.parse(content);
		Elements elements = doc.select("link[href$=css]");
		for (Element e : elements) {
			String cssName = e.attr("href");
			cssName = cssName.replace("../", "");
			SourceCSSFileEntry cssContents = extractor.getCSSPage(cssName);
			String newCSSFileLocation = figureOutWhereTheCSSFileShouldGo(externalPage,cssName);
			NgFile.mkdirs(newCSSFileLocation);
			NgTextFile.output(cssContents.getContent(), newCSSFileLocation);
			String whereElse = figureOutBackTrack(externalPage, newCSSFileLocation);
			e.attr("href", whereElse);
		}
		return doc.toString();
	}

	private String copyHTML(String content, String externalPage) throws Exception {
		NgFile.mkdirs(externalPage);
		NgTextFile.output(content, externalPage);
		return content;
	}

	private String redirectResources(String content, String externalPage, String hook, String sink, String type)
			throws Exception {
		Document doc = HTMLEdit.parse(content);
		Elements elements = doc.select(hook + "[" + sink + "$=" + type + "]");
		for (Element e : elements) {
			String href = e.attr(sink);
			href = href.replace("../", "/assets/");
			e.attr(sink, href);
		}
		return doc.toString();
	}

	private String redirectResources(String content, String externalPage) throws Exception {
		content = redirectResources(content, externalPage, "link", "href", "png");
		content = redirectResources(content, externalPage, "link", "href", "jpg");
		content = redirectResources(content, externalPage, "link", "href", "jpeg");
		content = redirectResources(content, externalPage, "link", "href", "bmp");
		content = redirectResources(content, externalPage, "link", "href", "gif");
		content = redirectResources(content, externalPage, "script", "src", "js");
		content = redirectResources(content, externalPage, "img", "src", "png");
		content = redirectResources(content, externalPage, "img", "src", "jpg");
		content = redirectResources(content, externalPage, "img", "src", "jpeg");
		content = redirectResources(content, externalPage, "img", "src", "bmp");
		content = redirectResources(content, externalPage, "img", "src", "gif");
		return content;
	}

}
