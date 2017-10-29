package org.w2a.tests;

import org.junit.Test;
import org.w0a.IniFile;
import org.w2a.W2AIniFile;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.ini4j.InvalidFileFormatException;
import org.junit.Assert;

public class W2AIniFileTest {
	
	public static String testFilename = "files/webflow.test.ini";

	@Test
	public void testIniFile() throws InvalidFileFormatException, FileNotFoundException, IOException {
		IniFile iniFile = new W2AIniFile(testFilename);
		Assert.assertNotNull(iniFile);
	}

	@Test
	public void testFormat() throws Exception {
		W2AIniFile iniFile = new W2AIniFile(testFilename);
		Assert.assertNotNull(iniFile);
		String format = iniFile.getFormat();
		Assert.assertNotNull(format);
		Assert.assertTrue(format.equals("NgComponent"));
	}


}
