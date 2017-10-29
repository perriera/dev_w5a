package org.w1a;

/*
 * class Main
 * 
 * The purpose of this class is to provide an instance of the
 * Angular1BodyFormat and Angular1ContentDivFormat class via 
 * command line parameters to an ini file.
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

import java.io.IOException;

import org.w0a.extractor.SourceExtractor;
import org.w0a.webflow.WebflowExtractor;
import org.w1a.formats.Angular1BodyFormat;
import org.w1a.formats.Angular1ContentFormat;


public class Main extends org.w0a.Main  {
	 
	/*
	 * Properties / WebflowInterface implementation
	 */

	public Main(String[] args, W1AIniFile iniFile) throws Exception {
		super(args, iniFile);
	}
	
	public Main(String[] args) throws Exception {
		this(args, new W1AIniFile(args[0]));
	}

	@Override
	public W1AIniFile getXIniFile() {
		return (W1AIniFile)iniFile;
	}
	
	/**
	 * void getFormatter()
	 * 
	 * Determines the WebflowArchive object to be used. This method should
	 * be overridden to allow Main to utilize specialized WebflowArchive
	 * derivatives.
	 * 
	 */
	
	public synchronized SourceExtractor getSourceFormatter() throws Exception {
		if ( getXIniFile().getContentDiv()!=null )
			return new Angular1ContentFormat(new WebflowExtractor(getXIniFile()));
		else
			return new Angular1BodyFormat(new WebflowExtractor(getXIniFile()));
	}
	

	/**
	 * void main()
	 * 
	 * Standard main() construct.
	 * 
	 */
	
	public static void main(String[] args) {

		try {
			Main main = new Main(args);
			main.process();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}