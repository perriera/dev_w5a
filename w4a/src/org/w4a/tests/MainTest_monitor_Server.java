package org.w4a.tests;

import java.io.File;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w4a.Main;
import org.w4a.W4AIniFile;
import org.w4a.keys.UserRequest;
import org.w4a.server.Server;

public class MainTest_monitor_Server {

	private static String serverMonitorIniFile = "files/testcases/webflow/inifiles/w4a.monitor.ini";
	private static String webflowIniFile = "files/testcases/webflow/inifiles/c-org.webflow.ini";
	private static String webflowFile = "files/testcases/webflow/uploads/c-org.webflow.zip";
	private static String skeletonFile = "files/archives/angular2cli/skeleton.zip";
	private static String skeletonIniFile = "files/inifiles/skeleton.ini";
	private static String zippedFile = "files/archives/zipped/c-org.webflow.src.zip";
	private static String token = "E6B7A2B1";

	private static String _webflowFile = "files/testcases/webflow/uploads/c-org.webflow.zip";
	
	W4AIniFile w4aIniFile; 

	@Before
	public void setUp() throws Exception {
		
		webflowFile = _webflowFile;
		
		this.w4aIniFile = new W4AIniFile(serverMonitorIniFile);
		Server server = new Server(w4aIniFile);
		server.reset();
		server.copyToInventory(new W2AIniFile(webflowIniFile));
		server.copyToResources(new File(skeletonFile));
		server.copyToInventory(new W2AIniFile(skeletonIniFile));
		
	}
	
	@After
	public void cleanUp() throws Exception {
		Server server = new Server(w4aIniFile);
		server.destroy();
	}
	
//	@Test
//	public void test_01_monitor_source() throws Exception {
//		
//		Server server = new Server(w4aIniFile);
//	
//		Assert.assertNotNull(server);
//		Assert.assertTrue(server.uploadsDirExists());
//		Assert.assertTrue(server.existsInInventory(webflowIniFile));
//		Assert.assertTrue(!server.existsInUploads(webflowFile));
//		Assert.assertTrue(!server.existsInUploads(zippedFile));
//		Assert.assertTrue(server.queueEmpty());
//
//		server.monitorSource();
//		
//		long time = new Date().getTime();
//		long test = new Date().getTime();
//		while (server.queueEmpty()) {
//			Thread.sleep(1000);
//			server.uploadAndTagZipFileToServer(webflowFile,token, UserRequest.FORWARD);
//			Thread.sleep(1000);
//			test = new Date().getTime();
//		}
//		
//		Assert.assertTrue(!server.queueEmpty());
//		Assert.assertTrue(test>time);
//		Assert.assertTrue(test<time+3000);
//		
//	}
	
	@Test
	public void test_02_monitor_uploads_directory_with_forward_example() throws Exception {
		
		Server server = new Server(w4aIniFile);
	
		Assert.assertNotNull(server);
		Assert.assertTrue(server.uploadsDirExists());
		Assert.assertTrue(server.existsInInventory(webflowIniFile));
		Assert.assertTrue(!server.existsInUploads(webflowFile));
		Assert.assertTrue(!server.existsInUploads(zippedFile));
		Assert.assertTrue(!server.existsInDownloads(webflowFile));
		Assert.assertTrue(!server.existsInDownloads(zippedFile));
		Assert.assertTrue(server.isUploadsDirectoryEmpty());

//		String[] args = { w4aIniFile.getFilename(), "-w" };
//		Main main = new Main(args);
//		main.monitorSource();

		String taggedWebflowFile = server.uploadAndTagZipFileToServer(webflowFile,token, UserRequest.FORWARD);
		Assert.assertTrue(server.existsInInventory(webflowIniFile));
		
		String[] args = { w4aIniFile.getFilename(), "-w" };
		Main main = new Main(args);
		main.execute();
		
		while (!server.isUploadsDirectoryEmpty()) {
			Thread.sleep(1000);
		}
		
		Assert.assertTrue(!server.existsInUploads(webflowFile));
		Assert.assertTrue(!server.existsInUploads(taggedWebflowFile));
		Assert.assertTrue(!server.existsInUploads(zippedFile));
		Assert.assertTrue(!server.existsInDownloads(webflowFile));
		Assert.assertTrue(!server.existsInDownloads(zippedFile));
		String token = HTMLEdit.getTokenFromFilename(taggedWebflowFile) + ".zip";
		Assert.assertTrue(server.existsInDownloads(token));
		Assert.assertTrue(!server.existsInDownloads(taggedWebflowFile));
		Assert.assertTrue(server.isUploadsDirectoryEmpty());
		
	}
	
	@Test
	public void test_03_monitor_uploads_directory_with_reverse_example() throws Exception {
		
		Server server = new Server(w4aIniFile);
	
		Assert.assertNotNull(server);
		Assert.assertTrue(server.uploadsDirExists());
		Assert.assertTrue(server.existsInInventory(webflowIniFile));
		Assert.assertTrue(!server.existsInUploads(webflowFile));
		Assert.assertTrue(!server.existsInUploads(zippedFile));
		Assert.assertTrue(!server.existsInDownloads(webflowFile));
		Assert.assertTrue(!server.existsInDownloads(zippedFile));
		Assert.assertTrue(server.isUploadsDirectoryEmpty());

//		String[] args = { w4aIniFile.getFilename(), "-w" };
//		Main main = new Main(args);
//		main.monitorSource();

		String taggedWebflowFile = server.uploadAndTagZipFileToServer(webflowFile,token, UserRequest.REVERSE);
		String taggedZippedFile = server.uploadAndTagZipFileToServer(zippedFile,token, UserRequest.REVERSE);
		Assert.assertTrue(server.existsInUploads(taggedWebflowFile));
		Assert.assertTrue(server.existsInUploads(taggedZippedFile));
		
		String[] args = { w4aIniFile.getFilename(), "-w" };
		Main main = new Main(args);
		main.execute();
		
		while (!server.isUploadsDirectoryEmpty()) {
			Thread.sleep(1000);
		}
		
		Assert.assertTrue(!server.existsInUploads(webflowFile));
		Assert.assertTrue(!server.existsInUploads(zippedFile));
		Assert.assertTrue(!server.existsInUploads(taggedWebflowFile));
		Assert.assertTrue(!server.existsInUploads(taggedZippedFile));
		Assert.assertTrue(!server.existsInDownloads(webflowFile));
		Assert.assertTrue(!server.existsInDownloads(zippedFile));
		String token = HTMLEdit.getTokenFromFilename(taggedWebflowFile) + ".zip";
		Assert.assertTrue(server.existsInDownloads(token));
		Assert.assertTrue(!server.existsInDownloads(taggedWebflowFile));
		Assert.assertTrue(!server.existsInDownloads(taggedZippedFile));
		Assert.assertTrue(server.isUploadsDirectoryEmpty());
		
	}
	
}
