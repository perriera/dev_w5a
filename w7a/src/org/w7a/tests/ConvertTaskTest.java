package org.w7a.tests;

import java.io.File;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w5a.requests.ConvertRequest;
import org.w7a.Directory;
import org.w7a.Server;

public class ConvertTaskTest {

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

		ConvertRequest convertRequest = new ConvertRequest("freeformjs.webflow.zip");
		convertRequest.request();

		while (!convertRequest.isSuccessful()) {
			Thread.sleep(100);
		}
		
		File testFile = new File(Directory.getUploadFilename(convertRequest.filename));
		Assert.assertTrue(testFile.exists());
		
	}

}
