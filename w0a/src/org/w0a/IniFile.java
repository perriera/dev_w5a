package org.w0a;

/*
 * class IniFile
 * 
 * The purpose of this class is to provide a number of typical
 * *.ini operations in one convenient location.
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


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.w0a.interfaces.IniFileInterface;


public class IniFile implements IniFileInterface {

	@SuppressWarnings("serial")
	class SectionNotFoundException extends Exception {
		public SectionNotFoundException(String name) {
			super(name);
		}
	}
	
	protected String filename;

	/**
	 * IniFile
	 * 
	 * Constructs an instance of the IniFile class.
	 * 
	 * @param filename
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws InvalidFileFormatException 
	 */
	
	public IniFile(String  filename) throws InvalidFileFormatException, FileNotFoundException, IOException {
		this.filename = filename;
		Ini ini = new Ini();
		ini.load(new FileReader(new File(filename)));
	}
	
	public IniFile(File zipFile) throws Exception {
		String filename = zipFile.getPath();
		this.setFilename(filename);
		this.setSource(zipFile.getPath());
	}
	
	public IniFile() {
	}
	
	public void delete() {
		if ( new File(this.filename).exists() )
			new File(this.filename).delete();
	}
	
	@Override
	public IniFile getXIniFile() {
		return this;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	/**
	 * String 	getDirective
	 * @param 	sectionId
	 * @param 	itemId
	 * @return 	the value from the IniFile using a sectionId and a itemId.
	 * @throws 	IOException
	 */
	
	public String getDirective(String sectionId, String itemId) throws Exception {
		Ini ini = new Ini();
		ini.load(new FileReader(new File(filename)));
		Ini.Section section = ini.get(sectionId);
		if ( section==null )
			throw new SectionNotFoundException(sectionId);
		String item = section.get(itemId);
		return item;
	}

	public String putDirective(String sectionId, String itemId, String value) throws Exception {
		Ini ini = new Ini();
		String oldValue = "";
		if ( new File(filename).exists() ) {
			ini.load(new FileReader(new File(filename)));
			Ini.Section section = ini.get(sectionId);
			if ( section==null ) {
				// throw new SectionNotFoundException(sectionId);
				ini.add(sectionId,itemId,value);
				section = ini.get(sectionId);
			}
			oldValue = section.get(itemId);
			section.put(itemId, value);
		} else {
			ini.add(sectionId,itemId,value);
		}
		ini.store(new File(this.filename));
		return oldValue;
	}
	
	/**
	 * String 	getSource
	 * @return 	the name of the Webflow ZIP archive to be processed.
	 * @throws 	IOException
	 */
	
	public String getSource() throws Exception {
		String source = getDirective("Common","source");
		if ( source==null ) {
			Path p = Paths.get(filename);
			String file = p.getFileName().toString();
			file = file.replaceFirst("[.][^.]+$", "");
			source = file;	
		}
		return source;
	}

	public String setSource(String newSource) throws Exception {
		String oldValue = putDirective("Common","source",newSource);
		return oldValue;
	}

	/**
	 * boolean 	getSourceFilename
	 * @return	The file name of source archive only.
	 * @throws 	IOException
	 */
	
	public String getSourceFilename() throws Exception {
		String source = getSource();
		String filename = new File(source).getName();
		return filename;
	}
	
	/**
	 * boolean 	getSourceFilename
	 * @return	The name of the default directory inside the Webflow ZIP archive.
	 * @throws 	IOException
	 */
	
	public String getSourceDir() throws Exception {
		String filename = getSourceFilename();
		String sourceDir = filename.replace(".zip", "/");
		return sourceDir;
	}
	
	/**
	 * String 	getDestination
	 * @return 	the directory location where the contents of the WebFlow
	 * 			ZIP archive will be placed.
	 * @throws 	IOException
	 */
	public String getDestination() throws Exception {
		String destination = getDirective("Common","destination");
		if ( destination==null ) {
			Path p = Paths.get(filename);
			String file = p.getFileName().toString();
			file = file.replaceFirst("[.][^.]+$", "");
			destination = file;	
		}
		return destination;
	}

	public String setDestination(String newSource) throws Exception {
		return putDirective("Common","destination",newSource);
	}

	/**
	 * boolean 	getWatchMode
	 * @return	Whether or not w0a is being run in watch mode where the
	 * 			existence of the source file is continually monitored.
	 * @throws 	IOException
	 */
	
	public String[] getIgnoreDir() throws Exception {
		String ignoreDir = getDirective("Common","ignoreDir");
		if ( ignoreDir==null ) {
			ignoreDir = "__MACOSX/";
		}
		String[] dirs = ignoreDir.split(" ");
		return dirs;
	}
	
	public String getIgnoreList() throws Exception {
		String ignoreList = getDirective("Common","ignoreList");
		if ( ignoreList==null ) {
			return ".DS_Store";
		}
		return ignoreList;
	}

	public boolean isIgnored(String key) throws Exception {
		String ignoreList = getIgnoreList();
		return ignoreList.contains(key);
	}
	
	public String getIgnorePolicy() throws Exception {
		String ignorePolicy = getDirective("Common","ignorePolicy");
		if ( ignorePolicy==null ) {
			return "pass";
		}
		return ignorePolicy;
	}
	
	public boolean isSkipIgnoredFiles() throws Exception {
		return getIgnorePolicy().toLowerCase().equals("skip");
	}

	public File getFile() {
		return new File(getFilename());
	}
	
}
