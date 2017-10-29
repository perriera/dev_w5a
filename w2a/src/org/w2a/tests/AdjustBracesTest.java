package org.w2a.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.ngcomponent.NgComponentExtractor;
import org.w2a.prettypepper.NgPrettyPepper;


public class AdjustBracesTest {

	W2AIniFile iniFile;

	@Before
	public void setup() throws Exception {
		this.iniFile = new W2AIniFile("files/formaltests/sample.00.ini");
	}

	String stripped(String before) {
		String after1 = before.replace("\n", "");
		String after2 = after1.replace("\t", "");
		String after3 = after2.replace(" ", "");
		return after3;
	}
	
	@Test
	public void test1() throws Exception {
		
		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.09.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.09.after.html");

		String test = NgComponentExtractor.adjustCulyBraces(before);

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		Assert.assertTrue(test.equals(after));
		
	}
	
	@Test
	public void test2() throws Exception {
		
		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.10.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.10.after.html");

		String test = NgComponentExtractor.adjustCulyBraces(before);

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		Assert.assertTrue(test.equals(after));
		
	}
	
	@Test
	public void test3() throws Exception {
		
		String before = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.11.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/outlinesource/quotetest.11.after.html");
		
		NgPrettyPepper pp = new NgPrettyPepper(before, iniFile);
		String test = pp.output();

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		Assert.assertTrue(test.trim().equals(after.trim()));

		
	}
}
