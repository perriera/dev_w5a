package org.w2a.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w2a.W2AIniFile;
import org.w2a.ngfile.NgFile;
import org.w2a.ngroute.NgAppRoutes;
import org.w2a.ngroute.NgImport;
import org.w2a.ngtypes.NgTextFile;

public class NgAppRoutesTest /*extends NgTestSetup*/ {

	static String testFilename = "files/assignment-cpp-dot-org.ini";
	String contents;
	W2AIniFile iniFile;
	NgFile ngFile;
	String appModuleFilename;
	String appModuleContent;

	@Before
	public void setUp() throws Exception {
		//super.setUp();
		this.contents = NgTextFile.input("files/test.module.ts");
		this.iniFile = new W2AIniFile(testFilename);
		this.ngFile = new NgFile(this.iniFile);
		this.appModuleFilename = ngFile.getAppModuleFilename();
		this.appModuleContent = NgTextFile.input(appModuleFilename, iniFile);
		Assert.assertTrue(appModuleContent.contains("export class AppModule { }"));
	}

//	@Test
//	public void test() throws Exception {
//		NgAppRoutes appRoutes = new NgAppRoutes(this.contents,this.iniFile);
//		appRoutes.input();
//	}
	
	@Test()
	public void testFormats_01_HomeComponent() throws Exception {

		String test = "import { HomeComponent } from './pages/home/home.component';\n";
		NgImport ngImport = new NgImport(test, iniFile);
		Assert.assertEquals( ngImport.getFormattedURL(), "" );
		
		Assert.assertEquals( NgAppRoutes.adjustLink("index.html"),"/");
		
	}
	
	@Test()
	public void testFormats_02b_ReviewAnArticleComponent() throws Exception {

		String path = "import { RegisterComponent } from './pages/articles/review-an-article/review-an-article.component';\n";
		String link = "articles/review-an-article.html";

		NgImport ngImport = new NgImport(path, iniFile);
		String importUrl = ngImport.getFormattedURL();
		String importRef = NgAppRoutes.adjustLink(link);
		
		Assert.assertEquals( importUrl, "articles_review_an_article" );
		Assert.assertEquals( importRef,"/articles_review_an_article");
		
	}
	
	@Test()
	public void testFormats_02_RegisterComponent() throws Exception {

		String path = "import { RegisterComponent } from './pages/register/register.component';\n";
		NgImport ngImport = new NgImport(path, iniFile);
		String importUrl = ngImport.getFormattedURL();
		Assert.assertEquals( importUrl, "register" );
		
		Assert.assertEquals( NgAppRoutes.adjustLink("register.html"),"/register");
		
	}
	
	@Test()
	public void testFormats_03_LoginComponent() throws Exception {

		String path = "import { LoginComponent } from './pages/login/login.component';\n";
		NgImport ngImport = new NgImport(path, iniFile);
		String importUrl = ngImport.getFormattedURL();
		Assert.assertEquals( importUrl, "login" );
		
		Assert.assertEquals( NgAppRoutes.adjustLink("login.html"),"/login");
		
	}
	
	@Test()
	public void testFormats_04_HistoryComponent() throws Exception {

		String path = "import { HistoryComponent } from './pages/history/history.component';\n";
		NgImport ngImport = new NgImport(path, iniFile);
		String importUrl = ngImport.getFormattedURL();
		Assert.assertEquals( importUrl, "history" );
		
		Assert.assertEquals( NgAppRoutes.adjustLink("history.html"),"/history");
		
	}
	
	@Test()
	public void testFormats_05_PublicationsComponent() throws Exception {

		String path = "import { PublicationsComponent } from './pages/about-cpp/bjarne/publications/publications.component';\n";
		NgImport ngImport = new NgImport(path, iniFile);
		String importUrl = ngImport.getFormattedURL();
		Assert.assertEquals( importUrl, "about_cpp_bjarne_publications" );
		
		String refUrl = NgAppRoutes.adjustLink("../../about-cpp/bjarne/publications.html");
		Assert.assertEquals( refUrl,"/"+importUrl);
		
	}
	
	@Test()
	public void testFormats_07_BjarneComponent() throws Exception {

		String path = "import { BjarneComponent } from './pages/about-cpp/bjarne/bjarne.component';\n";
		NgImport ngImport = new NgImport(path, iniFile);
		String importUrl = ngImport.getFormattedURL();
		Assert.assertEquals( importUrl, "about_cpp_bjarne" );
		
		String refUrl = NgAppRoutes.adjustLink("../../about-cpp/bjarne/bjarne.html");
		Assert.assertEquals( refUrl,"/"+importUrl);
		
	}
	
	@Test()
	public void testFormats_08_BjarneComponent() throws Exception {

		String path = "import { UtilitiesComponent } from './pages/users/utilities/utilities.component';\n";
		NgImport ngImport = new NgImport(path, iniFile);
		String importUrl = ngImport.getFormattedURL();
		Assert.assertEquals( importUrl, "users_utilities" );
		
		String refUrl = NgAppRoutes.adjustLink("../../users/utilities/utilities.html");
		Assert.assertEquals( refUrl,"/"+importUrl);
		
	}
	
	
//	@Test()
//	public void testFormats_08_HistoryComponent() throws Exception {
//
//		String path = "import { HistoryComponent } from './pages/history/history.component';\n";
//		NgImport ngImport = new NgImport(path,this.iniFile);
//		ngImport.input();
//		String importUrl = ngImport.getFormattedURL();
//		String appRoute = ngImport.getAppRoute();
//		ngImport.output();
//		String outputPath = ngImport.getContent();
//		Assert.assertEquals( importUrl, "history" );
//		
//		Assert.assertEquals( NgAppRoutes.adjustLink("history.html"),"/history");
//		
//	}
	
//	@Test()
//	public void testFormatModule5() throws Exception {
//		
//		String imports = "";
//		imports += "import { HomeComponent } from './pages/home/home.component';\n";
//		imports += "import { HistoryComponent } from './pages/history/history.component';\n";
//		imports += "import { StandardizationComponent } from './pages/history/standardization/standardization.component';\n";
//		imports += "import { PublicationsComponent } from './pages/about-cpp/bjarne/publications/publications.component';\n";
//		imports += "import { BjarneComponent } from './pages/about-cpp/bjarne/bjarne.component';\n";
//		imports += "import { FrequentlyRequestedPapersComponent } from './pages/about-cpp/bjarne/frequently-requested-papers/frequently-requested-papers.component';\n";
//		imports += "import { FrequentlyRequestedPapers1Component } from './pages/about-cpp/bjarne/frequently-requested-papers/frequently-requested-papers-1/frequently-requested-papers-1.component';\n";
//		imports += "import { RegisterComponent } from './pages/register/register.component';\n";
//		imports += "import { LoginComponent } from './pages/login/login.component';\n";
//		
//		NgImportMap ngImportMap = new NgImportMap(imports, iniFile);
//		NgImport ngImport = ngImportMap.get("HistoryComponent");
//		
//		String before = ngImport.getPath();
//		Assert.assertNotNull( before );
//
//		
//	}

}

