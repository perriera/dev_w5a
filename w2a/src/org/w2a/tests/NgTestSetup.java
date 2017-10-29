package org.w2a.tests;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.w2a.W2AIniFile;
import org.w2a.ngcomponent.NgComponentExtractor;
import org.w0a.extractor.SourceExtractor;
import org.w0a.webflow.WebflowExtractor;

public class NgTestSetup extends MockProjectSetup {

	protected static W2AIniFile iniFile;
	protected static WebflowExtractor extractor;
	
	NgComponentExtractor formatter;
	protected SourceExtractor sourceExtractor;
	
	static String getTestFilename() {
		return "files/assignment-cpp-dot-org.ini";
	}
	
	static String getSoureFilename() {
		return "files/cplusplus-dot-org.webflow.zip";
	}
	
	@BeforeClass
	static public void setUpClass() throws Exception {
		setupMockProjectClass(getTestFilename());
		if ( new File(assignmentFile).exists() )
			new File(assignmentFile).delete();
		copyFile(new File(getSoureFilename()), new File(assignmentFile));
		iniFile = new W2AIniFile(getTestFilename());
		extractor = new WebflowExtractor(iniFile);
		Assert.assertNotNull(extractor);
		extractor.input();
	}
	
	@AfterClass
	static public void tearDownClass() throws Exception {
		if ( new File(assignmentFile).exists() )
			new File(assignmentFile).delete();
		tearDownMockProjectClass(getTestFilename());
	}
	
	@Before
	public void setUp() throws Exception {
		setupMockProject(getTestFilename());
		this.formatter = new NgComponentExtractor(extractor);
		Assert.assertNotNull(this.formatter);
		Assert.assertTrue(new File(iniFile.getDestination()+"/index.html").isFile());
		this.formatter.input();
		this.sourceExtractor = formatter.getExtractor();
		Assert.assertNotNull(this.sourceExtractor);
	}

	@After
	public void tearDown() throws Exception {
		tearDownMockProject(getTestFilename());
	}
	
}
