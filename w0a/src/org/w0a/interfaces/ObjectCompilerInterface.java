package org.w0a.interfaces;

public interface ObjectCompilerInterface<T> {

	/**
	 * input()
	 * 
	 * Takes no parameter and is expected to operate on contents available
	 * to the object given to it during construction.
	 * 
	 * @throws Exception
	 */
	
	void input(T before) throws Exception;
	
	/**
	 * output()
	 * 
	 * Takes no parameter and is expected to operate on contents available
	 * to the object given to it during construction.
	 * 
	 * @throws Exception
	 */
	
	T output() throws Exception;
	
}
