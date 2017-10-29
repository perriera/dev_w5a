package org.w2a.ngcomponent;

/*
 * NgComponent
 * 
 * The purpose of this class is to convert a WebFlow 'index.html' page into 
 * three different Angular 2 HTML files, two of which are components and one
 * CSS file used by Angular 2 for all CSS style files:
 * 
 *     Assuming a file called 'sample.html' inside a Webflow Zip file:
 *     
 *          index.html -> src/index.html
 *          index.html -> src/app/app.component.html
 *          index.html -> src/home/home.component.html
 *          index.html -> styles.css
 *          
 *     Where the <head> portion of the 'index.html' is stripped off and
 *     made part of the 'app/index.html' and the body of the 'index.html'
 *     file from the the WebFlow archive is split into two parts depending
 *     on where the ng-router specifier is placed. The outer part is placed
 *     in the 'app/app.component.html' file and the inner part is placed
 *     inside a 'home/home.component.html' file with the standard Router
 *     <router-outlet></router-outlet>. 
 *          
 */

import org.w0a.extractor.SourceExtractor;
import org.w0a.types.SourceIndexFileEntry;
import org.w2a.W2AIniFile;
import org.w2a.ngfile.NgFileInterface;
import org.w2a.ngtypes.NgIndexFile;
import org.w2a.ngtypes.NgHTMLFile;


public class NgIndexComponent extends NgHTMLFile implements SourceIndexFileEntry, NgFileInterface {

	SourceIndexFileEntry entry;
	NgIndexFile ngIndexFile;
	NgAppComponent ngAppComponent;
	NgHomeComponent ngHomeComponent;
	NgCSSComponent ngComponentCSS;

	public NgIndexComponent(String key, SourceIndexFileEntry entry, W2AIniFile iniFile, SourceExtractor extractor) throws Exception {
		super(key,entry,iniFile);
		this.entry = entry;
		this.ngIndexFile = new NgIndexFile(key,entry,iniFile);
		this.ngAppComponent = new NgAppComponent(key,entry,iniFile);
		this.ngHomeComponent = new NgHomeComponent(key,entry,iniFile);
		this.ngComponentCSS = new NgCSSComponent(entry,extractor,iniFile);
	}
	
	@Override
	public String getHTMLAssetFilename() throws Exception {
		return this.getNgFile().getIndexFilename();
	}
	
	@Override
	public void input() throws Exception {
		this.ngIndexFile.input();
		this.ngAppComponent.input();
		this.ngHomeComponent.input();
		this.ngComponentCSS.input();
	}
	
	@Override
	public void output() throws Exception {
		this.ngIndexFile.output();
		this.ngAppComponent.output();
		this.ngHomeComponent.output();
		this.ngComponentCSS.output();
	}
	
	@Override
	public void output(String name) throws Exception {
		this.input();
		this.output();
	}
	
}
