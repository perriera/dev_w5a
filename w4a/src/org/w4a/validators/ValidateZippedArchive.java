package org.w4a.validators;

import java.io.File;

import org.w0a.extractor.SourceExtractor;
import org.w0a.webflow.WebflowExtractor;
import org.w2a.W2AIniFile;
import org.w4a.exceptions.PartialUploadDetectedException;
import org.w4a.exceptions.ZippedSrcDroppedIntoWebflowBoxByMistakeException;
import org.w4a.keys.RequestKey;
import org.w4a.requests.UploadingMonitor2;
import org.w4a.tasks.PrepareIniFileForUse;

public class ValidateZippedArchive extends ValidationTools {

	SourceExtractor extractor;
	File zipFile = null;
	
	public ValidateZippedArchive(W2AIniFile iniFile) throws Exception {
		this.extractor = new WebflowExtractor(iniFile);
	}
	
	public ValidateZippedArchive(File zipFile) throws Exception {
		W2AIniFile w2aIniFile = PrepareIniFileForUse.useOrCreateDefaultIniFile(zipFile);
		this.iniFile = w2aIniFile;
		this.extractor = new WebflowExtractor(iniFile);
		this.zipFile = zipFile;
	}
	
	public boolean validate() throws Exception {
		
		this.extractor.input();

		testForNull(extractor,"src/");
		testForNull(extractor,"src/app/");
		testForNull(extractor,"src/app/app.component.html");
		testForNull(extractor,"src/app/app.component.ts");
		testForNull(extractor,"src/app/app.module.ts");
		testForNull(extractor,"src/assets/");
		testForNull(extractor,"src/environments/");
		testForNull(extractor,"src/main.ts");
		testForNull(extractor,"src/styles.css");
		
		if ( this.iniFile!=null && RequestKey.isForward(this.iniFile.getFile()) )
			throw new ZippedSrcDroppedIntoWebflowBoxByMistakeException(this.iniFile.getFilename());
		
		if ( zipFile!=null && !(UploadingMonitor2.isUploading(zipFile)) ) {
			UploadingMonitor2.clear(zipFile);
			throw new PartialUploadDetectedException(zipFile.getName());
		}
		
		return true;
	}
	
}
