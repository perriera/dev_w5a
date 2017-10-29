package org.w2a.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.ngtypes.NgHTMLFile;


public class CheckForDuplicateIdsTest {

	W2AIniFile iniFile;

	@Before
	public void setup() throws Exception {
		this.iniFile = new W2AIniFile("files/formaltests/sample.00.ini");
	}

	@Test
	public void test1() throws Exception {
		
		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.12.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.12.after.html");

		String test = NgHTMLFile.checkForDuplicateIds(before, "views/uploads.html", iniFile);

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		Assert.assertTrue(test.trim().equals(after.trim()));
		
	}
	
}
