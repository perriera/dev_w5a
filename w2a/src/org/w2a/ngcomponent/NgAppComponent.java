package org.w2a.ngcomponent;

/**
 * 
 * [04:52] NiceRedBall: I'm so happy y're here Roxanne ☻!
 * [04:52] Pʀɪɴᴄᴇss Bᴜɴɴʏ Lᴇᴛʜᴀʟ™ (DjNaughtyBunny Resident) is online.
 * [04:52] ღRoseBellaღ (RoseDoll546 Resident) is online.
 * [04:52] Syrah Evans is online.
 * 
 */

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.w0a.types.SourceIndexFileEntry;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.exceptions.IndexFileMissingNgRouterException;
import org.w2a.ngfile.NgFileInterface;
import org.w2a.ngtypes.NgImageFile;

public class NgAppComponent extends NgBaseComponent implements SourceIndexFileEntry, NgFileInterface {

	SourceIndexFileEntry entry;
	
	public NgAppComponent(String key, SourceIndexFileEntry entry, W2AIniFile iniFile) throws Exception {
		super(key,entry.getContent(),iniFile);
		this.entry = entry;
	}
	
	@Override
	public String getHTMLAssetFilename() throws Exception {
		return this.getNgFile().getAppComponentFilename();
	}
	
	/*
	 * Take <body> portion of the original index.html file and split
	 * it into two parts, one for the app/app.component.html and
	 * the remainder for a new app/home/home.componnent.html. 
	 */
	
	String implementRouterOutlet(String before) throws IndexFileMissingNgRouterException {
		Document doc = HTMLEdit.parse(before);
		Element body = doc.body();
		Element link = doc.select("div[ng-router]").first();
		if ( link==null ) {
			// throw new IndexFileMissingNgRouterException("app/app.component.html","index.html");
			link = doc.select("div").first();
		}
		Element ngRouter = doc.createElement("router-outlet"); // Create the new element
		link.replaceWith(ngRouter); // Replace element with new one
		String after = body.toString();
		return after;
	}
	
	@Override
	public void input() throws Exception {
		super.input();
		String appComponentHtml = implementRouterOutlet(this.getContent());
		// TODO: find out which of the below are not required
		appComponentHtml = NgImageFile.adjustImageLinks(appComponentHtml,iniFile);
		appComponentHtml = NgComponentExtractor.removeHeaderAndBody(appComponentHtml,iniFile); 
		appComponentHtml = NgComponentExtractor.outlineSource(appComponentHtml, this.iniFile);
		this.setContent(appComponentHtml);
	}
	
	/*
	 * Override the default behavior of createComponent() as 
	 * app/app.component.html is already created by default.
	 * 
	 * (non-Javadoc)
	 * @see org.w2a.ngcomponent.NgBaseComponent#createComponent()
	 */
	
	public void createComponent() throws Exception {
		// override default behavior
		// app/app.component.html is created by default
	}
	

	
}
