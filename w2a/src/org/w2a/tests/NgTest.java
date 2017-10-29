package org.w2a.tests;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.w2a.ngcomponent.NgComponent;
import org.w2a.ngtypes.NgTextFile;
import org.w2a.ngcomponent.NgBaseComponent;
import org.w0a.types.SourceHTMLFileEntry;

public class NgTest extends NgTestSetup {

	
	@Test()
	public void testCreate() throws Exception {
		
		String key = "about-cpp/about-cpp.html";
		SourceHTMLFileEntry page = this.sourceExtractor.getHTMLPage(key);
		Assert.assertNotNull(page);

		NgComponent ngc = new NgBaseComponent(key,page.getContent(),iniFile);
		Assert.assertNotNull(ngc);
		
		ngc.input();
		ngc.output();
		
		Assert.assertTrue(new File(ngc.getNgFile().getComponentDirectory()).exists());
		Assert.assertTrue(new File(ngc.getNgFile().getComponentHTMLFilename()).exists());
		Assert.assertTrue(new File(ngc.getNgFile().getComponentItem(".css")).exists());
		Assert.assertTrue(new File(ngc.getNgFile().getComponentItem(".html")).exists());
		Assert.assertTrue(new File(ngc.getNgFile().getComponentItem(".spec.ts")).exists());
		Assert.assertTrue(new File(ngc.getNgFile().getComponentItem(".ts")).exists());
		
		String htmlContent = NgTextFile.input(ngc.getNgFile().getComponentHTMLFilename(), iniFile);
		Assert.assertTrue(htmlContent.contains("ng-controller=\"sidebar-controller\""));
		
	}
	
}
