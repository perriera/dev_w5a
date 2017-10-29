package org.w2a.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.ngroute.NgAppRoutes;


public class AdjustRouterLinksWithParametersTest {

	W2AIniFile iniFile;

	@Before
	public void setup() throws Exception {
		this.iniFile = new W2AIniFile("files/formaltests/sample.00.ini");
	}

	@Test
	public void test1() throws Exception {
		
		String html = HTMLEdit.loadLocalFile("files/formaltests/webflowitems/replace.test1.html");
		
		Assert.assertTrue(html.contains("routerLink=\"/articles_review_an_article\""));
		Assert.assertTrue(html.contains("ng-parameter1=\"{{article._id}}\""));
		
		html = NgAppRoutes.adjustRouterLinksWithParameters(html, this.iniFile);
		
		Assert.assertTrue(html.contains("routerLink=\"/articles_review_an_article/{{article._id}}\""));
		Assert.assertTrue(!html.contains("ng-parameter1=\"{{article._id}}\""));

		
	}

	@Test
	public void test2() throws Exception {
		
		String html = HTMLEdit.loadLocalFile("files/formaltests/webflowitems/replace.test2.html");
		
		Assert.assertTrue(html.contains("routerLink=\"/articles_review_an_article/{{article._id}}\""));
		Assert.assertTrue(html.contains("ng-parameter1=\"{{article._id}}\""));
		
		html = NgAppRoutes.adjustRouterLinksWithParameters(html, this.iniFile);
		
		Assert.assertTrue(html.contains("routerLink=\"/articles_review_an_article/{{article._id}}\""));
		Assert.assertTrue(!html.contains("ng-parameter1=\"{{article._id}}\""));

		
	}


	
}
