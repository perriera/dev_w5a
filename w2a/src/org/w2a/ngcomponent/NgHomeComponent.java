package org.w2a.ngcomponent;

import org.w0a.types.SourceIndexFileEntry;
import org.w2a.W2AIniFile;

/*
 * Wake up #37 . . . 
 */

public class NgHomeComponent extends NgPageComponent implements SourceIndexFileEntry {

	SourceIndexFileEntry entry;
	
	public NgHomeComponent(String key, SourceIndexFileEntry entry, W2AIniFile iniFile) throws Exception {
		super("home.html",entry,iniFile, null);
		this.entry = entry;
	}
	
}
