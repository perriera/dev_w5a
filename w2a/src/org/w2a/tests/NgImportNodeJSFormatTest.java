package org.w2a.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w2a.W2AIniFile;
import org.w2a.ngimport.NgImportNodeJSFormat;

public class NgImportNodeJSFormatTest {

	W2AIniFile iniFile;

	@Before
	public void setup() throws Exception {
		this.iniFile = new W2AIniFile("files/formaltests/ngimport/sample.ngimport.test1.ini");
	}

	@Test
	public void testInput() throws Exception {
		 NgImportNodeJSFormat ngi = new  NgImportNodeJSFormat(iniFile);
		 ngi.input();
	}

	@Test
	public void testOutput() throws Exception {
		 NgImportNodeJSFormat ngi = new  NgImportNodeJSFormat(iniFile);
		 
		 Assert.assertTrue(ngi.size()==0);
		 ngi.input();
		 ngi.output();
		 Assert.assertTrue(ngi.size()==4);
		 
	}
	
}
