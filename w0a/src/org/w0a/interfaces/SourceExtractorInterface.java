package org.w0a.interfaces;

public interface SourceExtractorInterface extends IniFileInterface {

	void input() throws Exception;
	void output()  throws Exception;
	void prepare() throws Exception;
	void cleanup() throws Exception;
	
}
