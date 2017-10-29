package org.w1a.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	Angular1BodyFormatTest.class, 
	Angular1ContentFormatTest.class,
	MainTest.class
})
public class AllTests {
}
