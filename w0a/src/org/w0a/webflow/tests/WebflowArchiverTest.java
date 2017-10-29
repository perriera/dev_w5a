package org.w0a.webflow.tests;

import java.io.File;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w0a.IniFile;
import org.w0a.exceptions.FeatureNotImplementedException;
import org.w0a.tests.IniFileTest;
import org.w0a.tests.Setup;
import org.w0a.webflow.WebflowExtractor;
import org.w0a.webflow.WebflowArchiver;

public class WebflowArchiverTest extends Setup {
	

	
	@Before
	public void setUp() throws Exception {
		deleteTestFiles();
		copyFile(new File("assignment-a200ad.webflow.zip"), new File(assignmentFile));
		IniFile iniFile = new IniFile(IniFileTest.testFilename);
		WebflowExtractor archive = new WebflowExtractor(iniFile);
		Assert.assertNotNull(archive);
		archive.input();
		archive.output();
		Assert.assertTrue(new File(iniFile.getDestination()+"/index.html").isFile());
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
		WebflowArchiver archiver = new WebflowArchiver(iniFile);
		archiver.input();
		Assert.assertNotNull(archiver);
		Assert.assertTrue(new File(iniFile.getDestination()+"/index.html").isFile());
	}

	@Test(expected=FeatureNotImplementedException.class)
	public void testOutput() throws Exception {
		IniFile iniFile = new IniFile(IniFileTest.testFilename);
		WebflowArchiver archiver = new WebflowArchiver(iniFile);
		Assert.assertNotNull(archiver);
		archiver.input();
		archiver.output();
		Assert.assertTrue(new File(iniFile.getDestination()+"/index.html").isFile());
	}
	
	@Test
	public void testInputFileNotFound() throws Exception {
		deleteTestFiles();
		IniFile iniFile = new IniFile(IniFileTest.testFilename);
		WebflowArchiver archiver = new WebflowArchiver(iniFile);
		archiver.input();
		Assert.assertNotNull(archiver);
	}
	
}

