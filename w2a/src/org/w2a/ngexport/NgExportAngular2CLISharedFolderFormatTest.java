package org.w2a.ngexport;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w2a.W2AIniFile;
import org.w2a.ngimport.NgImportNodeJSFormat;


public class NgExportAngular2CLISharedFolderFormatTest {

	W2AIniFile iniFile;

	@Before
	public void setup() throws Exception {
		this.iniFile = new W2AIniFile("files/formaltests/ngimport/sample.ngimport.test1.ini");
	}

	@Test
	public void testInput() throws Exception {
		
		 NgImportNodeJSFormat ngi = new  NgImportNodeJSFormat(iniFile);
		 NgExportAngular2CLISharedFolderFormat egi = new NgExportAngular2CLISharedFolderFormat(ngi);
		 egi.input();
		 
	}

	@Test
	public void testOutput() throws Exception {
		
		 NgImportNodeJSFormat ngi = new  NgImportNodeJSFormat(iniFile);
		 NgExportAngular2CLISharedFolderFormat egi = new NgExportAngular2CLISharedFolderFormat(ngi);
		 
		 Assert.assertTrue(ngi.size()==0);
		 egi.input();
		 Assert.assertTrue(ngi.size()==0);
		 egi.output();
		 Assert.assertTrue(ngi.size()==4);
		 
	}

}
