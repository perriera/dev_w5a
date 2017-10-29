package org.w4a.validators;

import org.w0a.IniFile;
import org.w0a.extractor.SourceExtractor;
import org.w4a.exceptions.MissingZipEntryException;

abstract public class ValidationTools implements ValidateInterface {

	IniFile iniFile;
	
	@Override
	public void delete() throws Exception {
		if ( iniFile!=null )
			iniFile.delete();
	}
	
	void testForNull(Object result, String key) throws MissingZipEntryException {
		if ( result==null)
			throw new MissingZipEntryException(key);
	}
	
	void testForNull(SourceExtractor map, String key) throws MissingZipEntryException {
		testForNull(map.get(key),key);
	}
	
}
