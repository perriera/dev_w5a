package org.w2a.tests;

import org.junit.Assert;
import org.junit.Test;
import org.w0a.types.SourceHTMLFileEntry;
import org.w2a.ngsymbol.NgSymbol;

public class NgMorphTest extends NgTestSetup {
	
	@Test()
	public void testGetSymbolizedHtml() throws Exception {
		
		String key = "about-cpp/about-cpp.html";
		SourceHTMLFileEntry page = this.sourceExtractor.getHTMLPage(key);
		Assert.assertNotNull(page);

		NgSymbol ngSymbol = new NgSymbol(key,page.getContent(),iniFile);
		Assert.assertNotNull(ngSymbol);
		
		String result = ngSymbol.getSymbolizedHtml();
		
		Assert.assertTrue(result.length()>0);
		Assert.assertTrue(result.contains("app-sidebar-controller"));
		Assert.assertTrue(result.contains("app-back-controller"));
		Assert.assertTrue(result.contains("app-code-controller"));
		
	}
	
}
