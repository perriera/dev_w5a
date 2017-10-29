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

public class MainTest_execute {

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
		
		MainTest_execute.webflowFile = MainTest_execute._webflowFile; 
		
		this.w4aIniFile = new W4AIniFile(serverMonitorIniFile);
		Server server = new Server(w4aIniFile);
		server.reset();
		server.copyToInventory(new W2AIniFile(webflowIniFile));
		server.copyToResources(new File(skeletonFile));
		server.copyToInventory(new W2AIniFile(skeletonIniFile));
		webflowFile = server.uploadAndTagZipFileToServer(webflowFile,token, UserRequest.FORWARD);
		
	}

	@Test
	public void test_execute() throws Exception {
		
		Server server = new Server(w4aIniFile);

		Assert.assertTrue(server.existsInInventory(webflowIniFile));
		Assert.assertTrue(server.existsInUploads(webflowFile));
		Assert.assertTrue(!server.existsInUploads(zippedFile));
	
		Assert.assertTrue(!server.isUploadsDirectoryEmpty());
		
		String[] args = { w4aIniFile.getFilename(), "-w" };
		Main main = new Main(args);
		main.execute();

		Assert.assertTrue(server.isUploadsDirectoryEmpty());
		
	}
	
	
}
