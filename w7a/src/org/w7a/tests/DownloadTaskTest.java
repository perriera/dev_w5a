package org.w7a.tests;

import java.io.File;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w5a.requests.DownloadRequest;
import org.w5a.requests.UploadRequest;
import org.w7a.Directory;
import org.w7a.Server;

public class DownloadTaskTest {
	
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
		
		String test = "freeformjs.webflow.zip";
		
		new Server();
		
		UploadRequest uploadRequest = new UploadRequest(test);
		uploadRequest.request();
		
		while (!uploadRequest.isSuccessful()) {
			Thread.sleep(100);
		}
		
		DownloadRequest downloadRequest = new DownloadRequest(test);
		downloadRequest.request();

		while (!downloadRequest.isSuccessful()) {
			Thread.sleep(100);
		}
		
		File testFile = new File(Directory.getTestDirFilename(uploadRequest.filename));
		Assert.assertTrue(testFile.exists());
		
	}

}
