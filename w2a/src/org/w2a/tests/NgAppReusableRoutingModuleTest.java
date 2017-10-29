package org.w2a.tests;

import org.junit.Assert;
import org.junit.Test;
import org.w2a.HTMLEdit;
import org.w2a.ngmodule.NgAppReusableRoutingModule;

public class NgAppReusableRoutingModuleTest extends NgAppRoutingModuleTest {

	@Test
	public void test_01_SimpleReFormattingOfDefaultAppRoutingModule() throws Exception {

		basicSetup();
		NgAppReusableRoutingModule ngAppReusableRoutingModule = new NgAppReusableRoutingModule(appModule, iniFile);
		
		String before = beforeAppRoutingReverse(0);
		before = ngAppReusableRoutingModule.readFile(before);
		
		String after = ngAppReusableRoutingModule.output();
		String test = HTMLEdit.loadLocalFile(afterAppRoutingReverse(0));
		
		Assert.assertTrue(before.length() > 0);
		Assert.assertTrue(after.length() > 0);
		Assert.assertTrue(test.length() > 0);
		Assert.assertTrue(test.equals(before));
		Assert.assertTrue(test.equals(after));

	}
	
	@Test
	public void test_02_SimpleReFormattingAppRoutingModuleWithHomeComponent() throws Exception {

		setup(1,1,0);
		NgAppReusableRoutingModule ngAppReusableRoutingModule = new NgAppReusableRoutingModule(appModule, iniFile);
		
		String before = beforeAppRoutingReverse(1);
		before = ngAppReusableRoutingModule.readFile(before);
		
		String after = ngAppReusableRoutingModule.output();
		String test = HTMLEdit.loadLocalFile(afterAppRoutingReverse(1));
		
		Assert.assertTrue(before.length() > 0);
		Assert.assertTrue(after.length() > 0);
		Assert.assertTrue(test.length() > 0);
		Assert.assertTrue(test.equals(before));
		Assert.assertTrue(test.equals(after));

	}
	
	@Test
	public void test_03_SimpleReFormattingAppRoutingModuleAuthServiceImport() throws Exception {

		setup(2,2,0);
		NgAppReusableRoutingModule ngAppReusableRoutingModule = new NgAppReusableRoutingModule(appModule, iniFile);
		
		String before = beforeAppRoutingReverse(2);
		before = ngAppReusableRoutingModule.readFile(before);
		
		String after = ngAppReusableRoutingModule.output();
		String test = HTMLEdit.loadLocalFile(afterAppRoutingReverse(2));
		
		Assert.assertTrue(before.length() > 0);
		Assert.assertTrue(after.length() > 0);
		Assert.assertTrue(test.length() > 0);
		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(test.equals(after));

	}
	
	@Test
	public void test_04_PreservingSysImport() throws Exception {

		setup(2,2,0);
		NgAppReusableRoutingModule ngAppReusableRoutingModule = new NgAppReusableRoutingModule(appModule, iniFile);
		
		String before = beforeAppRoutingReverse(3);
		before = ngAppReusableRoutingModule.readFile(before);
		
		String after = ngAppReusableRoutingModule.output();
		String test = HTMLEdit.loadLocalFile(afterAppRoutingReverse(3));
		
		Assert.assertTrue(before.length() > 0);
		Assert.assertTrue(after.length() > 0);
		Assert.assertTrue(test.length() > 0);
		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(test.equals(after));

	}
	
	@Test
	public void test_05_PreservingAppImport() throws Exception {

		setup(2,2,0);
		NgAppReusableRoutingModule ngAppReusableRoutingModule = new NgAppReusableRoutingModule(appModule, iniFile);
		
		String before = beforeAppRoutingReverse(4);
		before = ngAppReusableRoutingModule.readFile(before);
		
		String after = ngAppReusableRoutingModule.output();
		String test = HTMLEdit.loadLocalFile(afterAppRoutingReverse(4));
		
		Assert.assertTrue(before.length() > 0);
		Assert.assertTrue(after.length() > 0);
		Assert.assertTrue(test.length() > 0);
		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(test.equals(after));

	}
	
	@Test
	public void test_06_PreservingNgModule() throws Exception {

		setup(2,2,0);
		NgAppReusableRoutingModule ngAppReusableRoutingModule = new NgAppReusableRoutingModule(appModule, iniFile);
		
		String before = beforeAppRoutingReverse(5);
		before = ngAppReusableRoutingModule.readFile(before);
		
		String after = ngAppReusableRoutingModule.output();
		String test = HTMLEdit.loadLocalFile(afterAppRoutingReverse(5));
		
		Assert.assertTrue(before.length() > 0);
		Assert.assertTrue(after.length() > 0);
		Assert.assertTrue(test.length() > 0);
		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(test.equals(after));

	}
	
	@Test
	public void test_07_PreservingSysImportWithNewHomeComponentAdded() throws Exception {

		setup(1,1,0);
		NgAppReusableRoutingModule ngAppReusableRoutingModule = new NgAppReusableRoutingModule(appModule, iniFile);
		
		String before = beforeAppRoutingReverse(6);
		before = ngAppReusableRoutingModule.readFile(before);
		
		String after = ngAppReusableRoutingModule.output();
		String test = HTMLEdit.loadLocalFile(afterAppRoutingReverse(6));
		
		Assert.assertTrue(before.length() > 0);
		Assert.assertTrue(after.length() > 0);
		Assert.assertTrue(test.length() > 0);
		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(test.equals(after));

	}
	
	@Test
	public void test_07_PreservingAppImportAndNgModuleWithNewHomeComponentAdded() throws Exception {

		setup(1,1,0);
		NgAppReusableRoutingModule ngAppReusableRoutingModule = new NgAppReusableRoutingModule(appModule, iniFile);
		
		String before = beforeAppRoutingReverse(7);
		before = ngAppReusableRoutingModule.readFile(before);
		
		String after = ngAppReusableRoutingModule.output();
		String test = HTMLEdit.loadLocalFile(afterAppRoutingReverse(7));
		
		Assert.assertTrue(before.length() > 0);
		Assert.assertTrue(after.length() > 0);
		Assert.assertTrue(test.length() > 0);
		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(test.equals(after));

	}
	
	@Test
	public void test_08_PreservingAppRoutesWithNewHomeComponentAdded() throws Exception {

		setup(1,1,0);
		NgAppReusableRoutingModule ngAppReusableRoutingModule = new NgAppReusableRoutingModule(appModule, iniFile);
		
		String before = beforeAppRoutingReverse(8);
		before = ngAppReusableRoutingModule.readFile(before);
		
		String after = ngAppReusableRoutingModule.output();
		String test = HTMLEdit.loadLocalFile(afterAppRoutingReverse(8));
		
		Assert.assertTrue(before.length() > 0);
		Assert.assertTrue(after.length() > 0);
		Assert.assertTrue(test.length() > 0);
		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(test.equals(after));

	}
	
	
	@Test
	public void test_10_PreservingAppRoutesWhileAddingHomeComponentMoreComplexExample() throws Exception {

		setup(1,1,0);
		NgAppReusableRoutingModule ngAppReusableRoutingModule = new NgAppReusableRoutingModule(appModule, iniFile);
		
		String before = beforeAppRoutingReverse(10);
		before = ngAppReusableRoutingModule.readFile(before);
		
		String after = ngAppReusableRoutingModule.output();
		String test = HTMLEdit.loadLocalFile(afterAppRoutingReverse(10));
		
		Assert.assertTrue(before.length() > 0);
		Assert.assertTrue(after.length() > 0);
		Assert.assertTrue(test.length() > 0);
		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(test.equals(after));

	}
	
	
	@Test
	public void test_11_PreservingAppRoutesWhileAddingNewComponentMoreComplexExample() throws Exception {

		setup(5,5,1);
		NgAppReusableRoutingModule ngAppReusableRoutingModule = new NgAppReusableRoutingModule(appModule, iniFile);
		
		String before = beforeAppRoutingReverse(11).trim();
		before = ngAppReusableRoutingModule.readFile(before);
		
		String after = ngAppReusableRoutingModule.output().trim();
		String test = HTMLEdit.loadLocalFile(afterAppRoutingReverse(11)).trim();
		
		Assert.assertTrue(before.length() > 0);
		Assert.assertTrue(after.length() > 0);
		Assert.assertTrue(test.length() > 0);
		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(test.equals(after));

	}
	
}
