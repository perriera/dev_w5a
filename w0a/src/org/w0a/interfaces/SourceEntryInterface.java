package org.w0a.interfaces;

/*
 * SourceEntryInterface
 * 
 * Keeping the general idea of file conversion to a minimum, this is an
 * interface to keep the process of converting a file from one format
 * to another is as few simple methods as possible.
 * 
 * @author perry
 *
 */

public interface SourceEntryInterface  extends SourceInterface, IniFileInterface {
	
	/**
	 * input()
	 * 
	 * Takes a filename as a parameter and populates the object with
	 * the contents of the object which is from a InputStream / File.
	 * 
	 * @param filename
	 * @throws Exception
	 */
	
	void input(String filename) throws Exception;
	
	/**
	 * output() 
	 * 
	 * Takes a filename and outputs the contents of the object to that
	 * particular filename. The object in question can output the contents
	 * of the object to different files.
	 * 
	 * @param filename
	 * @throws Exception
	 */
	
	void output(String filename)  throws Exception;
	
}
