package org.w2a.tests;

import java.io.File;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w2a.W2AIniFile;
import org.w2a.ngcomponent.NgComponentExtractor;
import org.w0a.IniFile;
import org.w0a.webflow.WebflowExtractor;

public class InspectNgComponentFormat extends Setup {

	public static String testFilename = "files/assignment.ini";
	
	@Before
	public void setUp() throws Exception {
		copyFile(new File("assignment-a200ad.webflow.zip"), new File(assignmentFile));
	}
	
	@After
	public void tearDown() throws Exception {
		if ( new File(assignmentFile).exists() )
			new File(assignmentFile).delete();
	}

	@Test
	public void testOutput() throws Exception {
		IniFile iniFile = new W2AIniFile(testFilename);
		WebflowExtractor extractor = new WebflowExtractor(iniFile);
		Assert.assertNotNull(extractor);
		extractor.input();
		NgComponentExtractor formatter = new NgComponentExtractor(extractor);
		Assert.assertNotNull(formatter);
		formatter.input();
		formatter.output();
		Assert.assertTrue(new File(iniFile.getDestination()+"/index.html").isFile());
		Assert.assertTrue(new File(iniFile.getDestination()+"/app/app.component.html").isFile());
	}

}
