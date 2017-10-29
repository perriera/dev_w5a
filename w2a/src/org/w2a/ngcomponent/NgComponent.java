package org.w2a.ngcomponent;

/*
 * NgComponent
 * 
 * The purpose of this class is to convert a WebFlow HTML page into 
 * an Angular 2 HTML component, which (at present) is four files in one:
 * 
 *     Assuming a file called 'sample.html' inside a Webflow Zip file:
 *     
 *          sample.html -> pages/sample/sample.component.css
 *          sample.html -> pages/sample/sample.component.html
 *          sample.html -> pages/sample/sample.component.spec.ts
 *          sample.html -> pages/sample/sample.component.ts
 *          
 *     Where the <head> portion of the 'sample.html' is stripped off and
 *     made part of the 'app/index.html' and the body of the 'index.html'
 *     file from the the WebFlow archive is split into two parts depending
 *     on where the ng-router specifier is placed. The outer part is placed
 *     in the 'app/app.component.html' file and the inner part is placed
 *     inside a 'home/home.component.html' file with the standard Router
 *     <router-outlet></router-outlet>.
 *          
 */

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.w0a.IniFile;
import org.w0a.exceptions.FeatureNotRequiredException;
import org.w0a.types.SourceHTMLFileEntry;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.angular2.NgAngular2NonCLI;
import org.w2a.ngfile.NgFile;
import org.w2a.ngfile.NgFileInterface;
import org.w2a.ngmorph.NgMorph;
import org.w2a.ngroute.NgAppRoutes;
import org.w2a.ngtypes.NgImageFile;
import org.w2a.ngtypes.NgJSFile;
import org.w2a.ngtypes.NgHTMLFile;
import org.w2a.ngtypes.NgTextFile;

abstract public class NgComponent implements SourceHTMLFileEntry, NgFileInterface {

	String key;
	String webflowHtml;
	protected String mergedHtml;
	NgFile ngFile;
	protected W2AIniFile iniFile;
	String directory;

	/**
	 * class NgComponent construct
	 * 
	 * @param key
	 * @param page
	 * @param directory
	 * @param iniFile
	 */

	public NgComponent(String key, String webflowHtml, String directory, W2AIniFile iniFile) {
		this.key = key;
		this.ngFile = new NgFile(key, directory, iniFile);
		this.webflowHtml = webflowHtml;
		this.mergedHtml = webflowHtml;
		this.directory = directory;
		this.iniFile = iniFile;
	}

	@Override
	public String getHTMLAssetFilename() throws Exception {
		String filename = ngFile.getComponentHTMLFilename();
		return filename;
	}

	public NgFile getNgFile() throws Exception {
		return ngFile;
	}

	@Override
	public IniFile getXIniFile() {
		return iniFile;
	}

	@Override
	public String getContent() {
		return mergedHtml;
	}

	@Override
	public void setContent(String html) {
		this.mergedHtml = html;
	}

	/**
	 * input()
	 * 
	 * This method creates a ng CLI style component inside the ng CLI angular
	 * project. It then merges the contents of the old HTML file of the same
	 * name if it exists into the new HTML file downloaded from Webflow.
	 * 
	 * @throws Exception
	 */

	@Override
	public void input() throws Exception {
		String oldContents = "";
		if (new File(getHTMLAssetFilename()).exists())
			oldContents = NgTextFile.input(getHTMLAssetFilename(), iniFile);
		mergedHtml = NgHTMLFile.checkForDuplicateIds(mergedHtml, this.key, iniFile);
		mergedHtml = NgHTMLFile.checkForMisplacedNgRouter(mergedHtml, this.key, iniFile);
		mergedHtml = NgImageFile.adjustImageLinks(mergedHtml, iniFile);
		mergedHtml = NgImageFile.adjustHRefLinks(mergedHtml, iniFile);
		mergedHtml = NgAppRoutes.adjustAHRefsWithRouterLinks(mergedHtml, iniFile);
		mergedHtml = NgAppRoutes.adjustRouterLinksWithParameters(mergedHtml, iniFile);
		mergedHtml = NgCSSComponent.removeCSSLinks(mergedHtml);
		mergedHtml = NgJSFile.adjustJSLinks(mergedHtml, iniFile);
		mergedHtml = merge(mergedHtml, oldContents, iniFile);
		mergedHtml = NgComponentExtractor.adjustCulyBraces(mergedHtml);
		mergedHtml = NgComponentExtractor.removeWebFlowItems(mergedHtml, iniFile);
		mergedHtml = NgMorph.morphSource(mergedHtml, this.iniFile);
		mergedHtml = NgComponentExtractor.outlineSource(mergedHtml, this.iniFile);
		mergedHtml = NgComponentExtractor.replaceBraces(mergedHtml, iniFile);
		mergedHtml = NgComponentExtractor.fixAmpIssue(mergedHtml, iniFile);
	}

	/**
	 * output()
	 * 
	 * This method writes the contents of the merged HTML file to the component
	 * HTML file in the directory.
	 * 
	 * @throws IOException
	 * @throws Exception
	 */

	public void createComponent() throws Exception {
//		if (true || iniFile.getNonCli()) {
			NgAngular2NonCLI worker = new NgAngular2NonCLI(this.iniFile, this.ngFile);
			worker.input(this.directory);
			this.directory = worker.output();
//		} else {
//			NgAngular2CLI worker = new NgAngular2CLIInspector(this.iniFile, this.ngFile);
//			worker.input(this.directory);
//			this.directory = worker.output();
//		}
	}

	@Override
	public void output() throws Exception {
		createComponent();
		NgTextFile.output(mergedHtml, getHTMLAssetFilename());
	}

	//
	// Methods below throw FeatureNotRequiredException
	//

	@Override
	public void input(String filename) throws Exception {
		throw new FeatureNotRequiredException();
	}

	@Override
	public void input(InputStream is) throws Exception {
		throw new FeatureNotRequiredException();
	}

	@Override
	public void output(String filename) throws Exception {
		this.output();
	}

	//
	// Static Members below ...
	//

	static boolean overrideUserEdittedValueInHTMLPage(String key, W2AIniFile iniFile) throws Exception {
		String[] reservedAttibutes = iniFile.getReservedAttibutes();
		for (String reservedWord : reservedAttibutes)
			if (key.equals(reservedWord))
				return true;
		return false;
	}

	static public String merge(String newContent, String oldContent, W2AIniFile iniFile) throws Exception {
		Document newDoc = HTMLEdit.parse(newContent);
		Document oldDoc = HTMLEdit.parse(oldContent);
		for (Element newE : newDoc.body().select("*")) {
			if (!newE.attr("id").equals("")) {
				String id = newE.attr("id");
				Element oldE = oldDoc.select("#" + id).first();
				if (oldE != null) {
					for (Attribute a : oldE.attributes().asList()) {
						String key = a.getKey();
						if (!overrideUserEdittedValueInHTMLPage(key, iniFile))
							newE.attributes().put(a);
					}
					for (Attribute a : oldE.attributes().asList()) {
						String test = a.getKey().toString();
						if (test.startsWith("_")) {
							String removal = test.substring(1);
							newE.removeAttr(removal);
						}
					}
					// TODO: Ensure that the following line works properly ...
					// String innerNewContent = newE.html();
					// String innerOldContent = oldE.html();
					// if ( innerNewContent.length()>0 ||
					// innerOldContent.length()>0 ) {
					// String results = merge(innerOldContent,innerNewContent);
					// newE.html(results);
					// }
					// newE.html(oldE.html());
				}
			}
		}
		String done = newDoc.body().toString();
		done = HTMLEdit.stripFirstAndLast(done);
		return done;
	}

}
