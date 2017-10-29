package org.w1a.tests;

import java.io.File;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w1a.W1AIniFile;
import org.w1a.formats.Angular1ContentFormat;
import org.w0a.IniFile;
import org.w0a.webflow.WebflowExtractor;

public class Angular1ContentFormatTest extends Setup {

	public static String testFilename = "angular1.contenttest.ini";
	
	static String assignmentFile1 = "bodytest.webflow.zip";
	static String assignmentFile2 = "contenttest.webflow.zip";
	
	@Before
	public void setUp() throws Exception {
		copyFile(new File("unified-objects-inc.webflow.zip"), new File(assignmentFile1));
		copyFile(new File("cplusplusorg.webflow.zip"), new File(assignmentFile2));
	}
	
	@After
	public void tearDown() throws Exception {
		deleteTestFiles();
		if ( new File(assignmentFile1).exists() )
			new File(assignmentFile1).delete();
		if ( new File(assignmentFile2).exists() )
			new File(assignmentFile2).delete();
	}

	@Test
	public void testAngular1Formatter() throws Exception {
		IniFile iniFile = new W1AIniFile(testFilename);
		WebflowExtractor extractor = new WebflowExtractor(iniFile);
		Assert.assertNotNull(extractor);
		extractor.input();
		Angular1ContentFormat archiver = new Angular1ContentFormat(extractor);
		Assert.assertNotNull(archiver);
		Assert.assertFalse(new File(iniFile.getDestination()+"/index.html").isFile());
	}

	@Test
	public void testInput() throws Exception {
		IniFile iniFile = new W1AIniFile(testFilename);
		WebflowExtractor extractor = new WebflowExtractor(iniFile);
		Assert.assertNotNull(extractor);
		extractor.input();
		Angular1ContentFormat archiver = new Angular1ContentFormat(extractor);
		Assert.assertNotNull(archiver);
		archiver.input();
		Assert.assertFalse(new File(iniFile.getDestination()+"/index.html").isFile());
	}

	@Test
	public void testOutput() throws Exception {
		IniFile iniFile = new W1AIniFile(testFilename);
		WebflowExtractor extractor = new WebflowExtractor(iniFile);
		Assert.assertNotNull(extractor);
		extractor.input();
		Angular1ContentFormat archiver = new Angular1ContentFormat(extractor);
		Assert.assertNotNull(archiver);
		archiver.input();
		archiver.output();
		Assert.assertTrue(new File(iniFile.getDestination()+"/index.html").isFile());
		Assert.assertTrue(new File(iniFile.getDestination()+"/index.htm").isFile());
	}

}
