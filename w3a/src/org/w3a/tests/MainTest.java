package org.w3a.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3a.Main;
import org.w3a.W3AIniFile;

public class MainTest {

	String monitorIniFile = "files/formaltests/freeformjs.monitor.ini";
	W3AIniFile iniFile; 

	@Before
	public void setUp() throws Exception {
		this.iniFile = new W3AIniFile(monitorIniFile);
	}

	@Test
	public void test_construct() {
		String[] args = { monitorIniFile, "-w" };
		Main.main(args);
	}

	@Test
	public void test_file_forward() throws Exception {
		String[] args = { monitorIniFile, "-w" };
		Main.prepareForward("c-org.webflow",iniFile,"files/formaltests",true,"");
		Main.main(args);
		while(!Main.projectProcessed(iniFile,"c-org.webflow")) {
			Thread.sleep(1000);
		}
	}
	
	@Test
	public void test_file_reverse() throws Exception {
		String[] args = { monitorIniFile, "-w" };
		Main.prepareReverse("c-org.webflow",iniFile,"files/formaltests",true,"c-org.webflow.src");
		Main.main(args);
		while(!Main.projectProcessed(iniFile,"c-org.webflow")) {
			Thread.sleep(1000);
		}
	}
	
	@Test
	public void test_file_reverse_IniFileMissing() throws Exception {
		String[] args = { monitorIniFile, "-w" };
		Main.prepareReverseInError("c-org.webflow",iniFile,"files/formaltests",false,"src");
		Main main = new Main(args);
		Assert.assertTrue(!Main.projectProcessedInError(iniFile));
		main.execute();
		Assert.assertTrue(Main.projectProcessedInError(iniFile));
	}
}
