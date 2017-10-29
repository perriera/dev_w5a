package org.w4a.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	ServerTest.class, 
	ServerTest_ForwardRequest.class,
	// ServerTest_ReverseRequest.class,
	ServerTest_ForwardUploadRequest.class,
	ServerTest_ReverseUploadRequest.class,
	RequestKeyTest.class,
	InventoryKeyTest.class,
	InventoryRequestKeyTest.class,
	MainTest_execute.class,
	MainTest_monitor_Server.class,
	MainTest_monitor_single_thread_Server.class,
	ServerTest_BadUploadRequest.class,
	ServerTest_PartialUploadRequest.class,
	ServerTest_execute_wrong_zipfile_on_forward.class,
	ServerTest_execute_garbage_file_on_forward.class,
	ServerTest_execute_reverse.class,
	ServerTest_execute_reverse2.class
})
public class AllTests {
	
}


