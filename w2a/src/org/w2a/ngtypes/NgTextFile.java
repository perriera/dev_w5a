package org.w2a.ngtypes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import org.w0a.IniFile;
import org.w2a.HTMLEdit;
import org.w2a.ngfile.NgFileInterface;

public class NgTextFile  {

	/**
	 * output()
	 * 
	 * Standard text file output utility.
	 * 
	 * @param content
	 * @param filename
	 * @throws Exception
	 */
	
	static public void output(String content, String filename) throws Exception {
		String fn = HTMLEdit.writeDirectory(filename);
		PrintWriter writer = new PrintWriter(fn, "UTF-8");
		writer.println(content);
		writer.close();
	}
	
	static public void output(String content, NgFileInterface ngFileInterface) throws Exception {
		String fn = HTMLEdit.writeDirectory(ngFileInterface.getHTMLAssetFilename());
		PrintWriter writer = new PrintWriter(fn, "UTF-8");
		writer.println(content);
		writer.close();
	}

	/**
	 * input(filename)
	 * 
	 * Standard text file input utility.
	 * 
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	
	public static String input(String filename) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String content = "";
		String sCurrentLine;
		while ((sCurrentLine = br.readLine()) != null) {
			content += sCurrentLine + "\n";
		}
		br.close();
		return content;
	} 
	
	/**
	 * input( filename, iniFile)
	 * 
	 * Standard text file input utility with parameters.
	 * 
	 * @param filename
	 * @param iniFile
	 * @return
	 * @throws Exception
	 */
	
	static public String input(String filename, IniFile iniFile) throws Exception {
		String fn = HTMLEdit.writeDirectory(filename);
		if ( !new File(fn).exists() )
			return "";
		return input(filename);
	} 


}

