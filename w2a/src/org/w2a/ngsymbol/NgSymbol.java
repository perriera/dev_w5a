package org.w2a.ngsymbol;

/**
 * class NgSymbol
 * 
 * This class will take a HTML file and look for any div that contains the
 * ng-component attribute. Where upon, the entire div that contains that
 * attribute will be sectioned off into it's own NgSymbol object. 
 * 
 * Webflow has a feature by which a section of HTML code can be used as a
 * entity that can appear on one of more HTML pages and also more than just
 * once upon a page. 
 * 
 * Webflow prevents the use of symbols embedded in other symbols but there is
 * nothing to prevent the user from using ng-component inside other divs that
 * contain ng-component. So recurision is possible. 
 * 
 * Certain rules apply however such that a parent component and an embedded 
 * component cannot have the same name, nor can a parent component be embedded
 * inside a child component. While that might be possible via the source file
 * it will be rejected here. 
 * 
 * All symbols have a singleton relationship with the project and therefore should
 * exist in a global namespace therefore a unique identifier is required.
 * 
 * While the use of symbols provide a convenience, there should not be a need to
 * go nuts on the use of symbols, hence only a basic embedding capability is required 
 * at this point in time.
 * 
 * @author perry
 *
 */

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w0a.IniFile;
import org.w0a.exceptions.FeatureNotRequiredException;
import org.w0a.interfaces.SourceObserver;
import org.w0a.types.SourceHTMLFileEntry;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.ngcomponent.NgBaseComponent;

public class NgSymbol implements SourceHTMLFileEntry, SourceObserver<String> {

	String key;
	String webflowHtml;
	String symbolizedHtml;
	W2AIniFile iniFile;
	NgBaseComponent pageComponent;
	Map<String, NgSymbolComponent> symbolsMap;

	/**
	 * class NgSymbol construct
	 * 
	 * @param key
	 * @param webflowContent
	 * @param iniFile
	 */
	public NgSymbol(String key, String webflowContent, W2AIniFile iniFile) {
		this.key = key;
		this.webflowHtml = webflowContent;
		this.iniFile = iniFile;
	}

	@Override
	public IniFile getXIniFile() {
		return iniFile;
	}

	@Override
	public String getContent() {
		return this.symbolizedHtml;
	}

	@Override
	public void setContent(String html) {
		this.symbolizedHtml = html;
	}
	
	/**
	 * input()
	 * 
	 * Take the original HTML source code and identify all symbols inside
	 * the source. Create a map entry for each symbol identified.
	 * 
	 */
	
	public HashMap<String,NgSymbolComponent> getSymbols() throws Exception {
		HashMap<String,NgSymbolComponent> results = new HashMap<String,NgSymbolComponent>();
		Document doc = HTMLEdit.parse(webflowHtml);
		String ngComponent = iniFile.getSymbolsIdentifier();
		observe("symbolsIdentifier is "+ngComponent);
		Elements ngComponents = doc.select("div["+ngComponent+"]");
		int cnt = 0;
		for (Element div : ngComponents) {
			String name = div.attr(ngComponent);
			observe("symbolsIdentifier found: "+name);
			name = camelToNonCamel(name);
			div.attr(ngComponent,name);
			String content = div.toString();
			NgSymbolComponent comp = new NgSymbolComponent(name,content,this.iniFile);
			comp.input();
			results.put(name, comp);
			cnt++;
		}
		observe("symbolsIdentifier total for page: "+cnt);
		return results;
	}
	
	public String getSymbolizedHtml() throws Exception {
		Document doc = HTMLEdit.parse(webflowHtml);
		String ngComponent = iniFile.getSymbolsIdentifier();
		Elements ngComponents = doc.select("div["+ngComponent+"]");
		for (Element div : ngComponents) {
			String name = div.attr(ngComponent);
			name = camelToNonCamel(name);
			div.attr(ngComponent,name);
			Element ngCustomDiv = doc.createElement("app-"+name); // Create the new element
			ngCustomDiv.attr("id",div.attr("id"));
			for (Attribute a : div.attributes().asList()) {
				ngCustomDiv.attributes().put(a);
			}
			div.replaceWith(ngCustomDiv); // Replace element with new one
		}
		Element body = doc.body();
		String result = body.toString();
		result = HTMLEdit.stripFirstAndLast(result);
		return result;
	}
	
	@Override
	public void input() throws Exception {
		this.symbolsMap = this.getSymbols();
		this.symbolizedHtml = this.getSymbolizedHtml();
//		this.pageComponent =  new NgPageComponent(key, symbolizedHtml, iniFile);
//		this.pageComponent.input();
	}

	/**
	 * output()
	 * 
	 * Outputs the component source for page and the symbols.
	 * 
	 */
	
	@Override
	public void output() throws Exception {
		this.pageComponent.output();
		this.outputSymbolsOnly();
	}
	
	public void outputSymbolsOnly() throws Exception {
		for (Entry<String, NgSymbolComponent> entry : symbolsMap.entrySet()) {
			NgSymbolComponent symbol = entry.getValue();
			symbol.output();
		}
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
	
	public static String camelToNonCamel(String from) {
		String to = "";
		for ( int i = 0; i<from.length(); i++ ) {
			char c = from.charAt(i);
			if ( Character.isUpperCase(c) && i>0 ) {
				to += ("-"+c).toLowerCase();
			}
			else 
				to += (""+c).toLowerCase();
		}
		return to;
	}
	
	public static String nonCamelToCamel(String from) {
		String to = "";
		boolean capMode = true;
		for ( int i = 0; i<from.length(); i++ ) {
			char c = from.charAt(i);
			if ( Character.isLetter(c) && capMode ) {
				to += (""+c).toUpperCase();
				capMode = false;
			}
			else {
				if ( c=='-' || c=='.' ) {
					capMode = true;
				} else {
					if ( Character.isDigit(c) ) {
						capMode = true;
					}
					to += (""+c).toLowerCase();
				}
			}
		}
		return to;
	}

	private static int linecnt = 0;
	
	@Override
	public String observe(String t) {
		if ( ++linecnt>30 ) {
			linecnt = 0;
			System.out.println(".");
		}
		else
			System.out.print(".");
		return t;
	}

	@Override
	public boolean isExternalPage() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
