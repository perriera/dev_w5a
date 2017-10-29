package org.w0a.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.w0a.webflow.tests.WebflowArchiverTest;
import org.w0a.webflow.tests.WebflowExtractorTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	IniFileTest.class, 
	MainTest.class, 
	WebflowExtractorTest.class, 
	WebflowArchiverTest.class 
})
public class AllTests {

}

