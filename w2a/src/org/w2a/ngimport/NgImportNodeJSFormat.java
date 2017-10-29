package org.w2a.ngimport;

import java.io.File;
import org.w1a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.ngtypes.NgTextFile;

@SuppressWarnings("serial")
public class NgImportNodeJSFormat extends NgImportFormat {

	File[] fileListing;
	
	public NgImportNodeJSFormat(W2AIniFile iniFile) {
		super(iniFile);
	}

	@Override
	public void input() throws Exception {
		String srcDir = iniFile.getSource();
		this.fileListing = new File(srcDir).listFiles();
	}

	private String replaceMongooseImport(String contents) throws Exception {
		String from = iniFile.getReplaceMongooseFrom();
		String to = iniFile.getReplaceMongooseTo();
		String[] lines = contents.split("\n");
		String result = "";
		for ( String line : lines ) {
			if ( line.trim().equals(from) )
				line = line.replace(from, to);
			result += line +"\n";
		}
		return result;
	}

	@Override
	public void output() throws Exception {
		String srcDir = iniFile.getSource();
		for (File file : this.fileListing ) {
		    if (file.isFile()) {
		    	String fileName = HTMLEdit.append(srcDir, file.getName());
				String contents = NgTextFile.input(fileName);
		    	this.put(file, contents);
				if ( iniFile.replaceMongooseImport() ) {
					contents = replaceMongooseImport(contents);
					this.put(file, contents);
				}
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
