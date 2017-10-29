package org.w2a.tests;

import java.io.File;
import org.w2a.exceptions.MockProjectRequiredException;

public class MockProjectSetup extends Setup {

	public static void setupMockProjectClass(String testFilename ) throws Exception {
		if ( !new File("mock-project").exists() ) 
			throw new MockProjectRequiredException();
		zipupTestFiles(testFilename);
		deleteTestFiles(testFilename);
		unZipTestFiles(testFilename);
	}

	public static void tearDownMockProjectClass(String testFilename ) throws Exception {
		deleteZipFile(testFilename);
	}
	
	public void setupMockProject(String testFilename ) throws Exception {
	}
	
	public void tearDownMockProject(String testFilename ) throws Exception {
		deleteTestFiles(testFilename);
		unZipTestFiles(testFilename);
	}
	
}
