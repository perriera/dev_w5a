package org.w1a;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.ini4j.InvalidFileFormatException;

/*
 * class W1AIniFile
 * 
 * The purpose of this class is to provide options specific to
 * Angular 1 formatting parameters via an ini file for the project.
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



public class W1AIniFile extends org.w0a.IniFile {

	public W1AIniFile(String filename) throws InvalidFileFormatException, FileNotFoundException, IOException {
		super(filename);
	}
	
	public W1AIniFile(File file) throws Exception {
		super(file);
	}
	
	public W1AIniFile() {
	}
	
	public String getRoutesFile() throws Exception {
		return getDirective("Angular1","routesFile");
	}

	public String getContentDiv() throws Exception {
		String contentDiv = getDirective("Angular1","contentDiv");
		if ( contentDiv==null ) {
			return null;
		}
		return contentDiv;
	}


}
