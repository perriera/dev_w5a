package org.w2a.ngcomponent;

import java.util.Map;
import java.util.Map.Entry;

import org.w2a.W2AIniFile;
import org.w2a.ngsymbol.NgSymbol;
import org.w2a.ngsymbol.NgSymbolComponent;

/*
 * NgPageComponent
 * 
 * The purpose of this class is to determine the contents of each HTML page 
 * with respect to HTML pages in Webflow that need to be converted into
 * Angular 2 components. Each HTML page in the Webflow archive needs to be
 * parsed to locate any divs that have a ng-component custom attributes 
 * assigned to them and such assigned as NgSymbolComponent objects. 
 * 
 * All HTML pages in a Webflow archive by default are made into Angular 2
 * component objects with the only exception being the index.html file in 
 * the Webflow archive, which is a special case as well as parts of the
 * HTML page that are marked as symbols.
 * 
 */

public class NgBaseComponent extends NgComponent {

	NgSymbol ngSymbol;
	String symbolizedHtml;
	Map<String, NgSymbolComponent> symbolsMap;
	
	public NgBaseComponent(String key, String newContent, W2AIniFile iniFile) throws Exception {
		super(key, newContent, iniFile.getViewsDir(), iniFile);
		this.ngSymbol = new NgSymbol(key,newContent,iniFile);
	}
	
	@Override
	public void input() throws Exception {
		
		/*
		 * Determine what symbols are in the HTML page that
		 * need to be converted into NgSymbolComponent objects
		 * during output().
		 */
		
		this.symbolsMap = this.ngSymbol.getSymbols();
		
		/*
		 * Determine the 'symbolized' version of the Webflow HTML
		 * file such that it now recognizes the custom attribute
		 * ng-component.
		 * 
		 */
		
		this.symbolizedHtml = this.ngSymbol.getSymbolizedHtml();
		
		/*
		 * Now update the content of the page with the 'symbolized'
		 * content from the original Webflow HTML page.
		 */
		
		this.setContent(this.symbolizedHtml);
		
		/*
		 * Now perform a NgBaseComponent input which will create
		 * the actual Angular component as well as read in any 
		 * existing contents if it already exists.
		 */
		
		super.input();
		
		/*
		 * Also perform an input() on each and every NgSymbolComponent
		 * detected in this page.
		 */
		
		for (Entry<String, NgSymbolComponent> entry : symbolsMap.entrySet()) {
			NgSymbolComponent symbol = entry.getValue();
			symbol.input();
		}
		
	}
	
	@Override
	public void output() throws Exception {
		
		/*
		 * Simply output the contents of this 'symbolized' page.
		 */
		
		super.output();
		
		/*
		 * Then output() any NgSymbolComponent objects detected in
		 * this page.
		 */
		
		for (Entry<String, NgSymbolComponent> entry : symbolsMap.entrySet()) {
			NgSymbolComponent symbol = entry.getValue();
			symbol.output();
		}
		
	}

	@Override
	public boolean isExternalPage() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
