package org.w4a.tests;

import java.io.File;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w4a.W4AIniFile;
import org.w4a.keys.UserRequest;
import org.w4a.requests.ForwardUploadRequest;
import org.w4a.server.Server;
import org.w4a.tasks.PrepareIniFileForUse;

public class ServerTest_ForwardRequest2 {

	private static String serverMonitorIniFile = "files/testcases/webflow/inifiles/w4a.monitor.ini";
	private static String webflowIniFile = "files/testcases/webflow/inifiles/c-org.webflow.ini";
	private static String webflowFile = "files/testcases/webflow/uploads/freeformjs.webflow.zip";
	private static String skeletonFile = "files/archives/angular2cli/skeleton.zip";
	private static String skeletonIniFile = "files/inifiles/skeleton.ini";
	private static String token = "E6B7A2B1";

	W4AIniFile w4aIniFile; 

	@Before
	public void setUp() throws Exception {
		
		this.w4aIniFile = new W4AIniFile(serverMonitorIniFile);
		Server server = new Server(w4aIniFile);
		server.reset();
		server.copyToInventory(new W2AIniFile(webflowIniFile));
		server.copyToResources(new File(skeletonFile));
		server.copyToInventory(new W2AIniFile(skeletonIniFile));
		
	}

	@After
	public void cleanUp() throws Exception {
		
		Server server = new Server(new W4AIniFile(serverMonitorIniFile));
		server.destroy();
		
	}

	@Test
	public void test_execute_process_webflow_archive_with() throws Exception {
		
		Server server = new Server(w4aIniFile);
		server.copyToInventory(new W2AIniFile(webflowIniFile));
		Assert.assertTrue(server.existsInInventory(webflowIniFile)); 
		
		String taggedWebflowFile = server.uploadAndTagZipFileToServer(webflowFile, token, UserRequest.FORWARD);
		Assert.assertTrue(server.existsInUploads(taggedWebflowFile));
		Assert.assertTrue(!server.existsInDownloads(taggedWebflowFile));
		
		String taggedWebflowIniFile = server.uploadAndTagZipFileToServer(webflowIniFile, token, UserRequest.FORWARD);
		Assert.assertTrue(server.existsInUploads(taggedWebflowIniFile));
		Assert.assertTrue(!server.existsInDownloads(taggedWebflowIniFile));
		
		ForwardUploadRequest request = new ForwardUploadRequest(new File(taggedWebflowFile));
		request.setIniFile(new File(taggedWebflowIniFile));
		PrepareIniFileForUse prepare = new PrepareIniFileForUse(server,request);
		request.doTask(server, prepare.getW2AIniFile(), server.getXIniFile());
		
		String token = HTMLEdit.getTokenFromFilename(taggedWebflowFile) + ".zip";
		Assert.assertTrue(server.existsInDownloads(token));
		Assert.assertTrue(!server.existsInDownloads(taggedWebflowFile));
		Assert.assertTrue(!server.existsInUploads(taggedWebflowFile));
		
	}
	
//	@Test
//	public void test_execute_process_webflow_archive_with_no_iniFile() throws Exception {
//		
//		Server server = new Server(w4aIniFile);
//		server.copyToInventory(new W2AIniFile(webflowIniFile));
//		Assert.assertTrue(server.existsInInventory(webflowIniFile)); 
//		
//		String taggedWebflowFile = server.uploadAndTagZipFileToServer(webflowFile, token, UserRequest.FORWARD);
//		Assert.assertTrue(server.existsInUploads(taggedWebflowFile));
//		Assert.assertTrue(!server.existsInDownloads(taggedWebflowFile));
//		
//		String taggedWebflowIniFile = server.uploadAndTagZipFileToServer(webflowIniFile, token, UserRequest.FORWARD);
//		Assert.assertTrue(server.existsInUploads(taggedWebflowIniFile));
//		Assert.assertTrue(!server.existsInDownloads(taggedWebflowIniFile));
//		
//		ForwardUploadRequest request = new ForwardUploadRequest(new File(taggedWebflowFile));
//		request.setIniFile(new File(taggedWebflowIniFile));
//		PrepareIniFileForUse prepare = new PrepareIniFileForUse(server,request);
//		request.doTask(server, prepare.getW2AIniFile(), server.getXIniFile());
//		
//		String token = HTMLEdit.getTokenFromFilename(taggedWebflowFile) + ".zip";
//		Assert.assertTrue(server.existsInDownloads(token));
//		Assert.assertTrue(!server.existsInDownloads(taggedWebflowFile));
//		Assert.assertTrue(!server.existsInUploads(taggedWebflowFile));
//		
//	}
	
//	@Test
//	public void test_execute_process_webflow_archive_without() throws Exception {
//		
//		Server server = new Server(w4aIniFile);
//		server.copyToInventory(new W2AIniFile(webflowIniFile));
//		Assert.assertTrue(server.existsInInventory(webflowIniFile)); 
//		
//		String taggedWebflowFile = server.uploadAndTagZipFileToServer(webflowFile, token, UserRequest.FORWARD);
//		Assert.assertTrue(server.existsInUploads(taggedWebflowFile));
//		Assert.assertTrue(!server.existsInDownloads(taggedWebflowFile));
//		
//		server.execute_process_webflow_archive(new File(taggedWebflowFile));
//		String token = HTMLEdit.getTokenFromFilename(taggedWebflowFile) + ".zip";
//		Assert.assertTrue(server.existsInDownloads(token));
//		Assert.assertTrue(!server.existsInDownloads(taggedWebflowFile));
//		Assert.assertTrue(!server.existsInUploads(taggedWebflowFile));
//		
//	}

}
