package org.w2a.ngtypes;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w0a.exceptions.FeatureNotRequiredException;
import org.w0a.types.SourceHTMLFileEntry;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.ngfile.NgFile;
import org.w2a.ngsymbol.NgSymbol;

public class NgHTMLFile implements SourceHTMLFileEntry {

	NgSymbol ngSymbol;
	W2AIniFile iniFile;
	NgFile ngFile;
	SourceHTMLFileEntry entry;
	
	public NgHTMLFile(String key, SourceHTMLFileEntry entry, W2AIniFile iniFile) throws Exception {
		this.ngSymbol = new NgSymbol(key,entry.getContent(),iniFile);
		this.iniFile = iniFile;
		this.setContent(entry.getContent());
		this.ngFile = new NgFile(key, iniFile.getViewsDir(), iniFile);
		this.entry = entry;
	}
	
	@Override
	public W2AIniFile getXIniFile() {
		return iniFile;
	}
	
	public NgFile getNgFile() {
		return ngFile;
	}

	@Override
	public String getContent() {
		return ngSymbol.getContent();
	}

	@Override
	public void setContent(String text) {
		ngSymbol.setContent(text);
	}

	@Override
	public void input() throws Exception {
		ngSymbol.input();
	}

	@Override
	public void output() throws Exception {
		ngSymbol.output();
	}
	
	@Override
	public void input(String filename) throws Exception {
		throw new FeatureNotRequiredException();
	}

	@Override
	public void input(InputStream is) throws Exception {
		throw new FeatureNotRequiredException();
	}

	@Override
	public void output(String name) throws Exception {
		ngSymbol.input();
		ngSymbol.output();
	}
	
	//
	// Static Members below ... 
	//
	
	public static String getNewId(String id, Map<String, Integer> counterMap) {
		int idValue = 1;
		if ( counterMap.get(id) != null )
			idValue = counterMap.get(id).intValue() + 1;
		counterMap.put(id, new Integer(idValue));
		return id+idValue;
	}
	
	public static String checkForDuplicateIds(String html, String pageName, W2AIniFile iniFile) throws Exception {
		Document doc = HTMLEdit.parse(html);
		Elements divs = doc.body().select("*[id]");
		Map<String,Element> map = new HashMap<String,Element>();
		Map<String,Integer> counterMap = new HashMap<String,Integer>();
		for (Element div : divs) {
			String id = div.attr("id");
			// check for no id in the ng-component
			String ngComponentWithNoId = div.attr("ng-component");
			if ( ngComponentWithNoId!=null && id.length()==0) {
				String defaultId = NgSymbol.nonCamelToCamel(ngComponentWithNoId);
				div.attr("id",defaultId);
			}
			// resume normal checks
			if ( id.length()>0 ) {
				if ( map.get(id)!=null ) {
					// throw new DuplicateIdException(pageName,id,div.tagName());
					id = getNewId(id,counterMap);
					div.attr("id",id);
				}
				else
					map.put(id, div);
			} 
		} 
		String editted = doc.body().toString();
		editted = HTMLEdit.stripFirstAndLast(editted);
		return editted;
	}

	public static String checkForMisplacedNgRouter(String mergedHtml, String key, W2AIniFile iniFile2) throws IOException {
		if ( !key.equals("index.html") && !key.equals("home.html") ) {
			Document doc = HTMLEdit.parse(mergedHtml);
			Element misplaceNgRouter = doc.body().select("*[ng-router]").first();
			if ( misplaceNgRouter!=null ) {
				// TODO: Implement a messaging system to inform the user of mistakes like this!
				System.out.println("\n\tMisplaced ng-router detected in '" + key + "'\n" );
				misplaceNgRouter.removeAttr("ng-router");
				mergedHtml = doc.body().toString();
				mergedHtml = HTMLEdit.stripFirstAndLast(mergedHtml);
			}
			
		}
		return mergedHtml;
	}

	@Override
	public boolean isExternalPage() {
		// TODO Auto-generated method stub
		return this.entry.isExternalPage();
	}
	
	
}

