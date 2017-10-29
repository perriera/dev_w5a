package org.w2a.ngmorph;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;

public class NgMorph {


	static public String morphSource(String content, W2AIniFile iniFile) throws Exception {
		
		String morphSymbol = iniFile.getMorphIdentifier();
		Document doc = HTMLEdit.parse(content);
		
		Elements links = doc.select("*["+morphSymbol+"]");
		for (Element link : links) {
			
			String name = link.attr(morphSymbol);
			
			Element ngCustomDiv = doc.createElement(name); // Create the new element
			ngCustomDiv.attr("id",link.attr("id"));
			for (Attribute a : link.attributes().asList()) {
				ngCustomDiv.attributes().put(a);
			}
			ngCustomDiv.html(link.html());
			link.replaceWith(ngCustomDiv); // Replace element with new one
			
		} 
		
		String editted = doc.body().toString();
	    editted = HTMLEdit.stripFirstAndLast(editted);
	    
		return editted;
		
	}
	
	
}
