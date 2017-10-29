package org.w2a.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.ngcomponent.NgComponentExtractor;


public class RemoveWebFlowItemsTest {

	W2AIniFile iniFile;

	@Before
	public void setup() throws Exception {
		this.iniFile = new W2AIniFile("files/formaltests/sample.00.ini");
	}

	@Test
	public void test1() throws Exception {
		
		String html = HTMLEdit.loadLocalFile("files/formaltests/webflowitems/remove.test1.html");
		
		Assert.assertTrue(html.contains("class=\"w-form-done\""));
		Assert.assertTrue(html.contains("class=\"w-form-fail\""));
		
		html = NgComponentExtractor.removeWebFlowItems(html, this.iniFile);
		
		Assert.assertTrue(!html.contains("class=\"w-form-done\""));
		Assert.assertTrue(!html.contains("class=\"w-form-fail\""));
		
	}

	@Test
	public void test2() throws Exception {
		
		String html = HTMLEdit.loadLocalFile("files/formaltests/webflowitems/remove.test2.html");
		
		Assert.assertTrue(html.contains("jquery.min.js"));
		Assert.assertTrue(html.contains("js/webflow.js"));
		Assert.assertTrue(html.contains("[if lte IE 9]"));
		
		html = NgComponentExtractor.removeWebFlowItems(html, this.iniFile);
		
		Assert.assertTrue(!html.contains("jquery.min.js"));
		Assert.assertTrue(!html.contains("js/webflow.js"));
		Assert.assertTrue(!html.contains("[if lte IE 9]s"));
		
	}
	
}
