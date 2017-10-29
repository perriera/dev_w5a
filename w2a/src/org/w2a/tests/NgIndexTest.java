package org.w2a.tests;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.w2a.ngcomponent.NgAppComponent;
import org.w2a.ngcomponent.NgCSSComponent;
import org.w2a.ngcomponent.NgIndexComponent;
import org.w2a.ngtypes.NgIndexFile;
import org.w2a.ngtypes.NgTextFile;
import org.w2a.ngcomponent.NgHomeComponent;
import org.w0a.types.SourceIndexFileEntry;

public class NgIndexTest extends NgTestSetup {

	@Test()
	public void testIndexComponent() throws Exception {
		
		String key = "index.html";
		SourceIndexFileEntry page = this.sourceExtractor.getIndex();
		Assert.assertNotNull(page);

		NgIndexFile ngc = new NgIndexFile(key,page,iniFile);
		Assert.assertNotNull(ngc);
		
		ngc.input();
		ngc.output();
		
		Assert.assertTrue(new File(ngc.getHTMLAssetFilename()).exists());
		
		String htmlContent = NgTextFile.input(ngc.getHTMLAssetFilename(), iniFile);
		Assert.assertTrue(htmlContent.contains("<app-root></app-root>"));
		
	}
	
	@Test()
	public void testAppComponent() throws Exception {
		
		String key = "index.html";
		SourceIndexFileEntry page = this.sourceExtractor.getIndex();
		Assert.assertNotNull(page);

		NgAppComponent ngc = new NgAppComponent(key,page,iniFile);
		Assert.assertNotNull(ngc);
		
		ngc.input();
		ngc.output();
		
		ngc.input();
		ngc.output();
		
		Assert.assertTrue(new File(ngc.getHTMLAssetFilename()).exists());
		
		String htmlContent = NgTextFile.input(ngc.getHTMLAssetFilename(), iniFile);
		Assert.assertTrue(htmlContent.contains("ng-controller=\"sidebar-controller\""));
		Assert.assertTrue(!htmlContent.contains("ng-router=\"true\""));
		Assert.assertTrue(htmlContent.contains("<router-outlet></router-outlet>"));
		
	}
	
	@Test()
	public void testHomeComponent() throws Exception {
		
		String key = "index.html";
		SourceIndexFileEntry page = this.sourceExtractor.getIndex();
		Assert.assertNotNull(page);

		NgHomeComponent ngc = new NgHomeComponent(key,page,iniFile);
		Assert.assertNotNull(ngc);
		
		ngc.input();
		ngc.output();
		
		Assert.assertTrue(new File(ngc.getNgFile().getComponentDirectory()).exists());
		Assert.assertTrue(new File(ngc.getNgFile().getComponentHTMLFilename()).exists());
		Assert.assertTrue(new File(ngc.getNgFile().getComponentItem(".css")).exists());
		Assert.assertTrue(new File(ngc.getNgFile().getComponentItem(".html")).exists());
		Assert.assertTrue(new File(ngc.getNgFile().getComponentItem(".spec.ts")).exists());
		Assert.assertTrue(new File(ngc.getNgFile().getComponentItem(".ts")).exists());
		
		String htmlContent = NgTextFile.input(ngc.getHTMLAssetFilename(), iniFile);
		Assert.assertTrue(!htmlContent.contains("ng-controller=\"sidebar-controller\""));
		Assert.assertTrue(htmlContent.contains("ng-router=\"true\""));
		Assert.assertTrue(!htmlContent.contains("<router-outlet></router-outlet>"));
		
	}
	
	@Test()
	public void testCSSComponent() throws Exception {

		SourceIndexFileEntry page = this.sourceExtractor.getIndex();
		Assert.assertNotNull(page);

		NgCSSComponent ngc = new NgCSSComponent(page, this.sourceExtractor, iniFile);
		Assert.assertNotNull(ngc);

		ngc.input();
		ngc.output();

		Assert.assertTrue(new File(ngc.getHTMLAssetFilename()).exists());
		String htmlContent = NgTextFile.input(ngc.getHTMLAssetFilename(), iniFile);
		Assert.assertTrue(htmlContent.contains("html {"));

	}
	
	@Test()
	public void testComponentIndex() throws Exception {
		
		String key = "index.html";
		SourceIndexFileEntry page = this.sourceExtractor.getIndex();
		Assert.assertNotNull(page);

		NgIndexComponent ngc = new NgIndexComponent(key,page,iniFile,this.sourceExtractor);
		Assert.assertNotNull(ngc);
		
		ngc.input();
		ngc.output();
		
		Assert.assertTrue(new File(ngc.getHTMLAssetFilename()).exists());

		String htmlContent = NgTextFile.input(ngc.getHTMLAssetFilename(), iniFile);
		Assert.assertTrue(htmlContent.contains("<app-root></app-root>"));
		
	}
	
}
