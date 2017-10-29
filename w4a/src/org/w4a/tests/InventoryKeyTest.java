package org.w4a.tests;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;
import org.w4a.exceptions.BadInventoryKeyException;
import org.w4a.exceptions.BadRequestKeyException;
import org.w4a.exceptions.NotUploadedException;
import org.w4a.keys.InventoryKey;
import org.w4a.keys.RequestKey;
import org.w4a.keys.UploadedKey;

public class InventoryKeyTest {

	@Test
	public void test0() throws BadInventoryKeyException, BadRequestKeyException {
		String filename = "R-ER45TY123_c-org.webflow.zip";
		RequestKey request = new RequestKey(filename);
		InventoryKey key = new InventoryKey(request);
		Assert.assertTrue(key.getToken().equals("ER45TY123"));
		Assert.assertTrue(key.getKey().equals("c-org.webflow.zip"));
	}

	@Test
	public void test1() throws BadInventoryKeyException, BadRequestKeyException, NotUploadedException {
		String filename = "R-ER45TY123_c-org.webflow.zip";
		UploadedKey uploaded = new UploadedKey(filename);
		RequestKey request = new RequestKey(uploaded);
		InventoryKey key = new InventoryKey(request);
		Assert.assertTrue(key.getToken().equals("ER45TY123"));
		Assert.assertTrue(key.getKey().equals("c-org.webflow.zip"));
	}
	
	@Test(expected = NotUploadedException.class)
	public void test2() throws BadInventoryKeyException, BadRequestKeyException, NotUploadedException {
		String filename = "R-ER45TY123_c-org.webflow.part.zip";
		new UploadedKey(filename);
		fail("exception should have been thrown");
	}
	
	@Test
	public void test3() throws BadInventoryKeyException, BadRequestKeyException {
		String test = InventoryKey.format("ER45TY123", "c-org.webflow.zip");
		Assert.assertTrue(test.equals("ER45TY123_c-org.webflow.zip"));
	}
	
}
