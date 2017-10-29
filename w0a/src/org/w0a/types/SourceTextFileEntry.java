package org.w0a.types;

import java.io.InputStream;

import org.w0a.interfaces.TextContentsInterface;

public interface SourceTextFileEntry extends SourceFileEntry, TextContentsInterface {

	void input(String filename) throws Exception;
	void input(InputStream is) throws Exception;
	
}
