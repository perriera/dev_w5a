package org.w1a.tests;

import java.io.File;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w0a.IniFile;
import org.w1a.Main;

public class MainTest extends Setup {

	@Before
	public void setUp() throws Exception {
		copyFile(new File("unified-objects-inc.webflow.zip"), new File(assignmentFile1));
		copyFile(new File("cplusplusorg.webflow.zip"), new File(assignmentFile2));
	}

	@After
	public void tearDown() throws Exception {
		deleteTestFiles();
		if (new File(assignmentFile1).exists())
			new File(assignmentFile1).delete();
		if (new File(assignmentFile2).exists())
			new File(assignmentFile2).delete();
	}

	@Test
	public void testMainStringArrayW1AIniFile_1() throws Exception {
		String[] args = { Angular1BodyFormatTest.testFilename };
		Main main = new Main(args);
		Assert.assertNotNull(main);
	}

	@Test
	public void testMainStringArrayW1AIniFile_2() throws Exception {
		String[] args = { Angular1ContentFormatTest.testFilename };
		Main main = new Main(args);
		Assert.assertNotNull(main);
	}

	@Test
	public void testMain_BodyFormat() throws Exception {
		String[] args = { Angular1BodyFormatTest.testFilename };
		Main.main(args);
		IniFile iniFile = new IniFile(Angular1BodyFormatTest.testFilename);
		Assert.assertTrue(new File(iniFile.getDestination() + "/index.html").exists());
	}

	@Test
	public void testMain_ContentFormat() throws Exception {
		String[] args = { Angular1ContentFormatTest.testFilename };
		Main.main(args);
		IniFile iniFile = new IniFile(Angular1ContentFormatTest.testFilename);
		Assert.assertTrue(new File(iniFile.getDestination() + "/index.html").exists());
	}

}
