package org.w4a.validators;

import java.io.File;

import org.w0a.extractor.SourceExtractor;
import org.w0a.webflow.WebflowExtractor;
import org.w2a.W2AIniFile;
import org.w4a.exceptions.PartialUploadDetectedException;
import org.w4a.requests.UploadingMonitor2;
import org.w4a.tasks.PrepareIniFileForUse;

public class ValidateWebflowArchive extends ValidationTools {

	File zipFile = null;
	SourceExtractor extractor;
	
	public ValidateWebflowArchive(W2AIniFile iniFile) throws Exception {
		this.extractor = new WebflowExtractor(iniFile);
	}
	
	public ValidateWebflowArchive(File zipFile) throws Exception {
		W2AIniFile w2aIniFile = PrepareIniFileForUse.useOrCreateDefaultIniFile(zipFile);
		this.iniFile = w2aIniFile;
		this.extractor = new WebflowExtractor(iniFile);
		this.zipFile = zipFile;
	}

	public boolean validate() throws Exception {
		
		this.extractor.input();
		
		testForNull(extractor,"images/");
		testForNull(extractor,"css/");
		testForNull(extractor,"css/normalize.css");
		testForNull(extractor,"css/webflow.css");
		testForNull(extractor,"js/webflow.js");
		testForNull(extractor,"index.html");
		
		if ( zipFile!=null && !(UploadingMonitor2.isUploading(zipFile)) ) {
			UploadingMonitor2.clear(zipFile);
			throw new PartialUploadDetectedException(zipFile.getName());
		}
		
		return true;
		
	} 
	
}
