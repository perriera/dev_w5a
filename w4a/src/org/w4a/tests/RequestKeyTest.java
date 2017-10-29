package org.w4a.tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.w4a.exceptions.BadInventoryKeyException;
import org.w4a.exceptions.BadRequestKeyException;
import org.w4a.keys.RequestKey;
import org.w4a.keys.UserRequest;

public class RequestKeyTest {

	@Test
	public void test0() throws BadRequestKeyException {
		String filename = "R-ER45TY123_c-org.webflow.zip";
		RequestKey key = new RequestKey(filename);
		Assert.assertTrue(key.getRequest() == UserRequest.REVERSE);
	}
	
	@Test
	public void test1() throws BadRequestKeyException {
		String filename = "F-ER45TY123_c-org.webflow.zip";
		RequestKey key = new RequestKey(filename);
		Assert.assertTrue(key.getRequest() == UserRequest.FORWARD);
	}
	
	@Test(expected = BadRequestKeyException.class)
	public void test2() throws BadRequestKeyException {
		String filename = "R";
		new RequestKey(filename);
		fail("exception not triggered");
	}

	@Test(expected = BadRequestKeyException.class)
	public void test3() throws BadRequestKeyException {
		String filename = "R-";
		new RequestKey(filename);
		fail("exception not triggered");
	}
	
	@Test
	public void test4() throws BadRequestKeyException {
		String filename = "F-X";
		RequestKey key = new RequestKey(filename);
		Assert.assertTrue(key.getRequest() == UserRequest.FORWARD);
	}
	
	@Test
	public void test5() throws BadRequestKeyException {
		String filename = "F-X.zip.part1";
		RequestKey key = new RequestKey(filename);
		Assert.assertTrue(key.getRequest() == UserRequest.FORWARD);
	}
	
	@Test
	public void test6() throws BadInventoryKeyException, BadRequestKeyException {
		String test = RequestKey.format("ER45TY123_c-org.webflow.zip", UserRequest.FORWARD);
		Assert.assertTrue(test.equals("F-ER45TY123_c-org.webflow.zip"));
	}
	
	@Test
	public void test7() throws BadInventoryKeyException, BadRequestKeyException {
		String test = RequestKey.format("ER45TY123_c-org.webflow.zip", UserRequest.REVERSE);
		Assert.assertTrue(test.equals("R-ER45TY123_c-org.webflow.zip"));
	}
	
	
}
