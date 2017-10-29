package org.w4a.tests;

import org.junit.Assert;
import org.junit.Test;
import org.w4a.exceptions.BadInventoryKeyException;
import org.w4a.exceptions.BadRequestKeyException;
import org.w4a.exceptions.NotUploadedException;
import org.w4a.keys.InventoryKey;
import org.w4a.keys.InventoryRequestKey;
import org.w4a.keys.RequestKey;
import org.w4a.keys.UploadedKey;
import org.w4a.keys.UserRequest;

public class InventoryRequestKeyTest {

	
	@Test
	public void test1() throws BadInventoryKeyException, BadRequestKeyException, NotUploadedException {
		String filename = "R-ER45TY123_c-org.webflow.zip";
		UploadedKey uploaded = new UploadedKey(filename);
		RequestKey request = new RequestKey(uploaded);
		InventoryKey key = new InventoryKey(request);
		Assert.assertTrue(key.getToken().equals("ER45TY123"));
		Assert.assertTrue(key.getKey().equals("c-org.webflow.zip"));
	}
	
	@Test
	public void test2() throws BadInventoryKeyException, BadRequestKeyException {
		String test = InventoryRequestKey.format(UserRequest.REVERSE, "ER45TY123","c-org.webflow.zip");
		Assert.assertTrue(test.equals("R-ER45TY123_c-org.webflow.zip"));
	}
	

}
