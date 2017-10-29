package org.w0a.extractor;

import java.io.IOException;
import org.w0a.interfaces.SourceEntryInterface;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("serial")
public class SourceEntryMap extends  SourceMap<SourceEntryInterface> {
	
	/**
	 * boolean isHtmlFile
	 * 
	 * Determines is a file is a HTML file by checking the extension of the
	 * filename.
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */

	static public boolean isHtmlFile(String filename) throws IOException {
		String[] textExtensions = { ".html", ".xhtml", ".htm" };
		for (int i = 0; i < textExtensions.length; i++)
			if (filename.contains(textExtensions[i]))
				return true;
		return false;
	}

	/**
	 * boolean isTextFile
	 * 
	 * Determines if a file is a text file by checking the extension of the
	 * filename. HTML, CSS, JS files are also text files and will return 
	 * a true with this test.
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */

	static public boolean isTextFile(String filename) throws IOException {
		String[] textExtensions = { ".txt", ".css", ".html", ".xhtml", ".js", ".htm" };
		for (int i = 0; i < textExtensions.length; i++)
			if (filename.contains(textExtensions[i]))
				return true;
		return false;
	}

	/**
	 * boolean isIndexFile
	 * 
	 * Determines if a file is the HTML index.html by checking the name of the
	 * file itself.
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */

	static public boolean isIndexFile(String filename) throws IOException {
		return filename.equals("index.html");
	}

	/**
	 * boolean isCSSFile
	 * 
	 * Determines if a file is a CSS file by checking the extension of the
	 * filename. 
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	
	static public boolean isCSSFile(String filename) throws IOException {
		String[] textExtensions = { ".css" };
		for (int i = 0; i < textExtensions.length; i++)
			if (filename.contains(textExtensions[i]))
				return true;
		return false;
	}
	
	/**
	 * boolean isJSFile
	 * 
	 * Determines if a file is a JavaScript file by checking the extension of the
	 * filename. 
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */

	public static boolean isJSFile(String filename) throws IOException {
		String[] textExtensions = { ".js" };
		for (int i = 0; i < textExtensions.length; i++)
			if (filename.contains(textExtensions[i]))
				return true;
		return false;
	}

	/**
	 * boolean isImageFile
	 * 
	 * Determines if a file is a image file by checking the extension of the
	 * filename.  Original code from package com.mkyong.regex;
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */

    private static final String IMAGE_PATTERN =  "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";
	private static Pattern pattern = Pattern.compile(IMAGE_PATTERN);

	public static boolean isImageFile(String filename) throws IOException {
		Matcher matcher = pattern.matcher(filename);
		return matcher.matches();
	}

	public static boolean isDirectoryFile(String fileName) {
		return fileName.endsWith("/");
	}
	
}
