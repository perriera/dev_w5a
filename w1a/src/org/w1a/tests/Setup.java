package org.w1a.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Assert;
import org.w0a.IniFile;

public class Setup {

	static String assignmentFile1 = "bodytest.webflow.zip";
	static String assignmentFile2 = "contenttest.webflow.zip";

	public void deleteDir(File file) {
	    File[] contents = file.listFiles();
	    if (contents != null) {
	        for (File f : contents) {
	            deleteDir(f);
	        }
	    }
	    file.delete();
	}
	
	public void deleteTestFiles() throws Exception {
		IniFile iniFile = new IniFile(Angular1BodyFormatTest.testFilename);
		if ( new File(iniFile.getDestination()+"/index.html").isFile() ) {
			Assert.assertTrue(new File(iniFile.getDestination()+"/index.html").delete());
			deleteDir(new File(iniFile.getDestination()));
		}
	}
	
	public void copyFile(File source, File dest) throws IOException {
		if (!dest.exists())
			Files.copy(source.toPath(), dest.toPath());
	}
	
}
