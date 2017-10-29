package org.w2a.tests;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.w2a.ngcomponent.NgComponentExtractor;
import org.w2a.ngtypes.NgTextFile;

public class NgExternalTest extends NgTestSetupB {
	
	@Test()
	public void testFormatComponent() throws Exception {

		NgComponentExtractor formatter = new NgComponentExtractor(extractor);
		Assert.assertNotNull(formatter);

		formatter.input();
		formatter.output();

		String indexFilename = iniFile.getDestination() + "/index.html";
		boolean test = new File(indexFilename).isFile();
		Assert.assertTrue(test);
		String htmlContent = NgTextFile.input(indexFilename, iniFile);
		Assert.assertTrue(htmlContent.contains("<app-root></app-root>"));

	}
	
}
