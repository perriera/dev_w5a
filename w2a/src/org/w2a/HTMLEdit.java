package org.w2a;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.exceptions.JSONException;
import org.json.exceptions.JSONKeyNotFoundException;
import org.jsoup.nodes.Document;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.w2a.exceptions.NoTokenFoundInFilenameException;

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

public class HTMLEdit extends org.w1a.HTMLEdit {

	public static String loadLocalFile(String filename) throws Exception {
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String textFile = "";
		String line = "";
		while ((line = br.readLine()) != null) {
			textFile += line + "\n";
		}
		br.close();
		fr.close();
		return textFile;
	}

	public static String ensureAppendage(String text, String string) {
		if (!text.contains(string)) {
			text = text + string;
		}
		return text;
	}

	public static String ensureReplacement(String text, String string, String string2) {
		if (!text.contains(string2)) {
			text = text.replace(string, string2);
		}
		return text;
	}

	public static String doubleEnsureReplacement(String text, String marker, String pattern, String replacement) {
		if (!text.contains(pattern)) {
			text = text.replace(marker, replacement);
		}
		return text;
	}

	public static String ensureInside(String text, String before, String during, String after) throws IOException {
		String result = HTMLEdit.stripFirstAndLast(text);
		result = ensureAppendage(result, "\t" + during);
		result = before + "\n" + result + "\n" + after;
		return result;
	}

	public static String getJsonFromNgModule(String text) throws IOException {
		String result = text.replace("{\n}", "{}");
		result = HTMLEdit.stripFirstAndLast("\n"+result);
		result = result.replace("@NgModule(", "").trim();
		return result;
	}

    static void checkBrackets(String t) throws JSONException {
    	int count = 0;
    	for (int i=0; i<t.length(); i++) {
    		if ( t.charAt(i) == '[' )
    			count++;
    		if ( t.charAt(i) == ']' )
    			count--;
    		if ( t.charAt(i) == '{' )
    			count++;
    		if ( t.charAt(i) == '}' )
    			count--;
    	}
    	if (count!=0)
    		throw new JSONException("Poorly formed JSON expression: "+t);
    }
    
	public static String[] getJSONArray(String json, String identifier) throws JSONException {
		checkBrackets(json);
		JSONObject obj = new JSONObject(json);
		try {
			JSONArray array = obj.getJSONArray(identifier);
			String[] values = new String[array.length()];
			for (int i = 0; i < array.length(); i++) {
				values[i] = array.getObject(i);
			}
			return values;
		} catch (JSONKeyNotFoundException e) {
			return new String[0];
		}
	}

	public static HashMap<String,String> getJSONMap(String json, String identifier) {
		checkBrackets(json);
		JSONObject obj = new JSONObject(json);
		HashMap<String,String> hashMap = new HashMap<String,String>();
		try {
			JSONArray array = obj.getJSONArray(identifier);
			for (int i = 0; i < array.length(); i++) {
				String value = array.getObject(i);
				hashMap.put(value, value);
			}
		} catch (JSONKeyNotFoundException e) {
		}
		return hashMap;
	}
	
	public static String formatJSONArray(String[] values, String name) {
		Arrays.sort(values);
		String out = "  "+name+": [\n";
		for ( String value: values) 
			out += ( "    "+value+",\n" );
		out += "  ],\n";
		return out;
	}
	
	public static String formatNgModule(String base, String... sections) {
		String total = base;
		for (String section : sections) {
			total += section;
		}
		total = "@NgModule({\n  " + (total.trim()) + "\n})\n";
		return total;
	}

	public static boolean hasOccurances(String output, String pattern, int n) {
		String[] count = output.split(pattern);
		return count.length==n+1;
	}

	public static String formatJSONMap(HashMap<String, String> map, String name) {
		String[] values = new String[map.size()];
		map.values().toArray(values);
		return formatJSONArray(values,name);
	}

	public static String formatNgModuleMap(String output) {
		String total = "@NgModule({\n  " + (output) + "\n})\n";
		return total;
	}

	public static String removeLinesContaining(String text, String... patterns) {
		String[] lines = text.split("\n");
		String result = "";
		for (String line : lines ) {
			boolean add = true;
			for (String pattern : patterns) {
				 if ( line.contains(pattern) )
					 add = false;
			}
			if ( add )
				result += line+"\n";
		}
		return result;
	}

	public static String getTokenFromFilename(String filename) throws NoTokenFoundInFilenameException {
		File file = new File(filename);
		String[] parts1 = file.getName().split("-");
		if ( parts1.length>1 ) {
			String[] parts2 = parts1[1].split("_");
			if ( parts2.length>1 ) {
				String path = file.getPath();
				String name = file.getName();
				path = path.replace(name, "");
				String fn = path + parts2[0];
				return fn;
			}
		}
		throw new NoTokenFoundInFilenameException(filename);
	}
	
	public static String getOutput(InputStream inputStream) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		String lastLine = null;
		String line = null;
		while ((line = in.readLine()) != null) {
			lastLine = line;
			System.out.println(line);
		}
		in.close();
		return lastLine;
	}
	
}
