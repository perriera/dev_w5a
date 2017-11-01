package org.w7a.tests;

import org.junit.After;
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
		
		String test = "freeformjs.webflow.zip";
		
		new Server();
		
//		UploadRequest uploadRequest = new UploadRequest(test);
//		uploadRequest.request();
//		
//		File testFile = new File(Directory.getUploadFilename(uploadRequest.filename));
//		while (!testFile.exists()&&!uploadRequest.isCompletedSuccessfully()) {
//			Thread.sleep(100);
//		}
		
		ConvertRequest convertRequest = new ConvertRequest(test);
		convertRequest.request();

//		while (!convertRequest.isCompletedSuccessfully()) {
//			Thread.sleep(100);
//		}
		
	}

}
