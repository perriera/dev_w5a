package org.w4a.tests;

import java.io.File;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w0a.IniFile;
import org.w2a.W2AIniFile;
import org.w4a.server.Server;

public class ServerTest {

	private static String serverMonitorIniFile = "files/testcases/webflow/inifiles/w4a.monitor.ini";
	private static String webflowIniFile = "files/testcases/webflow/inifiles/c-org.webflow.ini";
	private static String webflowFile = "files/testcases/webflow/uploads/c-org.webflow.zip";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_construct() throws Exception {
		
		Server server = new Server(serverMonitorIniFile);
		Assert.assertNotNull(server);
	}

	@Test
	public void test_server_setup() throws Exception {
		
		Server server = new Server(serverMonitorIniFile);
		Assert.assertNotNull(server);
		
		server.setup();
		Assert.assertTrue(server.exists());
		
		server.destroy();
		Assert.assertTrue(!server.exists());
		
	}

	@Test
	public void test_server_copyfiles() throws Exception {
		
		Server server = new Server(serverMonitorIniFile);
		Assert.assertNotNull(server);
		
		server.setup();
		Assert.assertTrue(server.exists());
		
		server.copyToInventory(new W2AIniFile(webflowIniFile));
		Assert.assertTrue(server.existsInInventory(webflowIniFile));
		
		server.destroy();
		Assert.assertTrue(!server.exists());
		Assert.assertTrue(!server.existsInInventory(webflowIniFile));
		
	}

	@Test
	public void test_server_uploadWebflowArchive() throws Exception {
		
		Server server = new Server(serverMonitorIniFile);
		Assert.assertNotNull(server);
		
		server.setup();
		Assert.assertTrue(server.exists());
		
		server.uploadZipFileToServer(webflowFile);
		Assert.assertTrue(server.existsInUploads(webflowFile));
		
		server.destroy();
		Assert.assertTrue(!server.exists());
		Assert.assertTrue(!server.existsInUploads(webflowFile));
		
	}
	
	@Test
	public void test_server_findInInventory() throws Exception {
		
		Server server = new Server(serverMonitorIniFile);
		Assert.assertNotNull(server);
		
		server.setup();
		Assert.assertTrue(server.exists());
		
		server.copyToInventory(new W2AIniFile(webflowIniFile));
		Assert.assertTrue(server.existsInInventory(webflowIniFile));
		
		server.uploadZipFileToServer(webflowFile);
		Assert.assertTrue(server.existsInUploads(webflowFile));
		
		File webflowFileArchive = new File(webflowFile);
		IniFile iniFile = server.findInInventory(webflowFileArchive);
		Assert.assertNotNull(iniFile);
		Assert.assertTrue(iniFile instanceof W2AIniFile);
		
		server.destroy();
		Assert.assertTrue(!server.exists());
		Assert.assertTrue(!server.existsInUploads(webflowFile));
		Assert.assertTrue(!server.existsInInventory(webflowIniFile));
		
	}
	
//	@Test
//	public void test_monitor_uploads_directory() throws Exception {
//		
//		Server server = new Server(serverMonitorIniFile);
//		server.reset();
//	
//		Assert.assertNotNull(server);
//		Assert.assertTrue(server.uploadsDirExists());
//		
//		server.monitorSource();
//		
//		long time = new Date().getTime();
//		long test = new Date().getTime();
//		while (server.queueEmpty()) {
//			Thread.sleep(1000);
//			server.uploadZipFileToServer(ServerTest.webflowFile);
//			Thread.sleep(1000);
//			test = new Date().getTime();
//		}
//		
//		Assert.assertTrue(test>time);
//		Assert.assertTrue(test<time+3000);
//		
//	}
	
	
}
