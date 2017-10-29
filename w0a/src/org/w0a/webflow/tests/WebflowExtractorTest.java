package org.w0a.webflow.tests;

import java.io.File;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w0a.IniFile;
import org.w0a.tests.IniFileTest;
import org.w0a.tests.Setup;
import org.w0a.webflow.WebflowExtractor;

public class WebflowExtractorTest extends Setup {
	
	static String assignmentFile = "assignment.webflow.zip";

	@Before
	public void setUp() throws Exception {
		deleteTestFiles();
		//copyFile(new File("assignment-a200ad.webflow.zip"), new File(assignmentFile));
		copyFile(new File("cplusplus-dot-org.webflow.zip"), new File(assignmentFile));
	}

	@After
	public void tearDown() throws Exception {
		deleteTestFiles();
		if ( new File(assignmentFile).exists() )
			new File(assignmentFile).delete();
	}

	@Test
	public void testInput() throws Exception {
		IniFile iniFile = new IniFile(IniFileTest.testFilename);
		WebflowExtractor extractor = new WebflowExtractor(iniFile);
		Assert.assertNotNull(extractor);
		extractor.input();
	}

	@Test
	public void testOutput() throws Exception {
		IniFile iniFile = new IniFile(IniFileTest.testFilename);
		WebflowExtractor extractor = new WebflowExtractor(iniFile);
		Assert.assertNotNull(extractor);
		extractor.input();
		extractor.output();
		Assert.assertTrue(new File(iniFile.getDestination()+"/index.html").isFile());
	}
	
}
