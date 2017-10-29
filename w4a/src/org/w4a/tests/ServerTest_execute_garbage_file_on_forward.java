package org.w4a.tests;

import java.io.File;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w2a.W2AIniFile;
import org.w4a.Main;
import org.w4a.W4AIniFile;
import org.w4a.keys.UserRequest;
import org.w4a.server.Server;

public class ServerTest_execute_garbage_file_on_forward {

	private static String serverMonitorIniFile = "files/testcases/webflow/inifiles/w4a.monitor.ini";
	private static String webflowIniFile = "files/testcases/webflow/inifiles/c-org.webflow.ini";
	private static String webflowFile = "files/testcases/webflow/uploads/dronaid-ca.webflow.zip";
	private static String skeletonFile = "files/archives/angular2cli/skeleton.zip";
	private static String skeletonIniFile = "files/inifiles/skeleton.ini";
	private static String garbageFile = "files/testcases/zipped/uploads/ngrok";
	private static String token = "E6B7A2B1";

	@Before
	public void setUp() throws Exception {
		
		W4AIniFile w4aIniFile = new W4AIniFile(serverMonitorIniFile); 
		Server server = new Server(w4aIniFile);
		server.reset();
		
	}

	@Test
	public void test_execute() throws Exception {
		
		W4AIniFile w4aIniFile = new W4AIniFile(serverMonitorIniFile); 
		Server server = new Server(w4aIniFile);
		
		server.copyToResources(new File(skeletonFile));
		server.copyToInventory(new W2AIniFile(skeletonIniFile));
		String taggedFile = server.uploadAndTagZipFileToServer(garbageFile,token, UserRequest.FORWARD);
				
		Assert.assertTrue(server.existsInUploads(taggedFile));
		Assert.assertTrue(!server.isUploadsDirectoryEmpty());
		Assert.assertTrue(server.isDownloadsDirectoryEmpty());
		Assert.assertTrue(server.isDiagnosticsDirectoryEmpty());
		Assert.assertTrue(server.isProcessingDirectoryEmpty());
		Assert.assertTrue(!server.readyForDownloading(taggedFile));
		
		server.execute();
		
		Assert.assertTrue(!server.existsInUploads(taggedFile));
		Assert.assertTrue(server.isUploadsDirectoryEmpty());
		Assert.assertTrue(server.isDownloadsDirectoryEmpty());
		Assert.assertTrue(!server.isDiagnosticsDirectoryEmpty());
		Assert.assertTrue(!server.isProcessingDirectoryEmpty());
		Assert.assertTrue(!server.readyForDownloading(taggedFile));
		
	}
	
	
}
