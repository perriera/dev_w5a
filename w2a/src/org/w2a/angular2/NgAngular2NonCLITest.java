package org.w2a.angular2;

import org.junit.Test;
import org.w2a.tests.NgTestSetup;

public class NgAngular2NonCLITest extends NgTestSetup {

	String desDir = "./mock-project/src/app/pages/about-cpp/bjarne/frequently-requested-papers/frequently-requested-papers-4/frequently-requested-papers-4.component.html";
	
	
	@Test
	public void test() throws Exception {
		//this.iniFile;
		NgAngular2NonCLI nc = new NgAngular2NonCLI(NgTestSetup.iniFile);
		
		nc.input(desDir);
		nc.output();
		
	}

}
