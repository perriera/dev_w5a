package org.w2a;

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

import org.w0a.interfaces.SourceExtractorInterface;
import org.w0a.webflow.WebflowExtractor;
import org.w2a.exceptions.UnknownFormatException;
import org.w2a.ngcomponent.NgComponentExtractor;
import org.w2a.ngexport.NgExportAngular2CLISharedFolderFormat;
import org.w2a.ngimport.NgImportNodeJSFormat;
import org.w2a.ngmodule.NgModuleCompilerLoader;

public class Main extends org.w0a.Main {

	/*
	 * Properties / WebflowInterface implementation
	 */

	public Main(String[] args, W2AIniFile iniFile) throws Exception {
		super(args, iniFile);
	}

	public Main(String[] args) throws Exception {
		this(args, new W2AIniFile(args[0]));
	}

	public Main(W2AIniFile w2aIniFile, boolean watchMode) throws Exception {
		super(w2aIniFile,watchMode);
	}

	@Override
	public W2AIniFile getXIniFile() {
		return (W2AIniFile) iniFile;
	}

	/**
	 * void getFormatter()
	 * 
	 * Determines the WebflowArchive object to be used. This method should be
	 * overridden to allow Main to utilize specialized WebflowArchive
	 * derivatives.
	 * 
	 */

	public synchronized SourceExtractorInterface getSourceFormatter() throws Exception {
		String[] formats = { "NgComponentFormat", "NgExportFormat" };
		String format = getXIniFile().getFormat();
		int c = 0;
		for (String test : formats) {
			if (test.equals(format))
				c++;
		}
		switch (c) {
		case 0:
			return new NgModuleCompilerLoader(new NgComponentExtractor(new WebflowExtractor(getXIniFile())));
		case 1:
			return new NgExportAngular2CLISharedFolderFormat(new NgImportNodeJSFormat(getXIniFile()));
		default:
			throw new UnknownFormatException(format);
		}
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