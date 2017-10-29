package org.w4a.validators;

import java.io.File;

import org.w0a.IniFile;
import org.w0a.extractor.SourceExtractor;
import org.w4a.exceptions.BogusZipFileLoadedIntoUploadsException;
import org.w4a.exceptions.PartialUploadDetectedException;
import org.w4a.requests.UploadingMonitor;
import org.w4a.requests.UploadingMonitor2;

public class ValidateIniFile extends ValidationTools {

	SourceExtractor extractor;
	File zipFile;
	
	public ValidateIniFile(File zipFile) throws Exception {
		this.iniFile = new IniFile(zipFile);
		this.zipFile = zipFile;
		//this.extractor = new WebflowExtractor(iniFile);
	}
	
	public boolean validate() throws Exception {
		
//		this.extractor.input();
//
//		testForNull(extractor,"src/");
//		testForNull(extractor,"src/app/");
//		testForNull(extractor,"src/app/app.component.html");
//		testForNull(extractor,"src/app/app.component.ts");
//		testForNull(extractor,"src/app/app.module.ts");
//		testForNull(extractor,"src/assets/");
//		testForNull(extractor,"src/environments/");
//		testForNull(extractor,"src/main.ts");
//		testForNull(extractor,"src/styles.css");
		
		if ( !(UploadingMonitor2.isUploading(zipFile)) ) {
			UploadingMonitor2.clear(zipFile);
			throw new PartialUploadDetectedException(zipFile.getName());
		}
		
		return true;
	}
	
}
