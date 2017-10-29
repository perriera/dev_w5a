package org.w3a;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.ini4j.InvalidFileFormatException;
import org.w0a.HTMLEdit;

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


public class W3AIniFile extends org.w2a.W2AIniFile {

	public W3AIniFile(String filename) throws InvalidFileFormatException, FileNotFoundException, IOException {
		super(filename);
	}

	public String getUpLoadsDir() throws Exception {
		String upLoadsDir = getDirective("Monitor","upLoadsDir");
		if ( upLoadsDir==null ) {
			Path p = Paths.get(filename);
			String file = p.getFileName().toString();
			file = file.replaceFirst("[.][^.]+$", "");
			upLoadsDir = file;	
		}
		return upLoadsDir;
	}
	
	public String getDownLoadsDir() throws Exception {
		String downLoadsDir = getDirective("Monitor","downLoadsDir");
		if ( downLoadsDir==null ) {
			Path p = Paths.get(filename);
			String file = p.getFileName().toString();
			file = file.replaceFirst("[.][^.]+$", "");
			downLoadsDir = file;	
		}
		return downLoadsDir;
	}

	public String getProcessingDir() throws Exception {
		String downLoadsDir = getDirective("Monitor","processingDir");
		if ( downLoadsDir==null ) {
			Path p = Paths.get(filename);
			String file = p.getFileName().toString();
			file = file.replaceFirst("[.][^.]+$", "");
			downLoadsDir = file;	
		}
		return downLoadsDir;
	}
	
	public String getIniFileRepositoryDir() throws Exception {
		String iniFileRepositoryDir = getDirective("Monitor","iniFileRepositoryDir");
		if ( iniFileRepositoryDir==null ) {
			Path p = Paths.get(filename);
			String file = p.getFileName().toString();
			file = file.replaceFirst("[.][^.]+$", "");
			iniFileRepositoryDir = file;	
		}
		return iniFileRepositoryDir;
	}

	public String getIniFileRepositoryName(File zipFile) throws Exception {
		String directory = getIniFileRepositoryDir();
		String filename = zipFile.getName();
		filename = filename.replace(".zip", ".ini");
		String fullFilename = HTMLEdit.append(directory, filename);
		return fullFilename;
	}

	public String getDownloadsArchiveName(File zipFile)  throws Exception {
		String directory = getDownLoadsDir();
		String filename = zipFile.getName();
		String fullFilename = HTMLEdit.append(directory, filename);
		return fullFilename;
	}

	public String getUpLoadsArchiveName(File zipFile) throws Exception {
		String directory = getUpLoadsDir();
		String filename = zipFile.getName();
		String fullFilename = HTMLEdit.append(directory, filename);
		return fullFilename;
	}

	public String setPreviousVersion(String previously) throws Exception {
		String oldValue = putDirective("Common","previously",previously);
		return oldValue;
	}
	
	public String getPreviousVersion() throws Exception {
		String oldValue = getDirective("Common","previously");
		return oldValue;
	}
	
	public boolean hasPreviousVersion() throws Exception {
		String oldValue = getDirective("Common","previously");
		return oldValue != null;
	}
	
}
