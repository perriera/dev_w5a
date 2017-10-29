package org.w2a.tests;

import org.junit.Assert;
import org.junit.Test;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.ngmodule.NgAppModule;
import org.w2a.ngmodule.NgAppRoutingModule;

public class NgAppRoutingModuleTest extends NgAppModuleTest {

	W2AIniFile iniFile;
	NgAppModule appModule;

	void setup(int appModuleBeforeFileNo, int appModuleAfterFileNo, int iniFilenameNo) throws Exception {

		this.iniFile = new W2AIniFile(formattedIniFile(iniFilenameNo,"files/formaltests/"));
		this.appModule = new NgAppModule(iniFile);

		String before = appModule.readFile(beforeApp(appModuleBeforeFileNo)).trim();
		String after = appModule.output().trim();

		String test = HTMLEdit.loadLocalFile(afterApp(appModuleAfterFileNo)).trim();
		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(test.equals(after));

	}
	
	void basicSetup() throws Exception {
		setup(0,0,0);
	}

	@Test
	public void test_01_SimpleFormattingOfDefaultAppRoutingModule() throws Exception {

		basicSetup();
		NgAppRoutingModule appRoutingModule = new NgAppRoutingModule(appModule, iniFile);

		String after = appRoutingModule.output();
		String test = HTMLEdit.loadLocalFile(afterAppRoutingForward(0));
		Assert.assertTrue(after.length() > 0);
		Assert.assertTrue(test.length() > 0);
		Assert.assertTrue(test.equals(after));

	}

	@Test
	public void test_02_TestAddHomeComponentImport() throws Exception {

		setup(1,1,0);
		NgAppRoutingModule appRoutingModule = new NgAppRoutingModule(appModule, iniFile);

		String after = appRoutingModule.output();
		String test = HTMLEdit.loadLocalFile(afterAppRoutingForward(1));
		Assert.assertTrue(after.length() > 0);
		Assert.assertTrue(test.length() > 0);
		Assert.assertTrue(test.equals(after));

	}

	@Test
	public void test_03_TestAddAuthServiceImport() throws Exception {

		setup(2,2,0);
		NgAppRoutingModule appRoutingModule = new NgAppRoutingModule(appModule, iniFile);

		String after = appRoutingModule.output();
		String test = HTMLEdit.loadLocalFile(afterAppRoutingForward(2));
		Assert.assertTrue(after.length() > 0);
		Assert.assertTrue(test.length() > 0);
		Assert.assertTrue(test.equals(after));

	}

}
