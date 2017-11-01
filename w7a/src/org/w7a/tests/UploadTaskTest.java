package org.w7a.tests;

import java.io.File;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w5a.requests.UploadRequest;
import org.w7a.Directory;
import org.w7a.Server;

public class UploadTaskTest {

	@Before
	public void setUp() throws Exception {
		Directory.create();
	}

	@After
	public void cleanUp() throws Exception {
		Directory.destroy();
	}

	@Test
	public void testResolve() throws Exception {

		new Server();

		UploadRequest uploadRequest = new UploadRequest("freeformjs.webflow.zip");
		uploadRequest.request();

		while (!uploadRequest.isSuccessful()) {
			Thread.sleep(100);
		}
		
		File testFile = new File(Directory.getUploadFilename(uploadRequest.filename));
		Assert.assertTrue(testFile.exists());
		
	}

}
