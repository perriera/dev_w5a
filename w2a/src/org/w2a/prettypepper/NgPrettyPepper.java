package org.w2a.prettypepper;

import java.util.Vector;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.w0a.interfaces.ObjectCompilerInterface;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.ngcomponent.NgComponentExtractor;

public class NgPrettyPepper implements ObjectCompilerInterface<String> {

	String content;
	String formatted;
	W2AIniFile iniFile;

	public NgPrettyPepper(String content, W2AIniFile iniFile) throws Exception {
		this.content = content;
		this.formatted = this.content;
		this.iniFile = iniFile;
		this.input(content);
	}

	static public String[] findDivs(String line) {
		Vector<String> divs = new Vector<String>();
		line = line.trim();
		for (int i = 0; i < line.length();) {
			char c = line.charAt(i);
			if (c == '<') {
				String div = "" + c;
				while (i < line.length() - 1) {
					c = line.charAt(++i);
					if (c == '<')
						break;
					div += c;
				}
				divs.add(div);
			} else
				i++;
		}
		String[] results = new String[divs.size()];
		results = divs.toArray(results);
		return results;
	}

	static public String formattedDiv(String div) throws Exception {
		Document doc = HTMLEdit.parse(div);
		Element e = doc.body().select("*[id]").first();
		String result = "\n\t\t<" + e.tagName();
		for (Attribute a : e.attributes().asList()) {
			result += "\n\t\t" + a.toString();
		}
		result += ">";
		result += e.html() + "\n";
		return result;
	}
	
	static public String formatBRs(String text) throws Exception {
		String[] brs = text.split("<br>");
		if ( brs.length<2 )
			return text;
		String tab = text.split("\n")[1];
		tab = tab.split("<")[0];
		String results = "";
		for ( String line : brs ) {
			if ( results.length()>0 ) 
				results += tab + "<br>" + line + "\n";
			else 
				results += line + "\n";
		}
		return results;
	}

	static public String formatted(String text) throws Exception {
		String[] divs = findDivs(text);
		Vector<String> formattedDivs = new Vector<String>();
		for (String div : divs) {
			if (div.contains("id=\""))
				div = formattedDiv(div);
			formattedDivs.add(div);
		}
		String results = "";
		for (String div : formattedDivs) {
			results += div;
		}
		if (results.contains("\n<")) {
			results = results.replace("\n<", "<");
			results += "\n";
		}
		results = formatBRs(results);
		return results;
	}

	String format(String source) {
		String[] lines = source.split("\n");
		String formatted = "";
		int indent = 0;
		for (String line : lines) {
			if (line.trim().length() > 0) {
				if (line.trim().startsWith("</"))
					indent -= 2;
				for (int i = 0; i < indent; i++)
					formatted += " ";
			}
			formatted += line.trim() + "\n";
			if (line.trim().startsWith("<"))
				indent += 2;
			String[] parts = line.split("</");
			if (line.contains("</"))
				indent -= 2 * (parts.length - 1);
		}
		return formatted;
	}

	public static String adjustInputTag(String osource) {
		String source = HTMLEdit.ltrim(osource);
		// System.out.println(source);
		String[] lines = source.split("\n");
		String formatted = "";
		boolean inputModeStarted = false;
		int backtab = 0;
		for (String line : lines) {
			if (line.trim().length() > 0) {
				// TODO: Fix Glitch below ...
				String test = line;
				try {
					line = line.substring((backtab * 2));
					if (line.trim().charAt(0) != '<' && line.charAt(0) != ' ')
						line = test.trim();
				} catch (Exception e) {
					line = test.trim();
				}
				// TODO: Fix Glitch above ...
			}
			formatted += line + "\n";
			if (line.trim().startsWith("<input") || line.trim().startsWith("<img") || line.trim().startsWith("<br"))
				inputModeStarted = true;
			if (inputModeStarted) {
				if (line.trim().endsWith(">") || line.contains("<br>")) {
					inputModeStarted = false;
					backtab++;
				}
			}
		}
		return formatted.trim();
	}

	@Override
	public void input(String before) throws Exception {
		if (!iniFile.getExpandIds())
			return;
		Document doc = HTMLEdit.parse(content);
		String source = doc.body().toString();
		source = HTMLEdit.stripFirstAndLast(source);
		String[] lines = source.split("\n");
		String results = "";
		for (String line : lines) {
			if (line.contains("id=\""))
				line = formatted(line);
			else
				line = HTMLEdit.rtrim(line);
			results += line + "\n";
		}

		results = results.replace("\n\n\n", "\n\n");
		results = format(results);
		results = adjustInputTag(results);
		this.formatted = results;
	}

	@Override
	public String output() throws Exception {
		formatted = NgComponentExtractor.fixAmpIssue(formatted, iniFile);
		formatted = NgComponentExtractor.fixBitchIssue(formatted, iniFile);
		formatted = NgComponentExtractor.removeBadCharacters(formatted, iniFile);
		return formatted;
	}

}
