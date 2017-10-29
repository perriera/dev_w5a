package org.w2a.tests;

import java.io.File;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;
import org.w0a.interfaces.SourceEntryInterface;
import org.w2a.ngtypes.NgJSFile;

public class NgJSTest extends NgTestSetup {

	@Test()
	public void testIndexComponent() throws Exception {
		
		String key = "js/webflow.js";
		NgJSFile ngc = (NgJSFile)this.sourceExtractor.get(key);;
		Assert.assertNotNull(ngc);
		
		ngc.input();
		ngc.output();
		
		Assert.assertTrue(new File(ngc.getHTMLAssetFilename()).exists());
		
	}
	
	@Test()
	public void testAllAssetsTests() throws Exception {
		
		for (Entry<String, SourceEntryInterface> entry : extractor) {

			if ( (entry.getValue() instanceof NgJSFile) ) {
				
				NgJSFile asset = (NgJSFile)entry.getValue();
				Assert.assertNotNull(asset);

				asset.input();
				asset.output();

				Assert.assertTrue(new File(asset.getHTMLAssetFilename()).exists());
				
			}
		}
	}
	


}
