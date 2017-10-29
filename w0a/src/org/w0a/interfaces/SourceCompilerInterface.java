package org.w0a.interfaces;

public interface SourceCompilerInterface {

	/**
	 * input()
	 * 
	 * Takes no parameter and is expected to operate on contents available
	 * to the object given to it during construction.
	 * 
	 * @throws Exception
	 */
	
	void input(String before) throws Exception;
	
	/**
	 * output()
	 * 
	 * Takes no parameter and is expected to operate on contents available
	 * to the object given to it during construction.
	 * 
	 * @throws Exception
	 */
	
	String output() throws Exception;
	
}
