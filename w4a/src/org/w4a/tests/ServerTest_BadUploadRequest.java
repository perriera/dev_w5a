package org.w4a.tests;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w2a.W2AIniFile;
import org.w4a.W4AIniFile;
import org.w4a.server.Server;

public class ServerTest_BadUploadRequest {

	private static String serverMonitorIniFile = "files/testcases/webflow/inifiles/w4a.monitor.ini";
	private static String webflowIniFile = "files/testcases/webflow/inifiles/c-org.webflow.ini";
//	private static String webflowFile = "files/testcases/webflow/uploads/c-org.webflow.zip";
	private static String skeletonFile = "files/archives/angular2cli/skeleton.zip";
	private static String skeletonIniFile = "files/inifiles/skeleton.ini";
	private static String zippedFile = "files/archives/zipped/c-org.webflow.src.zip";

	//private static String _webflowFile = "files/testcases/webflow/uploads/c-org.webflow.zip";
	//private static String _zippedFile = "files/archives/zipped/c-org.webflow.src.zip";
	
	W4AIniFile w4aIniFile; 

	@Before
	public void setUp() throws Exception {
		
		//ServerTest_BadUploadRequest.webflowFile = ServerTest_BadUploadRequest._webflowFile; 
	//	ServerTest_BadUploadRequest.zippedFile = ServerTest_BadUploadRequest._zippedFile; 
		
		this.w4aIniFile = new W4AIniFile(serverMonitorIniFile);
		Server server = new Server(w4aIniFile);
		server.reset();
		server.copyToInventory(new W2AIniFile(webflowIniFile));
		server.copyToResources(new File(skeletonFile));
		server.copyToResources(new File(zippedFile));
		server.copyToInventory(new W2AIniFile(skeletonIniFile));
		
	}

	@After
	public void cleanUp() throws Exception {
		
		Server server = new Server(new W4AIniFile(serverMonitorIniFile));
		server.destroy();
		
	}

	@Test
	public void test_02_monitor_uploads_directory_with_forward_example() throws Exception {
		
//		Server server = new Server(w4aIniFile);
//	
//		Assert.assertNotNull(server);
//		Assert.assertTrue(server.uploadsDirExists());
//		Assert.assertTrue(server.existsInInventory(webflowIniFile));
//		Assert.assertTrue(!server.existsInUploads(webflowFile));
//		Assert.assertTrue(!server.existsInUploads(zippedFile));
//		Assert.assertTrue(!server.existsInDownloads(webflowFile));
//		Assert.assertTrue(!server.existsInDownloads(zippedFile));
//		Assert.assertTrue(server.isUploadsDirectoryEmpty());
//
//		String[] args = { w4aIniFile.getFilename(), "-w" };
//		Main main = new Main(args);
//		main.process();
//
//		server.uploadZipFileToServer(webflowFile);
//		Assert.assertTrue(server.existsInUploads(webflowFile));
//		
//		while (!server.isUploadsDirectoryEmpty()) {
//			Thread.sleep(1000);
//		}
//		
//		Assert.assertTrue(!server.existsInUploads(webflowFile));
//		Assert.assertTrue(!server.existsInUploads(zippedFile));
//		Assert.assertTrue(!server.existsInDownloads(webflowFile));
//		Assert.assertTrue(!server.existsInDownloads(zippedFile));
//		Assert.assertTrue(server.isUploadsDirectoryEmpty());
		
	}
		
	

	
}
