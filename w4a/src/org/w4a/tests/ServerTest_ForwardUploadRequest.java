package org.w4a.tests;

import java.io.File;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w2a.W2AIniFile;
import org.w4a.W4AIniFile;
import org.w4a.keys.InventoryKey;
import org.w4a.keys.RequestKey;
import org.w4a.keys.UploadedKey;
import org.w4a.keys.UserRequest;
import org.w4a.requests.UploadRequestLogic;
import org.w4a.server.Server;

public class ServerTest_ForwardUploadRequest {

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
		
		ServerTest_ForwardUploadRequest.webflowFile = ServerTest_ForwardUploadRequest._webflowFile; 
		
		this.w4aIniFile = new W4AIniFile(serverMonitorIniFile);
		Server server = new Server(w4aIniFile);
		server.reset();
		server.copyToInventory(new W2AIniFile(webflowIniFile));
		server.copyToResources(new File(skeletonFile));
		server.copyToInventory(new W2AIniFile(skeletonIniFile));
		webflowFile = server.uploadAndTagZipFileToServer(webflowFile,token, UserRequest.FORWARD);
		
	}

	@After
	public void cleanUp() throws Exception {
		
		Server server = new Server(new W4AIniFile(serverMonitorIniFile));
		server.destroy();
		
	}

	@Test
	public void test_parseUploaded_webflowFile() throws Exception {
		
		Server server = new Server(w4aIniFile);
		UploadRequestLogic url = new UploadRequestLogic(server);
		
		Assert.assertTrue(server.existsInInventory(webflowIniFile));
		Assert.assertTrue(server.existsInUploads(webflowFile));
		Assert.assertTrue(!server.existsInUploads(zippedFile));
		
		url.parseUploaded(new File(webflowFile));
		
		UploadedKey uk = new UploadedKey(new File(webflowFile));
		RequestKey rk = new RequestKey(uk.getKey());
		InventoryKey ik = new InventoryKey(rk.getKey());
		
		Assert.assertTrue(!url.getZippedFileMap().containsKey(ik.getToken()));
		Assert.assertTrue(!url.getReverseWebflowFileMap().containsKey(ik.getToken()));
		Assert.assertTrue(url.getForwardWebflowFileMap().containsKey(ik.getToken()));
		
	}
	
	@Test
	public void test_determine_upload_request1() throws Exception {
		
		Server server = new Server(w4aIniFile);
		UploadRequestLogic url = new UploadRequestLogic(server);
		
		Assert.assertTrue(server.existsInInventory(webflowIniFile));
		Assert.assertTrue(server.existsInUploads(webflowFile));
		Assert.assertTrue(!server.existsInUploads(zippedFile));
	
		url.input(server.getXIniFile().getUploadsDir());
		
		UploadedKey uk = new UploadedKey(new File(webflowFile));
		RequestKey rk = new RequestKey(uk.getKey());
		InventoryKey ik = new InventoryKey(rk.getKey());
		
		Assert.assertTrue(!url.getZippedFileMap().containsKey(ik.getToken()));
		Assert.assertTrue(!url.getReverseWebflowFileMap().containsKey(ik.getToken()));
		Assert.assertTrue(url.getForwardWebflowFileMap().containsKey(ik.getToken()));
		Assert.assertTrue(url.getUploadRequestList().containsKey(ik.getToken()));
		
	}

	@Test
	public void test_determine_upload_request2() throws Exception {
		
		Server server = new Server(w4aIniFile);

		Assert.assertTrue(server.existsInInventory(webflowIniFile));
		Assert.assertTrue(server.existsInUploads(webflowFile));
		Assert.assertTrue(!server.existsInUploads(zippedFile));
	
		UploadRequestLogic url = server.determineUploadRequest();
		
		UploadedKey uk = new UploadedKey(new File(webflowFile));
		RequestKey rk = new RequestKey(uk.getKey());
		InventoryKey ik = new InventoryKey(rk.getKey());
		
		Assert.assertTrue(!url.getZippedFileMap().containsKey(ik.getToken()));
		Assert.assertTrue(!url.getReverseWebflowFileMap().containsKey(ik.getToken()));
		Assert.assertTrue(url.getForwardWebflowFileMap().containsKey(ik.getToken()));
		Assert.assertTrue(url.getUploadRequestList().containsKey(ik.getToken()));

		
	}
	
	@Test
	public void test_execute1() throws Exception {
		
		Server server = new Server(w4aIniFile);

		Assert.assertTrue(server.existsInInventory(webflowIniFile));
		Assert.assertTrue(server.existsInUploads(webflowFile));
		Assert.assertTrue(!server.existsInUploads(zippedFile));
	
		Assert.assertTrue(!server.isUploadsDirectoryEmpty());
		server.execute();
		Assert.assertTrue(server.isUploadsDirectoryEmpty());
		
	}


}
