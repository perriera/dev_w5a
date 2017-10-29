package org.w2a.tests;

import java.io.File;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;
import org.w0a.interfaces.SourceEntryInterface;
import org.w0a.types.SourceIndexFileEntry;
import org.w2a.ngcomponent.NgIndexComponent;
import org.w2a.ngsymbol.NgSymbolComponent;
import org.w2a.ngtypes.NgBinaryFile;
import org.w2a.ngtypes.NgTextFile;

public class NgAssetsTest extends NgTestSetup {

	@Test()
	public void testAssetComponent() throws Exception {
		
		String key = "images/cplusplusx256.png";
		NgBinaryFile ngc = (NgBinaryFile)this.sourceExtractor.get(key);;
		Assert.assertNotNull(ngc);
		
		ngc.input();
		ngc.output();
		
		Assert.assertTrue(new File(ngc.getHTMLAssetFilename()).exists());
		
	}
	
	@Test()
	public void testAllAssetsTests() throws Exception {
		
		for (Entry<String, SourceEntryInterface> entry : extractor) {

			if ( (entry.getValue() instanceof NgBinaryFile) ) {
				
				NgBinaryFile asset = (NgBinaryFile)entry.getValue();
				Assert.assertNotNull(asset);

				asset.input();
				asset.output();

				Assert.assertTrue(new File(asset.getHTMLAssetFilename()).exists());
				
			}
		}
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
		
		NgSymbolComponent symbol = new NgSymbolComponent("sidebar-controller","",iniFile);
	
		String symbolContent = NgTextFile.input(symbol.getHTMLAssetFilename(), iniFile);
		Assert.assertTrue(symbolContent.contains("img class=\"image-logo\""));
	}
	
//	@Test()
//	public void testAdjustLinks() throws Exception {
//		
////		testAssetComponent();
////	
//		String key = "index.html";
//		SourceIndexFileEntry page = this.sourceExtractor.getIndex();
//		Assert.assertNotNull(page);
//
//		NgIndexComponent ngc = new NgIndexComponent(key,page,iniFile);
//		Assert.assertNotNull(ngc);
//		
//		ngc.input();
//		ngc.output();
//
////		NgSymbolComponent symbol = new NgSymbolComponent("sidebar-controller","",iniFile);
////		
////		String htmlContent = HTMLEdit.input(symbol.getHTMLAssetFilename(), iniFile);
////		Assert.assertTrue(htmlContent.contains("<app-root></app-root>"));
//	
//		
////		String key = "images/001.png";
////		NgComponentAsset ngc = (NgComponentAsset)this.sourceExtractor.get(key);;
////		Assert.assertNotNull(ngc);
////		
////		ngc.input();
////		ngc.output();
////		
////		Assert.assertTrue(new File(ngc.getHTMLAssetFilename()).exists());
//		
//	}
//	

}
