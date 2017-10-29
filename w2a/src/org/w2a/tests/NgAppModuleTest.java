package org.w2a.tests;

import org.junit.Assert;
import org.junit.Test;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.ngmodule.NgAppModule;

public class NgAppModuleTest {
	
	W2AIniFile iniFile;
	NgAppModule appModule;

	void setup() throws Exception {

		this.iniFile = new W2AIniFile("files/formaltests/sample.00.ini");
		this.appModule = new NgAppModule(iniFile);

	} 
	
	String formattedIniFile(int number, String dir) {
		String formattedNumnber = ("00"+(""+number)).substring((""+number).length());
		String filename = dir+"sample."+formattedNumnber+".ini";
		return filename;
	}
	
	String formatted(int number, String word, String type, String dir) {
		String formattedNumnber = ("00"+(""+number)).substring((""+number).length());
		String filename = dir+type+".module.test."+formattedNumnber+"."+word+".ts";
		return filename;
	}
	
	String before(int number, String type, String dir) {
		return formatted(number,"before",type,dir);
	}
	
	String after(int number, String type, String dir) {
		return formatted(number,"after",type,dir);
	}
	
	String beforeApp(int number) {
		String dir = "files/formaltests/app.module.tests/";
		return formatted(number,"before","app",dir);
	}
	
	String afterApp(int number) {
		String dir = "files/formaltests/app.module.tests/";
		return formatted(number,"after","app",dir);
	}

	String beforeAppRouting(int number, String where) {
		String dir = "files/formaltests/app-routing.module.tests/"+where;
		return formatted(number,"before","app-routing",dir);
	}
	
	String afterAppRouting(int number, String where) {
		String dir = "files/formaltests/app-routing.module.tests/"+where;
		return formatted(number,"after","app-routing",dir);
	}
	
	String beforeAppRoutingForward(int number) {
		return beforeAppRouting(number,"forward/");
	}
	
	String afterAppRoutingForward(int number) {
		return afterAppRouting(number,"forward/");
	}
	
	String beforeAppRoutingReverse(int number) {
		return beforeAppRouting(number,"reverse/");
	}
	
	String afterAppRoutingReverse(int number) {
		return afterAppRouting(number,"reverse/");
	}
	
	@Test
	public void test_01_SimpleReformatOfDefaultAppModule() throws Exception {
		 	
		setup();
		
		String before = appModule.readFile(beforeApp(0));
		String after = appModule.output();
		
		String test = HTMLEdit.loadLocalFile(afterApp(0));
		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(test.equals(after));
		
	}

	@Test
	public void test_02_PreserveAppImportUserEdit() throws Exception {
		 	
		setup();
		
		String before = appModule.readFile(beforeApp(1));
		String after = appModule.output();
		
		String test = HTMLEdit.loadLocalFile(afterApp(1));
		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(test.equals(after));
		
	}
	
	@Test
	public void test_02_PreserveNgModuleUserEdit() throws Exception {
		 
		setup();
		
		String before = appModule.readFile(beforeApp(2));
		String after = appModule.output();
		
		String test = HTMLEdit.loadLocalFile(afterApp(2));
		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(test.equals(after));
		
	}

	@Test
	public void test_03_PreserveSysImportUserEdit() throws Exception {
		 
		setup();
		
		String before = appModule.readFile(beforeApp(3));
		String after = appModule.output();
		
		String test = HTMLEdit.loadLocalFile(afterApp(3));
		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(test.equals(after));
		
	}

	@Test
	public void test_04_PreserveSymbols() throws Exception {
		 
		setup();
		
		String before = appModule.readFile(beforeApp(4));
		String after = appModule.output();
		
		String test = HTMLEdit.loadLocalFile(afterApp(4));
		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(test.equals(after));
		
	}
}
