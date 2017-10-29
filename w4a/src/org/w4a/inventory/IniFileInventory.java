package org.w4a.inventory;

import java.io.File;
import java.util.HashMap;

import org.w0a.IniFile;
import org.w4a.exceptions.MissingKeyInUploadedFileException;

@SuppressWarnings("serial")
public class IniFileInventory extends HashMap<String,IniFile> implements InventoryInterface {

	static public String unlockKey(String lockedkey) {
		String[] parts = lockedkey.split("_");
		if ( parts.length>1 )
			return parts[1];
		else
			return parts[0];
	}
	
	static public String getKey(String key) throws MissingKeyInUploadedFileException {
		String[] parts = key.split("_");
		if ( parts.length>1 )
			return parts[0];
		else
			throw new MissingKeyInUploadedFileException(parts[1]);
	}
	
	static public String getKey(File file) throws MissingKeyInUploadedFileException {
		return getKey(file.getName());
	}
	
}
