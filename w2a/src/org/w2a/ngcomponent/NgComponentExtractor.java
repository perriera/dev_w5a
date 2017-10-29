package org.w2a.ngcomponent;

import java.io.IOException;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w0a.extractor.SourceExtractor;
import org.w0a.interfaces.SourceEntryInterface;
import org.w0a.types.SourceCSSFileEntry;
import org.w0a.types.SourceDirectoryEntry;
import org.w0a.types.SourceFileEntry;
import org.w0a.types.SourceHTMLFileEntry;
import org.w0a.types.SourceImageFileEntry;
import org.w0a.types.SourceIndexFileEntry;
import org.w0a.types.SourceJSFileEntry;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.exceptions.NoTokenFoundInFilenameException;
import org.w2a.ngfile.NgFile;
import org.w2a.ngtypes.NgBinaryFile;
import org.w2a.ngtypes.NgCSSFile;
import org.w2a.ngtypes.NgImageFile;
import org.w2a.ngtypes.NgJSFile;
import org.w2a.ngtypes.NgTextFile;
import org.w2a.prettypepper.NgPrettyPepper;

/*
 * SourceFormatter extends SourceExtractor
 * 
 * Take the contents of a SourceExtractor and format them into the
 * desired format. This class typically determines which types of the
 * SourceExtractor archive need to be converted into what format
 * based on the type of information they have as determined by
 * the input() method below.
 * 
 * Then during the output() method phase, those formatted components
 * are written out to a desired location, typically the destination
 * directory of a ng cli implementation.
 * 
 */

@SuppressWarnings("serial")
public class NgComponentExtractor extends SourceExtractor {

	protected SourceExtractor extractor;

	public NgComponentExtractor(SourceExtractor extractor) throws Exception {
		this.extractor = extractor;
	}

	@Override
	public W2AIniFile getXIniFile() {
		return (W2AIniFile) extractor.getXIniFile();
	}

	public SourceExtractor getExtractor() {
		return extractor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.w0a.base.SourceArchiveInterface#input(org.w0a.IniFile)
	 */

	@Override
	public void input() throws Exception {
		extractor.input();
		for (Entry<String, SourceEntryInterface> entry : extractor) {
			String key = entry.getKey();
			if (!getXIniFile().isIgnored(key)) {
				if (entry.getValue() instanceof SourceIndexFileEntry) {
					SourceIndexFileEntry e = (SourceIndexFileEntry) entry.getValue();
					extractor.put(key, new NgIndexComponent(key, e, getXIniFile(), extractor));
				} else if (entry.getValue() instanceof SourceHTMLFileEntry) {
					SourceHTMLFileEntry e = (SourceHTMLFileEntry) entry.getValue();
					extractor.put(key, new NgPageComponent(key, e, getXIniFile(), extractor));
				} else if (entry.getValue() instanceof SourceCSSFileEntry) {
					SourceCSSFileEntry e = (SourceCSSFileEntry) entry.getValue();
					extractor.put(key, new NgCSSFile(e));
				} else if (entry.getValue() instanceof SourceJSFileEntry) {
					SourceJSFileEntry e = (SourceJSFileEntry) entry.getValue();
					extractor.put(key, new NgJSFile(key, e, getXIniFile()));
				} else if (entry.getValue() instanceof SourceImageFileEntry) {
					SourceJSFileEntry e = (SourceJSFileEntry) entry.getValue();
					extractor.put(key, new NgImageFile(key, e, getXIniFile()));
				} else if (entry.getValue() instanceof SourceDirectoryEntry) {
				} else {
					SourceFileEntry e = (SourceFileEntry) entry.getValue();
					extractor.put(key, new NgBinaryFile(key, e, getXIniFile()));
				}
				SourceEntryInterface e = entry.getValue();
				e.input();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.w0a.base.SourceArchiveInterface#output(org.w0a.IniFile)
	 */

	String getStatusFilename(String filename) throws NoTokenFoundInFilenameException {
		String fn = HTMLEdit.getTokenFromFilename(filename);
		fn += ".status.txt";
		return fn;
	}

	void writeStatusFile(float index, float size, String filename) throws Exception {
		try {
			int percentDone = (int) ((++index / size) * 100);
			String status = "";
			for (int i = 0; i < percentDone; i++)
				status += "x";
			String fn = getStatusFilename(filename);
			NgTextFile.output(status, fn);
		} catch (NoTokenFoundInFilenameException ex) {
			// System.err.println(ex);
		}
	}

	@Override
	public void output() throws Exception {
		int index = 0;
		int size = extractor.size();
		W2AIniFile iniFile = getXIniFile();
		String desDir = iniFile.getDestination();
		desDir = desDir.replace("/src", ".txt");
		for (Entry<String, SourceEntryInterface> entry : extractor) {
			String key = entry.getKey();
			entry.getValue().output(key);
			writeStatusFile(index++, size, desDir);
		}
		try {
			NgFile.deleteDir(getStatusFilename(desDir));
		} catch (NoTokenFoundInFilenameException ex) {
			// System.err.println(ex);
		}

	}

	//
	// Static Memebers below ...
	//

	static public boolean hasNgConstruct(String line, W2AIniFile iniFile) throws Exception {
		if (line.contains("id=")) {
			Document doc = HTMLEdit.parse(line);
			Element body = doc.body();
			if (body != null) {
				for (Element e : body.select("*")) {
					if (e.id().length() > 0) {
						for (Attribute a : e.attributes().asList()) {
							if (a.getKey().startsWith("("))
								return true;
							if (a.getKey().startsWith("["))
								return true;
							if (a.getKey().startsWith("id") && iniFile.getExpandIds())
								return true;
							if (a.getKey().startsWith("ng"))
								return true;
							if (a.getKey().startsWith("*ng"))
								return true;
						}
					}
				}
			}
		}
		return false;
	}

	static public String outlineSource(String content, W2AIniFile iniFile) throws Exception {
		NgPrettyPepper pp = new NgPrettyPepper(content, iniFile);
		String test = pp.output();
		return test;
	}

	private static String repackageText(String content, String text) {
		content = content.replace("{{", "&xyz;");
		content = content.replace("}}", "&abc;");
		content = content.replace(text, "{{\"" + text + "\"}}");
		content = content.replace("&xyz;", "{{");
		content = content.replace("&abc;", "}}");
		return content;
	}

	public static String adjustCulyBraces(String html) throws IOException {
		String content = html;
		content = content.replace("&", "^hiddenampersand.");
		Document doc = HTMLEdit.parse(content);
		Elements elements = doc.select("*");
		for (Element e : elements) {
			for (Attribute a : e.attributes()) {
				String value = a.getValue();
				value = value.replace("{", "^hiddenleftbrace.");
				value = value.replace("}", "^hiddenrightbrace.");
				a.setValue(value);
			}
		}
		String editted = doc.body().toString();
		editted = HTMLEdit.stripFirstAndLast(editted);
		content = repackageText(editted, "{");
		content = repackageText(content, "}");
		doc = HTMLEdit.parse(content);
		elements = doc.select("*");
		for (Element e : elements) {
			for (Attribute a : e.attributes()) {
				String value = a.getValue();
				value = value.replace("^hiddenleftbrace.", "{");
				value = value.replace("^hiddenrightbrace.", "}");
				a.setValue(value);
			}
		}
		editted = doc.body().toString();
		content = HTMLEdit.stripFirstAndLast(editted);
		content = content.replace("^hiddenampersand.", "&");
		return content;
	}

	public static String removeHeaderAndBody(String html, W2AIniFile iniFile) throws IOException {
		Document doc = HTMLEdit.parse(html);
		String editted = doc.body().toString();
		editted = HTMLEdit.stripFirstAndLast(editted);
		return editted;
	}

	@Override
	public void prepare() throws Exception {
		extractor.prepare();
	}

	@Override
	public void cleanup() throws Exception {
		extractor.cleanup();
	}

	public static String removeWebFlowItems(String html, W2AIniFile iniFile) throws Exception {
		if (iniFile.preserveWebflowExtensions())
			return html;
		Document doc = HTMLEdit.parse(html);
		for (Element element : doc.select("div.w-form-done")) {
			element.remove();
		}
		for (Element element : doc.select("div.w-form-fail")) {
			element.remove();
		}
		for (Element element : doc.select("input[data-wait]")) {
			element.removeAttr("data-wait");
		}
		for (Element element : doc.select("input[data-name]")) {
			element.removeAttr("data-name");
		}
		for (Element element : doc.select("form[data-name]")) {
			element.removeAttr("data-name");
		}
		String editted = doc.body().toString();
		editted = HTMLEdit.stripFirstAndLast(editted);
		editted = HTMLEdit.removeLinesContaining(editted, "jquery.min.js", "js/webflow.js", "[if lte IE 9]",
				"[if lt IE 9]");
		return editted;
	}

	public static String replaceBraces(String mergedHtml, W2AIniFile iniFile) {
		mergedHtml = mergedHtml.replace("((", "{{");
		mergedHtml = mergedHtml.replace("))", "}}");
		return mergedHtml;
	}

	public static String fixAmpIssue(String html, W2AIniFile iniFile) throws IOException {
		String content = html;
		Document doc = HTMLEdit.parse(content);
		Elements elements = doc.select("*");
		for (Element e : elements) {
			for (Attribute a : e.attributes()) {
				String value = a.getValue();
				if (value.contains("&&")) {
					String target = value.replace("&&", "&amp;&amp;");
					html = html.replace(target, value);
				}
			}
		}
		return html;
	}

//	public static String firstInstanceOf(String pattern, String replacement) throws IOException {
//		String test = "x "+ line + " x"; 
//		String[] parts1 = test.split("\\{\\{"); 
//		String[] parts2 = test.split("\\}\\}"); 
//		if ( parts1.length!=parts2.length ) {
//			line = line.firstInstanceOf("{{", "(");
//		}
//		return line;
//	}

	public static String fixBitchLine(String line, W2AIniFile iniFile) throws IOException {
		String test = "x "+ line + " x"; 
		String[] parts1 = test.split("\\{\\{"); 
		String[] parts2 = test.split("\\}\\}"); 
		if ( parts1.length!=parts2.length ) {
			line = line.replaceFirst("\\{\\{", "(");
		}
		return line;
	}

	public static String fixBitchIssue(String formatted, W2AIniFile iniFile) throws IOException {
		String[] lines = formatted.split("\n");
		String result = "";
		for ( String line : lines ) {
			String fixed = fixBitchLine(line,iniFile);
			result += fixed + "\n";
		}
		return result;
	}
	
	public static String removeBadCharacters(String content, W2AIniFile iniFile) throws IOException {
		String fixed = content.replace("Â&nbsp;", "");
		return fixed;
	}
	

}
