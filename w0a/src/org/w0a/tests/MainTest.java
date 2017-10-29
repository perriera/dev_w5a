package org.w0a.tests;

import java.io.File;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w0a.IniFile;
import org.w0a.Main;

public class MainTest extends Setup {
	
	@Before
	public void setUp() throws Exception {
		copyFile(new File("assignment-a200ad.webflow.zip"), new File(assignmentFile));
	}

	@After
	public void tearDown() throws Exception {
		deleteTestFiles();
		if ( new File(assignmentFile).exists() )
			new File(assignmentFile).delete();
	}

	@Test
	public void testMainStringArrayW1AIniFile() throws Exception {
		String[] args = { IniFileTest.testFilename };
		Main main = new Main(args);
		Assert.assertNotNull(main);
	}

	@Test
	public void testMain_Solo() throws Exception {
		String[] args = { IniFileTest.testFilename };
		Main.main(args);
		IniFile iniFile = new IniFile(IniFileTest.testFilename);
		Assert.assertTrue(new File(iniFile.getDestination()+"/index.html").exists());
	}

//	@Test
//	public void testMain_WatchMode() throws Exception {
//		String[] args = { IniFileTest.testFilename, "-w" };
//		Main.main(args);
//		Thread.sleep(1000);
//		IniFile iniFile = new IniFile(IniFileTest.testFilename);
//		Assert.assertTrue(new File(iniFile.getDestination()+"/index.html").exists());
//	}
	
}
