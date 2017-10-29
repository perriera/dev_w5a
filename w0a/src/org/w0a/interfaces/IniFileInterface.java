package org.w0a.interfaces;

import org.w0a.IniFile;

/*
 * IniFileInterface
 * 
 * The IniFile, which differs for each succeeding implementation of w?a, is responsible
 * to maintain a standard interface by which the converted can access necessary
 * parameters.
 * 
 * @author perry
 *
 */

public interface IniFileInterface {

	/**
	 * getIniFile()
	 * 
	 * @return the IniFile object
	 * 
	 */
	
	IniFile getXIniFile();
	
}
