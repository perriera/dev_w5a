package org.w1a.tests;

import org.junit.Test;
import org.w0a.IniFile;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.ini4j.InvalidFileFormatException;
import org.junit.Assert;

public class W1AIniFileTest {
	
	public static String testFilename = "webflow.test.ini";

	@Test
	public void testIniFile() throws InvalidFileFormatException, FileNotFoundException, IOException {
		IniFile iniFile = new IniFile(testFilename);
		Assert.assertNotNull(iniFile);
	}

	@Test
	public void testGetDirective() throws InvalidFileFormatException, FileNotFoundException, IOException {
		IniFile iniFile = new IniFile(testFilename);
		Assert.assertNotNull(iniFile);
	}

	@Test
	public void testGetSource() throws Exception {
		IniFile iniFile = new IniFile(testFilename);
		String source = iniFile.getDirective("Common","source");
		Assert.assertNotNull(source);
		Assert.assertTrue(source.equals("./assignment.webflow.zip"));
	}

	@Test
	public void testGetSourceFilename() throws Exception {
		IniFile iniFile = new IniFile(testFilename);
		String source = iniFile.getSourceFilename();
		Assert.assertNotNull(source);
		Assert.assertTrue(source.equals("assignment.webflow.zip"));
	}

	@Test
	public void testGetSourceDir() throws Exception {
		IniFile iniFile = new IniFile(testFilename);
		String source = iniFile.getSourceDir();
		Assert.assertNotNull(source);
		Assert.assertTrue(source.equals("assignment.webflow/"));
	}

	@Test
	public void testGetDestination() throws Exception {
		IniFile iniFile = new IniFile(testFilename);
		String source = iniFile.getDestination();
		Assert.assertNotNull(source);
		Assert.assertTrue(source.equals("./public/"));
	}

	@Test
	public void testGetIgnoreDir()  throws Exception {
		IniFile iniFile = new IniFile(testFilename);
		String[] source = iniFile.getIgnoreDir();
		Assert.assertNotNull(source);
		Assert.assertTrue(source.length==1);
		Assert.assertTrue(source[0].equals("__MACOSX/"));
	}

}
