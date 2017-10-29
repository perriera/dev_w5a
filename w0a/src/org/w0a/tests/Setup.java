package org.w0a.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Assert;
import org.w0a.IniFile;

public class Setup {

	protected String assignmentFile = "assignment.webflow.zip";
	
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
		IniFile iniFile = new IniFile(IniFileTest.testFilename);
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
