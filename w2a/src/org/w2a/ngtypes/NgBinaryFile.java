package org.w2a.ngtypes;

import org.w0a.exceptions.FeatureNotImplementedException;
import org.w0a.types.SourceFileEntry;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.ngfile.NgFile;
import org.w2a.ngfile.NgFileInterface;


public class NgBinaryFile implements SourceFileEntry, NgFileInterface {

	String key;
	NgFile ngFile;
	protected SourceFileEntry entry;
	static String assetsDir = "assets/";
	
	public NgBinaryFile(String key, SourceFileEntry entry, W2AIniFile iniFile) {
		this.key = key;
		this.entry = entry;
		this.ngFile = new NgFile(key, assetsDir, iniFile);
	}
	
	@Override
	public W2AIniFile getXIniFile() {
		return (W2AIniFile)entry.getXIniFile();
	}
	
	@Override
	public NgFile getNgFile() throws Exception {
		return this.ngFile;
	}

	@Override
	public String getHTMLAssetFilename() throws Exception {
		String filename = HTMLEdit.append(this.ngFile.getAssetsDirectory(), key);
		return filename;
	}
	
	/**
	 * input()
	 * 
	 * Read the contents of the Webflow entity via the entity.input();
	 * By default the current underlying implementation depends upon
	 * the Zip4J framework which handles the input and output process.
	 * 
	 * @throws Exception 
	 * 
	 */
	
	@Override
	public void input() throws Exception   {
		try {
			this.entry.input();
		} catch (FeatureNotImplementedException e) {
		}
	}

	/**
	 * output()
	 * 
	 * Read the contents of the Webflow entity via the entity.input();
	 * By default the current underlying implementation depends upon
	 * the Zip4J framework which handles the input and output process.
	 * 
	 */
	
	@Override
	public void output() throws Exception {
		String target = assetsDir+key;
		entry.output(target);
	}
	
	// 
	// Methods below throw FeatureNotRequiredException
	//
	
	@Override
	public void output(String filename) throws Exception {
		String actualFilename = HTMLEdit.append(NgBinaryFile.assetsDir,filename);
		entry.output(actualFilename);
	}

	@Override
	public void input(String filename) throws Exception {
		throw new FeatureNotImplementedException();
	}


}

