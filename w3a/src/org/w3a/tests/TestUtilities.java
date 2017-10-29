package org.w3a.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.Assert;
import org.w0a.IniFile;

public class TestUtilities {

	static public void deleteDir(File file) {
	    File[] contents = file.listFiles();
	    if (contents != null) {
	        for (File f : contents) {
	            deleteDir(f);
	        }
	    }
	    file.delete();
	}
	
	static public void deleteTestFiles(IniFile iniFile) throws Exception {
		if ( new File(iniFile.getDestination()+"/index.html").isFile() ) {
			Assert.assertTrue(new File(iniFile.getDestination()+"/index.html").delete());
			deleteDir(new File(iniFile.getDestination()));
		}
	}
	
	static public void copyFile(File source, File dest) throws IOException {
		if (!dest.exists())
			Files.copy(source.toPath(), dest.toPath());
	}

	public static void copyFile(String testFilename, String dest) throws IOException {
		copyFile(new File(testFilename),new File(dest));
	}

	public static void deleteFile(File to) throws IOException {
		if (to.exists())
			to.delete();
	}
	
	public static void deleteFile(String to) throws IOException {
		deleteFile(new File(to));
	}
	
}
