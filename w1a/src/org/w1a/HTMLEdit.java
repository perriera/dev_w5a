package org.w1a;

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

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Vector;


public class HTMLEdit extends org.w0a.HTMLEdit {

	static String[] readFile(InputStream is) throws IOException {
		Vector<String> lines = new Vector<String>();
		String line = null;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		while ((line = bufferedReader.readLine()) != null) {
			lines.add(line);
		}
		bufferedReader.close();
		String[] array = new String[lines.size()];
		return lines.toArray(array);
	}
	
	static String[] remove(String[] buffer, int index) {
		String[] newBuffer = new String[buffer.length - 1];
		int c = 0;
		for (int i = 0; i < buffer.length; i++) {
			if (i != index)
				newBuffer[c++] = buffer[i];
		}
		return newBuffer;
	}

	public static String[] readFile(String text) throws IOException {
		InputStream stream = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
		return readFile(stream);
	}
	
	static String writeFile(String[] array) throws IOException {
		StringBuffer buffer = new StringBuffer();
		for (String line : array) {
			buffer.append(line);
			buffer.append("\n");
		}
		return buffer.toString();
	}
	
	static String[] removeFirst(String[] buffer) {
		return remove(buffer, 0);
	}

	static String[] removeLast(String[] buffer) {
		return remove(buffer, buffer.length - 1);
	}
//
//	static String[] stripFirstAndLast(String[] buffer) {
//		return removeFirst(removeLast(buffer));
//	}
//
	public static String stripFirstAndLast(String text) throws IOException {
		if ( text.length()==0 || text.equals("\n") )
			return text;
		String[] buffer = readFile(text);
		buffer = removeFirst(removeLast(buffer));
		return writeFile(buffer);
	}
	
	public static String stripScriptTags(String edited) throws IOException {
		String[] buffer = readFile(edited);
		int cnt = 0;
		for (String line: buffer) {
			if ( line.contains("<script") )
				cnt++;
		}
		String[] editedBuffer = new String[buffer.length-cnt];
		int i=0;
		for (String line: buffer) {
			if ( !line.contains("<script") )
				editedBuffer[i++] = line;
		}
		return writeFile(editedBuffer);
	}
	
	public static String fixPageLink(String line) throws IOException {
		int a = line.indexOf("href=\"../");
		int b = line.indexOf(".html");
		String link = line.substring(a + 6, b);
		String pre = line.substring(0, a + 6);
		String post = line.substring(b + 5);
		if (link.equals("../index"))
			line = pre + "#/" + post;
		else {
			String[] parts = link.split("/");
			link = parts[parts.length - 1];
			line = pre + "#/" + link + post;
		}
		return line;
	}

	public static String fixIndexLink(String line) throws IOException {
		int a = line.indexOf("href=\"");
		int b = line.indexOf(".html");
		if ( b<0 )
			return line;
		String link = line.substring(a + 6, b);
		String pre = line.substring(0, a + 6);
		String post = line.substring(b + 5);
		if (link.equals("index"))
			line = pre + "#/" + post;
		else {
			String[] parts = link.split("/");
			link = parts[parts.length - 1];
			line = pre + "#/" + link + post;
		}
		return line;
	}
	
	public static String convertLinks(String edited) throws IOException {
		String[] buffer = HTMLEdit.readFile(edited);
		int i = 0;
		String[] editedBuffer = new String[buffer.length];
		for (String line : buffer) {
			if (line.contains("<a ")) {
				if (line.contains("href=\"../"))
					line = fixPageLink(line);
				else if (line.contains("href=\"") && !line.contains("href=\"http"))
					line = fixIndexLink(line);
			}
			editedBuffer[i++] = line;
		}
		return HTMLEdit.writeFile(editedBuffer);
	}
	

	
	public static String getLink(String link) throws IOException {
		String[] parts = link.split("/");
		link = parts[parts.length - 1];
		parts = link.split("\\.");
		link = parts[0];
		return link;
	}
	
	public static String stripSpaces(String text) {
		StringBuffer sb = new StringBuffer();
		boolean skipMode = false;
		for ( char c : text.toCharArray() ) {
			if ( c != ' ' ) 
				skipMode = false;
			if ( c == '\n' ) 
				skipMode = true;
			if ( c == ' ' && skipMode )
				;
			else
				sb.append(c);
		}
		String test = sb.toString();
		return stripTrailingSpaces(test);
	}
	
	public static String stripTrailingSpaces(String text) {
		String[] src = text.split("\n");
		StringBuffer buf = new StringBuffer();
		for ( String s : src ) {
			buf.append(s.trim()+"\n");
		}
		return buf.toString();
	}
//
//	public static String replaceDiv(String content, String div, String app_div) throws IOException {
//		content = HTMLEdit.stripSpaces(content);
//		div = HTMLEdit.stripSpaces(div);
//		app_div = HTMLEdit.stripSpaces(app_div);
//		content = content.replace(div, app_div);
//		Document doc = Jsoup.parse(content);
//		Element body = doc.body();
//		content = body.toString();
//		content = HTMLEdit.stripFirstAndLast(content);
//		return content;
//	}
//	
//	public static Document parse(String html) {
////		Parser parser = Parser.htmlParser();
////		parser.settings(new ParseSettings(true, true)); // tag, attribute preserve case
////		Document doc = parser.parseInput(html, "");
//		Document doc = Jsoup.parse(html);
//		return doc;
//	}
}
