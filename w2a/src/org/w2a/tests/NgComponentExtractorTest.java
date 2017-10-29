package org.w2a.tests;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.w2a.HTMLEdit;
import org.w2a.angular2.NgAngular2CLI;
import org.w2a.angular2.NgAngular2NonCLI;
import org.w2a.ngcomponent.NgComponentExtractor;
import org.w2a.ngfile.NgFile;
import org.w2a.ngmodule.NgAppModule;
import org.w2a.ngtypes.NgTextFile;

public class NgComponentExtractorTest extends NgTestSetup {

	@Test()
	public void testFormatComponent() throws Exception {

		NgComponentExtractor formatter = new NgComponentExtractor(extractor);
		Assert.assertNotNull(formatter);

		formatter.input();
		formatter.output();

		String indexFilename = iniFile.getDestination() + "/index.html";
		boolean test = new File(indexFilename).isFile();
		Assert.assertTrue(test);
		String htmlContent = NgTextFile.input(indexFilename, iniFile);
		Assert.assertTrue(htmlContent.contains("<app-root></app-root>"));

	}

	@Test()
	public void testCliFormat() throws Exception {

		NgComponentExtractor formatter = new NgComponentExtractor(extractor);
		Assert.assertNotNull(formatter);

		formatter.input();
		formatter.output();

		String indexFilename = iniFile.getDestination() + "/index.html";
		boolean test = new File(indexFilename).isFile();
		Assert.assertTrue(test);
		String htmlContent = NgTextFile.input(indexFilename, iniFile);
		Assert.assertTrue(htmlContent.contains("<app-root></app-root>"));

	}

	@Test()
	public void testNonCliFormat() throws Exception {

		NgComponentExtractor formatter = new NgComponentExtractor(extractor);
		Assert.assertNotNull(formatter);

		iniFile.setNonCli(true);
		iniFile.setNonCliTrainerActive(false);

		formatter.input();
		formatter.output();

		iniFile.setNonCli(false);

		String indexFilename = iniFile.getDestination() + "/index.html";
		boolean test = new File(indexFilename).isFile();
		Assert.assertTrue(test);
		String htmlContent = NgTextFile.input(indexFilename, iniFile);
		Assert.assertTrue(htmlContent.contains("<app-root></app-root>"));

	}


	String getOutdir() throws Exception {
		String desDir = NgTestSetup.iniFile.getDestination();
		String appDir = NgTestSetup.iniFile.getAppDir();
		String viewDir = NgTestSetup.iniFile.getViewsDir();
		return HTMLEdit.append(desDir, appDir, viewDir);
	}
	
	void test_output(boolean skip) throws Exception {
		
		String pageDir = "about-cpp/bjarne/frequently-requested-papers/frequently-requested-papers-4";
		String outputDir = HTMLEdit.append(getOutdir(), pageDir);

		String[] tests = { ".css", ".html", ".spec.ts", ".ts" };
		for (String ext : tests) {
			String a1 = HTMLEdit.loadLocalFile("files/formaltests/angular2/cli/correct-output" + ext);
			String b1 = HTMLEdit
					.loadLocalFile(HTMLEdit.append(outputDir, "frequently-requested-papers-4.component" + ext));
			Assert.assertTrue(a1.trim().equals(b1.trim()));
		}
		String a1 = HTMLEdit.loadLocalFile("files/formaltests/angular2/cli/app.module1.ts");
		String desDir = NgTestSetup.iniFile.getDestination();
		String appDir = NgTestSetup.iniFile.getAppDir();
		String b1 = HTMLEdit.loadLocalFile(HTMLEdit.append(desDir, appDir, "app.module.ts"));
		
		if ( !skip )
			Assert.assertTrue(a1.equals(b1));
		
		NgAppModule appModule = new NgAppModule(iniFile);
		/*String before =*/ appModule.readFile();
		String after = appModule.output();

//		Assert.assertTrue(a1.trim().equals(before.trim()));
		String a2 = HTMLEdit.loadLocalFile("files/formaltests/angular2/cli/app.module2.ts");
		Assert.assertTrue(a2.trim().equals(after.trim()));
		
	}

	@Test
	public void testNgAngular2CLI() throws Exception {

		String key = "about-cpp/bjarne/frequently-requested-papers/frequently-requested-papers-4.html";
		String directory = "pages/";
		NgFile ngFile = new NgFile(key, directory, NgTestSetup.iniFile);
		NgAngular2CLI nc = new NgAngular2CLI(NgTestSetup.iniFile, ngFile);

		nc.input(directory);
		nc.output();

		test_output(false);

	}

	@Test
	public void testNgAngular2NonCLI() throws Exception {

		String key = "about-cpp/bjarne/frequently-requested-papers/frequently-requested-papers-4.html";
		String directory = "pages/";
		NgFile ngFile = new NgFile(key, directory, NgTestSetup.iniFile);
		NgAngular2NonCLI nc = new NgAngular2NonCLI(NgTestSetup.iniFile, ngFile);

		nc.input(directory);
		nc.output();
		
		test_output(true);

	}

}
