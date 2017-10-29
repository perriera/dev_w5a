package org.w2a.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.w2a.ngexport.NgExportAngular2CLISharedFolderFormatTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	W2AIniFileTest.class,
	NgTest.class, 
	NgSymbolTest.class,
	NgHTMLTest.class,
	NgAssetsTest.class,
	NgJSTest.class,
	NgIndexTest.class,
	NgComponentExtractorTest.class,
	NgAppModuleTest.class,
	NgAppRoutingModuleTest.class,
	NgAppReusableRoutingModuleTest.class,
	NgAppRoutesTest.class,
	NgPrettyPepperTest.class,
	NgImportNodeJSFormatTest.class,
	NgExportAngular2CLISharedFolderFormatTest.class,
	RemoveWebFlowItemsTest.class,
	AdjustRouterLinksWithParametersTest.class,
	AdjustBracesTest.class,
	CheckForDuplicateIdsTest.class,
	NgModuleCompilerTest.class,
	NgRouteMapTest.class,
	NgExternalTest.class
}) 
public class AllTests {

}
