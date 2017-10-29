package org.w2a.ngexport;

import java.io.File;
import java.util.Map.Entry;

import org.w1a.HTMLEdit;
import org.w2a.ngimport.NgImportFormat;
import org.w2a.ngtypes.NgTextFile;

public class NgExportAngular2CLISharedFolderFormat extends NgExportFormat {

	public NgExportAngular2CLISharedFolderFormat(NgImportFormat ngImportFormat) {
		super(ngImportFormat);
	}

	@Override
	public void input() throws Exception {
		ngImportFormat.input();
	}

	@Override
	public void output() throws Exception {
		ngImportFormat.output();
		String desDir = this.getXIniFile().getDestination();
		for (Entry<File, String> file : ngImportFormat.entrySet() ) {
			File key = file.getKey();
			String contents = file.getValue();
		    if (key.isFile()) {
		    	String fileName = HTMLEdit.append(desDir, key.getName());
				NgTextFile.output(contents,fileName);
		    }
		}
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cleanup() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
