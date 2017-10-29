package org.w2a.tests;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.w2a.ngcomponent.NgComponentExtractor;
import org.w2a.ngmodule.NgModuleCompilier;
import org.w2a.ngtypes.NgTextFile;

public class NgModuleCompilerTest extends NgTestSetup {

	@Test()
	public void testFormatComponent() throws Exception {
		
		NgModuleCompilier compiler = new NgModuleCompilier();
		Assert.assertNotNull(compiler);
		
		compiler.input(new NgComponentExtractor(extractor));
		compiler.output();
		
		String indexFilename = iniFile.getDestination()+"/index.html";
		boolean test = new File(indexFilename).isFile();
		Assert.assertTrue(test);
		String htmlContent = NgTextFile.input(indexFilename, iniFile);
		Assert.assertTrue(htmlContent.contains("<app-root></app-root>"));

	}
	

	
}
