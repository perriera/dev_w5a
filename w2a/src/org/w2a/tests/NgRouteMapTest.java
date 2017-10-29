package org.w2a.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.ngroute.NgRoute;


public class NgRouteMapTest {

	W2AIniFile iniFile;

	@Before
	public void setup() throws Exception {
		this.iniFile = new W2AIniFile("files/formaltests/sample.00.ini");
	}

	@Test
	public void test1() throws Exception {
		
//		String before = HTMLEdit.loadLocalFile("files/formaltests/ngroute/quotetest.00.before.html");
//		String after = HTMLEdit.loadLocalFile("files/formaltests/ngroute/quotetest.00.after.html");
//
//		NgRouteMap routeMap = new NgRouteMap(before,this.iniFile);
//		String test = routeMap.output();
//
//		Assert.assertTrue(!test.equals(before));
//		Assert.assertTrue(!before.equals(after));
//		Assert.assertTrue(test.trim().equals(after.trim()));
		
	}
	
	@Test
	public void test2() throws Exception {
		
		String before = HTMLEdit.loadLocalFile("files/formaltests/ngroute/quotetest.00.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/ngroute/quotetest.00.after.html");

		String test = NgRoute.moveToBottomOfList("404",before,this.iniFile);
		test = NgRoute.moveToBottomOfList("**",test,this.iniFile);

		Assert.assertTrue(!test.equals(before));
		Assert.assertTrue(!before.equals(after));
		Assert.assertTrue(test.trim().equals(after.trim()));
		
	}
	
	@Test
	public void test3() throws Exception {
		
		String before = HTMLEdit.loadLocalFile("files/formaltests/ngroute/quotetest.01.before.html");
		String after = HTMLEdit.loadLocalFile("files/formaltests/ngroute/quotetest.01.after.html");

		String test = NgRoute.moveToBottomOfList("404",before,this.iniFile);
		test = NgRoute.moveToBottomOfList("**",test,this.iniFile);

		Assert.assertTrue(test.equals(before));
		Assert.assertTrue(before.equals(after));
		Assert.assertTrue(test.trim().equals(after.trim()));
		
	}
}
