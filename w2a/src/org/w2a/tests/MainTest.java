package org.w2a.tests;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.w2a.Main;
import org.w2a.W2AIniFile;

public class MainTest extends NgTestSetup {


	@Test
	public void testMain_Solo() throws Exception {
		String[] args = { getTestFilename() };
		Main.main(args);
		W2AIniFile iniFile = new W2AIniFile(getTestFilename());
		Assert.assertTrue(new File(iniFile.getDestination()+"/index.html").exists());
	}

}
