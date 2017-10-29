package org.w2a.ngexport;

import org.w0a.IniFile;
import org.w0a.interfaces.SourceExtractorInterface;
import org.w2a.ngimport.NgImportFormat;

abstract public class NgExportFormat implements SourceExtractorInterface {

	NgImportFormat ngImportFormat;
	
	public NgExportFormat(NgImportFormat ngImportFormat2) {
		this.ngImportFormat = ngImportFormat2;
	}
	
	@Override
	public IniFile getXIniFile() {
		return this.ngImportFormat.getXIniFile();
	}

	@Override
	public void input() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void output() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
