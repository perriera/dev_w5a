package org.w4a.tests;

import java.io.File;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w2a.W2AIniFile;
import org.w4a.Main;
import org.w4a.W4AIniFile;
import org.w4a.keys.UserRequest;
import org.w4a.server.Server;

public class ServerTest_ReverseRequest_x3 {

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
	
	class Results {
		public String webflow;
		public String zipped;
	};

	Results beforeTest(Server server, String token) throws Exception {
		String t1webflow = server.uploadAndTagZipFileToServer(webflowFile, token, UserRequest.REVERSE);
		String t1zipped = server.uploadAndTagZipFileToServer(zippedFile, token, UserRequest.REVERSE);
		Assert.assertTrue(server.existsInUploads(t1webflow));
		Assert.assertTrue(server.existsInUploads(t1zipped));
		Assert.assertTrue(!server.existsInDownloads(t1webflow));
		Results results = new Results();
		results.webflow = t1webflow;
		results.zipped = t1zipped;
		return results;
	}
	
	@Test
	public void test_execute_process_zipped_archive_x3() throws Exception {
		
		Server server = new Server(w4aIniFile);
		server.copyToInventory(new W2AIniFile(webflowIniFile));
		Assert.assertTrue(server.existsInInventory(webflowIniFile));
		
		Results t1 = beforeTest(server,"1234567");
		Results t2 = beforeTest(server,"2345671");
		Results t3 = beforeTest(server,"3456712");
		
		// server.execute_process_zipped_archive(new File(webflowFile), new File(zippedFile));
		
		String[] args = { w4aIniFile.getFilename(), "-w" };
		Main main = new Main(args);
		main.execute();
		
		while (server.isProcessingQueue())
			Thread.sleep(1000);

		Assert.assertTrue(server.existsInDownloads(new File(t1.webflow).getName()));
		Assert.assertTrue(!server.existsInUploads(new File(t1.webflow).getName()));
		Assert.assertTrue(!server.existsInDownloads(new File(t1.zipped).getName()));
		Assert.assertTrue(!server.existsInUploads(new File(t1.zipped).getName()));
		
		Assert.assertTrue(server.existsInDownloads(new File(t2.webflow).getName()));
		Assert.assertTrue(!server.existsInUploads(new File(t2.webflow).getName()));
		Assert.assertTrue(!server.existsInDownloads(new File(t2.zipped).getName()));
		Assert.assertTrue(!server.existsInUploads(new File(t2.zipped).getName()));

		Assert.assertTrue(server.existsInDownloads(new File(t3.webflow).getName()));
		Assert.assertTrue(!server.existsInUploads(new File(t3.webflow).getName()));
		Assert.assertTrue(!server.existsInDownloads(new File(t3.zipped).getName()));
		Assert.assertTrue(!server.existsInUploads(new File(t3.zipped).getName()));
	
	}

}
