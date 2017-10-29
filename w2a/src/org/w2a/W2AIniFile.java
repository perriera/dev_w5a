package org.w2a;

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


public class W2AIniFile extends org.w1a.W1AIniFile {

	public W2AIniFile(String filename) throws InvalidFileFormatException, FileNotFoundException, IOException {
		super(filename);
	}
	
	public W2AIniFile(File file) throws Exception {
		super(file);
	}
	
	public W2AIniFile() {
		super();
	}

	public String getFormat() throws Exception {
		String format = getDirective("Angular2","format");
		if ( format==null ) {
			return "NgComponent";
		}
		return format;
	}
	
	public String setFormat(String format) throws Exception {
		return putDirective("Angular2","format", format);
	}

	public String getAssetsDir() throws Exception {
		String assetsDir = getDirective("Angular2","assetsDir");
		if ( assetsDir==null ) {
			return "assets/";
		}
		return assetsDir;
	}

	public String getAppDir() throws Exception {
		String appDir = getDirective("Angular2","appDir");
		if ( appDir==null ) {
			return "app/";
		}
		return appDir;
	}

	public String getViewsDir() throws Exception {
		// TODO: put in a proper check on directive format 
		String viewDir = getDirective("Angular2","viewDir");
		if ( viewDir==null ) {
			viewDir = "views/";
		}
		return viewDir;
	}
	
	public String getNodeDir() throws Exception {
		String nodeDir = getDirective("Angular2","nodeDir");
		if ( nodeDir==null ) {
			nodeDir = "/usr/local/bin";
		}
		if ( new File(nodeDir+"/node").exists() ) {
			return nodeDir;
		}
		else {
			nodeDir = "/usr/bin";
			if ( new File(nodeDir+"/node").exists() ) 
				return nodeDir;
			else
				nodeDir = null;
		}
		return nodeDir;
	}
	
	public String getNodeGCCommand(String componentName) throws Exception {
		String nodeGCCommand = null;
		String nodeDir = getNodeDir();
		if ( nodeDir!=null ) {
			nodeGCCommand = nodeDir+"/node "+nodeDir+"/ng g c "+componentName;
		}
		return nodeGCCommand;
	}

	public String getNodeGCCmd() throws Exception {
		String nodeGCCommand = null;
		String nodeDir = getNodeDir();
		if ( nodeDir!=null ) {
			nodeGCCommand = nodeDir+"/node";
		}
		return nodeGCCommand;
	}
	
	public String getNodeGCParams(String componentName) throws Exception {
		String nodeGCCommand = null;
		String nodeDir = getNodeDir();
		if ( nodeDir!=null ) {
			nodeGCCommand = nodeDir+"/ng g c "+componentName;
		}
		return nodeGCCommand;
	}
	
	public boolean getExpandIds() throws Exception {
		String expandIds = getDirective("Angular2","expandIds");
		if ( expandIds==null ) {
			return false;
		}
		return "yes|true".contains(expandIds.toLowerCase().trim());
	}
	
	public boolean getExpandNgs() throws Exception {
		String expandNgs = getDirective("Angular2","expandNgs");
		if ( expandNgs==null ) {
			return false;
		}
		return "yes|true".contains(expandNgs.toLowerCase().trim());
	}

	public String getSymbolsDir() throws Exception {
		// TODO: put in a proper check on directive format 
		String symbolsDir = getDirective("Angular2","symbolsDir");
		if ( symbolsDir==null ) {
			return "symbols";
		}
		return symbolsDir.trim();
	}
	
	public String getSymbolsIdentifier() throws Exception {
		String symbolsIdentifier = getDirective("Angular2","symbolsIdentifier");
		if ( symbolsIdentifier==null ) {
			return "symbols";
		}
		return symbolsIdentifier.trim();
	}

	public boolean forwardGenerateAppRoutesOnly() throws Exception {
		String forwardGenerateAppRoutesOnly = getDirective("Angular2","forwardGenerateAppRoutesOnly");
		if ( forwardGenerateAppRoutesOnly==null ) {
			return true;
		}
		return "yes|true".contains(forwardGenerateAppRoutesOnly.toLowerCase().trim());
	}

	public void setForwardGenerateAppRoutesOnly(boolean b) throws Exception {
		putDirective("Angular2","forwardGenerateAppRoutesOnly",b?"true":"false");
	}
	
	public String getMorphIdentifier() throws Exception {
		String morphIdentifier = getDirective("Angular2","morphIdentifier");
		if ( morphIdentifier==null ) {
			return "ng-morph";
		}
		return morphIdentifier.trim();
	}
	
	public boolean replaceMongooseImport() throws Exception {
		String replaceMongooseImport = getDirective("NgImport","replaceMongooseImport");
		if ( replaceMongooseImport==null ) {
			return false;
		}
		return "yes|true".contains(replaceMongooseImport.toLowerCase().trim());
	}
	
	public String getReplaceMongooseFrom() throws Exception {
		String replaceMongooseFrom = getDirective("NgImport","replaceMongooseFrom");
		if ( replaceMongooseFrom==null ) {
			return "import { Document } from 'mongoose';";
		}
		return replaceMongooseFrom.trim();
	}
	
	public String getReplaceMongooseTo() throws Exception {
		String replaceMongooseTo = getDirective("NgImport","replaceMongooseTo");
		if ( replaceMongooseTo==null ) {
			return "export interface Document { _id: string; }";
		}
		return replaceMongooseTo.trim();
	}

	public boolean preserveWebflowExtensions() throws Exception {
		String preserveWebflowExtensions = getDirective("Angular2","preserveWebflowExtensions");
		if ( preserveWebflowExtensions==null ) {
			return false;
		}
		return "yes|true".contains(preserveWebflowExtensions.toLowerCase().trim());
	}
	 
	public String[] getReservedAttibutes() throws Exception {
		String reservedAttibutes = getDirective("Angular2","reservedAttibutes");
		if ( reservedAttibutes==null ) {
			String[] defaultReservedAttibutes = { "routerLink" };
			return defaultReservedAttibutes;
		}
		return reservedAttibutes.split(" ");
	}

	public boolean getNonCli() throws Exception {
		String nonCli = getDirective("Angular2","nonCli");
		if ( nonCli==null ) {
			return false;
		}
		return "yes|true".contains(nonCli.toLowerCase().trim());
	}
	
	public boolean setNonCli(boolean state) throws Exception {
		String text = ( state ? "true" : "false");
		boolean oldState = getNonCli();
		putDirective("Angular2","nonCli",text);
		return oldState;
	}

	public boolean getNonCliTrainerActive(boolean b) throws Exception {
		String nonCliTrainerActive = getDirective("Angular2","nonCliTrainerActive");
		if ( nonCliTrainerActive==null ) {
			return false;
		}
		return "yes|true".contains(nonCliTrainerActive.toLowerCase().trim());
	}
	
	public boolean setNonCliTrainerActive(boolean state) throws Exception {
		String text = ( state ? "true" : "false");
		boolean oldState = getNonCli();
		putDirective("Angular2","nonCliTrainerActive",text);
		return oldState;
	}

	public String getExternalDir() throws Exception {
		String externalDir = getDirective("Angular2","externalDir");
		if ( externalDir==null ) {
			return "external/";
		}
		return externalDir.trim();
	}
	
}
