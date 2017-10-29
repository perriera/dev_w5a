package org.w2a.tests;

import org.junit.Assert;
import org.junit.Test;
import org.w0a.types.SourceHTMLFileEntry;
import org.w2a.ngsymbol.NgSymbol;

public class NgSymbolTest extends NgTestSetup {
	
//	@Test()
//	public void testGetSymbols() throws Exception {
//		
//		String key = "about-cpp/about-cpp.html";
//		SourceHTMLFileEntry page = this.sourceExtractor.getHTMLPage(key);
//		Assert.assertNotNull(page);
//
//		NgSymbol ngSymbol = new NgSymbol(key,page.getContent(),iniFile);
//		Assert.assertNotNull(ngSymbol);
//		
//		ngSymbol.input(); 
//		
//		HashMap<String,NgSymbolComponent> results = ngSymbol.getSymbols();
//		
//		Assert.assertTrue(results.size()==3);
//		Assert.assertTrue(results.containsKey("sidebar-controller"));
//		Assert.assertTrue(results.containsKey("back-controller"));
//		Assert.assertTrue(results.containsKey("code-controller"));
//		
//	}
	
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
	
//	@Test()
//	public void testCreate() throws Exception {
//		
//		String key = "about-cpp/about-cpp.html";
//		SourceHTMLFileEntry page = this.sourceExtractor.getHTMLPage(key);
//		Assert.assertNotNull(page);
//
//		NgSymbol ngSymbol = new NgSymbol(key,page.getContent(),iniFile);
//		Assert.assertNotNull(ngSymbol);
//		
//		ngSymbol.input();
//		ngSymbol.output();
//		
//		NgBaseComponent ngComponent = new NgPageComponent(key,ngSymbol.getContent(),iniFile);
//		Assert.assertNotNull(ngComponent);
//		
//		ngComponent.input();
//		ngComponent.output();
//		
//		Assert.assertTrue(new File(ngComponent.getNgFile().getComponentDirectory()).exists());
//		Assert.assertTrue(new File(ngComponent.getNgFile().getComponentHTMLFilename()).exists());
//		Assert.assertTrue(new File(ngComponent.getNgFile().getComponentItem(".css")).exists());
//		Assert.assertTrue(new File(ngComponent.getNgFile().getComponentItem(".html")).exists());
//		Assert.assertTrue(new File(ngComponent.getNgFile().getComponentItem(".spec.ts")).exists());
//		Assert.assertTrue(new File(ngComponent.getNgFile().getComponentItem(".ts")).exists());
//		
//		String htmlContent = HTMLEdit.input(ngComponent.getNgFile().getComponentHTMLFilename(), iniFile);
//		Assert.assertTrue(htmlContent.contains("<app-sidebar-controller"));
//		
//	}
	
}
