package org.w4a.tests;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.w2a.W2AIniFile;
import org.w4a.W4AIniFile;
import org.w4a.server.Server;

public class ServerTest_BackwardsUploadRequest {

	private static String serverMonitorIniFile = "files/testcases/webflow/inifiles/w4a.monitor.ini";
	private static String webflowIniFile = "files/testcases/webflow/inifiles/c-org.webflow.ini";
	private static String webflowFile = "files/testcases/webflow/uploads/c-org.webflow.zip";
	private static String skeletonFile = "files/archives/angular2cli/skeleton.zip";
	private static String skeletonIniFile = "files/inifiles/skeleton.ini";
	private static String zippedFile = "files/archives/zipped/c-org.webflow.src.zip";

	W4AIniFile w4aIniFile; 

	@Before
	public void setUp() throws Exception {
		
		this.w4aIniFile = new W4AIniFile(serverMonitorIniFile);
		Server server = new Server(w4aIniFile);
		server.reset();
		server.copyToInventory(new W2AIniFile(webflowIniFile));
		server.copyToResources(new File(skeletonFile));
		server.copyToResources(new File(zippedFile));
		server.copyToResources(new File(webflowFile));
		server.copyToInventory(new W2AIniFile(skeletonIniFile));
		
	}

	@After
	public void cleanUp() throws Exception {
		
		Server server = new Server(new W4AIniFile(serverMonitorIniFile));
		server.destroy();
		
	}

//	@Test
//	public void test_execute_process_zipped_archive() throws Exception {
//		
//		Server server = new Server(w4aIniFile);
//		server.copyToInventory(new W2AIniFile(webflowIniFile));
//		Assert.assertTrue(server.existsInInventory(webflowIniFile));
//		
//		String taggedWebflowFile = server.uploadAndTagZipFileToServer(webflowFile, token, UserRequest.REVERSE);
//		String taggedZippedFile = server.uploadAndTagZipFileToServer(zippedFile, token, UserRequest.REVERSE);
//		Assert.assertTrue(server.existsInUploads(taggedWebflowFile));
//		Assert.assertTrue(server.existsInUploads(taggedZippedFile));
//		Assert.assertTrue(!server.existsInDownloads(taggedWebflowFile));
//		
//		server.execute_process_zipped_archive(new File(taggedWebflowFile), new File(taggedZippedFile));
//		Assert.assertTrue(server.existsInDownloads(taggedWebflowFile));
//		Assert.assertTrue(!server.existsInUploads(taggedWebflowFile));
//		Assert.assertTrue(!server.existsInUploads(taggedZippedFile));
//		
//	}
	
}
