package org.w0a;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;

/*
 * class HTMLEdit
 * 
 * The purpose of this class is to provide basic HTML processing 
 * for the w1a framework. Specifically used throughout the Angular1BodyFormat
 * and the Angular1ContentDivFormat classes. Placed here for clarity of code
 * and possible reusability.
 * 
 * (c) Copyright 2017, ExPARX, Inc. 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */



public class HTMLEdit {

	/**
	 * parse()
	 * 
	 * Special version of the JSoup Parser that recognizes case sensitivity.
	 * 
	 * @param html
	 * @return
	 */

	public static Document parse(String html) {
		Parser parser = Parser.htmlParser();
		parser.settings(new ParseSettings(true, true));
		Document doc = parser.parseInput(html, "");
		return doc;
	}
	
	public static String append(String base, String... dirs) {
		String total = base;
		for (String dir : dirs) {
			total = append(total,dir);
		}
		return total;
	}
	
	public static String append(String directory, String filename) {
		if ( directory.endsWith("/") && !filename.startsWith("/") )
			return directory+filename;
		if ( !directory.endsWith("/") && filename.startsWith("/") )
			return directory+filename;	
		if ( !directory.endsWith("/") && !filename.startsWith("/") )
			return directory+"/"+filename;	
		return directory+filename.substring(1);	
	}

	public static String writeDirectory(String directory, String filename) throws IOException {
		return writeDirectory(HTMLEdit.append(directory,filename));
	}
	
	static public String writeDirectory(String fn) throws IOException {
		Path p = Paths.get(fn);
		Path folder = p.getParent();
		if (folder!=null&&!Files.exists(folder)) {
			Files.createDirectories(folder);
		}
		return fn;
	}
	
	@Deprecated
	static public void output(String content, String filename, IniFile iniFile) throws Exception {
		String directory = iniFile.getDestination();
		directory = HTMLEdit.append(directory,filename);
		String fn = HTMLEdit.writeDirectory(directory);
		PrintWriter writer = new PrintWriter(fn, "UTF-8");
		writer.println(content);
		writer.close();
	}
	
	private final static Pattern LTRIM = Pattern.compile("^\\s+");
	private final static Pattern RTRIM = Pattern.compile("\\s+$\",");
	
	public static String ltrimp(String s) {
	    return LTRIM.matcher(s).replaceAll("");
	}
	
	public static String rtrimp(String s) {
	    return RTRIM.matcher(s).replaceAll("");
	}
	
	public static String ltrim(String s) {
	    int i = 0;
	    while (i < s.length() && Character.isWhitespace(s.charAt(i))) {
	        i++;
	    }
	    return s.substring(i);
	}

	public static String rtrim(String s) {
	    int i = s.length()-1;
	    while (i >= 0 && Character.isWhitespace(s.charAt(i))) {
	        i--;
	    }
	    return s.substring(0,i+1);
	}
	
}
