package org.w2a.tests;

import java.io.File;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;
import org.w2a.ngcomponent.NgComponent;
import org.w2a.ngcomponent.NgIndexComponent;
import org.w2a.ngtypes.NgHTMLFile;
import org.w2a.ngtypes.NgTextFile;
import org.w2a.ngcomponent.NgBaseComponent;
import org.w0a.exceptions.InvalidTypeException;
import org.w0a.extractor.SourceMap;
import org.w0a.interfaces.SourceEntryInterface;
import org.w0a.types.SourceHTMLFileEntry;

public class NgHTMLTest extends NgTestSetup {

	@Test
	public void testGetPages() throws Exception {
		SourceMap<SourceHTMLFileEntry> pages = this.sourceExtractor.getHTMLPages();
		Assert.assertNotNull(pages);
	}

	@Test
	public void testGetPage() throws Exception {
		SourceMap<SourceHTMLFileEntry> pages = this.sourceExtractor.getHTMLPages();
		Assert.assertNotNull(pages);
		SourceHTMLFileEntry page = pages.first();
		Assert.assertNotNull(page);
	}

	@Test(expected = InvalidTypeException.class)
	public void testInvalidTypeException() throws Exception {
		this.sourceExtractor.getHTMLPage("images/001.png");
	}

	@Test
	public void testGetHTMLPageNull() throws Exception {
		SourceHTMLFileEntry page = this.sourceExtractor.getHTMLPage("xxx/about-cpp.html");
		Assert.assertNull(page);
	}

	@Test()
	public void testGetSelectPage() throws Exception {
		SourceHTMLFileEntry page = this.sourceExtractor.getHTMLPage("about-cpp/about-cpp.html");
		Assert.assertNotNull(page);
	}

	@Test()
	public void testCreatePageComponent() throws Exception {

		String key = "about-cpp/about-cpp.html";
		SourceHTMLFileEntry page = this.sourceExtractor.getHTMLPage(key);
		Assert.assertNotNull(page);

		NgComponent ngc = new NgBaseComponent(key, page.getContent(), iniFile);
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

	@Test()
	public void testCreatePages() throws Exception {

		for (Entry<String, SourceEntryInterface> entry : extractor) {

			if ( (entry.getValue() instanceof NgHTMLFile) && !(entry.getValue() instanceof NgIndexComponent) ) {
				
				NgHTMLFile page = (NgHTMLFile)entry.getValue();
				Assert.assertNotNull(page);

				page.input();
				page.output();

				Assert.assertTrue(new File(page.getNgFile().getComponentDirectory()).exists());
				Assert.assertTrue(new File(page.getNgFile().getComponentHTMLFilename()).exists());
				Assert.assertTrue(new File(page.getNgFile().getComponentItem(".css")).exists());
				Assert.assertTrue(new File(page.getNgFile().getComponentItem(".html")).exists());
				Assert.assertTrue(new File(page.getNgFile().getComponentItem(".spec.ts")).exists());
				Assert.assertTrue(new File(page.getNgFile().getComponentItem(".ts")).exists());

				String htmlContent = NgTextFile.input(page.getNgFile().getComponentHTMLFilename(), iniFile);
				Assert.assertNotNull(htmlContent);
				
			}
		}

	}

}
