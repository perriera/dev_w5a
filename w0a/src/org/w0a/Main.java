package org.w0a;

/*
 * class Main
 * 
 * The purpose of this class is to provide an instance of the
 * WebflowArchive class via command line parameters to an ini file.
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
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

import org.w0a.exceptions.FailedToDeleteException;
import org.w0a.exceptions.NgExceptions;
import org.w0a.interfaces.IniFileInterface;
import org.w0a.interfaces.SourceExtractorInterface;
import org.w0a.webflow.WebflowExtractor;

import net.lingala.zip4j.core.ZipFile;


public class Main implements IniFileInterface {

	/*
	 * Custom Exceptions
	 */

	@SuppressWarnings("serial")
	public class UnknownArgumentException extends IOException {
	}

	/*
	 * Members
	 */

	String[] args;
	public String iniFilename;
	protected IniFile iniFile;
	ZipFile zipFile;
	long startTime;
	long endTime;
	protected long timestamp1;
	protected long timestamp2;
	protected boolean watchMode;

	/*
	 * Construct
	 */

	public Main(String[] args) throws Exception {
		this(args, new IniFile(args[0]));
	}

	public Main(String[] args, IniFile iniFile) throws Exception {
		this.args = args;
		this.iniFile = iniFile;
		this.watchMode = args.length>1&&args[1].equals("-w");
		String zipFilename = iniFile.getSource();
		zipFile = new ZipFile(zipFilename);
		this.iniFilename = args[0];
	}

	public Main(IniFile iniFile, boolean watchMode) throws Exception {
		this.iniFile = iniFile;
		this.watchMode = watchMode;
		String zipFilename = iniFile.getSource();
		zipFile = new ZipFile(zipFilename);
		this.iniFilename = iniFile.getFilename();
	}

	@Override
	public IniFile getXIniFile() {
		return iniFile;
	}

	/*
	 * Properties / WebflowInterface implementation
	 */

	public String getAppIdentifier() {
		return "/* "+"W2A (Webflow to Angular) Framework: "+this.getClass().getName()+"generated file */";
	}
	
	/**
	 * (non-Javadoc)
	 * @see org.wya.WebflowInterface#println(java.lang.String)
	 */
	
	public synchronized void println(String text) {
		System.out.println(text);
	}
	
	public synchronized void printError(String text) {
		System.err.println(text);
	}

	/**
	 * (non-Javadoc)
	 * @see org.wya.WebflowInterface#print(java.lang.String)
	 */
	
	public void print(String text) {
		System.out.print(text);
	}

	/*
	 * Methods
	 */
	
	/**
	 * void showStart()
	 * 
	 * Display the name of the utility running and the version info.
	 * Derived classes should only specify the name and the version of
	 * the derived class in the constructor.
	 * 
	 */
	
	protected void showStart() {
		println(this.getClass().getName());
	}

	/**
	 * void showHelp()
	 * 
	 * Displays help on the various parameters that are currently
	 * in use for this framework. Should be overridden by derived
	 * classes to indicate specialized functionality.
	 * 
	 */
	
	protected void showHelp() {
		println("");
		println("-i\tThe ini file to use. ");
		println("  \tex. -i sample.ini");
		println("");
	}

	/**
	 * void showEnd()
	 * 
	 * Displays the successful execution of the framework.
	 * 
	 */
	
	protected void showEnd() {
		println("Terminated normally");
	}

	/**
	 * void showFail()
	 * 
	 * Displays any exceptions encountered during a failed
	 * execution of the framework.
	 * 
	 */
	
	protected void showFail(Exception e) {
		println("Terminated abnormally");
		println(e.getMessage());
		e.printStackTrace();
	}

	/**
	 * void startTime()
	 * 
	 * Displays the start time of a Webflow ZIP compile.
	 * 
	 */
	
	protected void startTime() {
		Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
		startTime = timestamp1.getTime();
		println("Started: " + timestamp1.getTime());
	}

	/**
	 * void endTime()
	 * 
	 * Displays the end time of a Webflow ZIP compile.
	 * 
	 */
	
	protected void endTime() {
		Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
		endTime = timestamp2.getTime();
		println("End: " + timestamp2.getTime());
		println("Diff: " + ((endTime - startTime) / 1000.0));
	}

	/**
	 * void getFormatter()
	 * 
	 * Determines the WebflowArchive object to be used. This method should
	 * be overridden to allow Main to utilize specialized WebflowArchive
	 * derivatives.
	 * 
	 */
	
	public synchronized SourceExtractorInterface getSourceFormatter() throws Exception {
		return new WebflowExtractor(this.getXIniFile());
	}
	
	/**
	 * void execute()
	 * 
	 * Carries out the process of converting a WebFlow ZIP archive into
	 * it's HTML format.
	 * @throws Exception 
	 * 
	 */
	
	public void execute() throws Exception {
		SourceExtractorInterface formatter = null;
		try {
			println("\nFound new " + getXIniFile().getSource());
			showStart();
			formatter = getSourceFormatter();
			if (formatter != null) {
				formatter.prepare();
				startTime();
				formatter.input();
				endTime();
				startTime();
				formatter.output();
				endTime();
			}
		} catch (UnknownArgumentException e) {
			showHelp();
		} catch (NgExceptions e) {
			printError("\n"+e.getIssue()+"\n");
		} catch (Exception e) {
			showFail(e);
			e.printStackTrace();
		}
		finally {
			showEnd();
			if (this.watchMode) {
				try {
					formatter.cleanup();
				} catch (FailedToDeleteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * void monitorSource()
	 * 
	 * Constructs a TimerTask object to monitor the iniFile.getSource()
	 * for the appearance of the iniFile.getSource() file. When present
	 * it calls the execute() method.
	 * @throws Exception 
	 * 
	 */
	
	protected void printStartup() throws Exception {
		showStart();
		println("Processing: "+this.iniFilename);
		println("Source file: "+iniFile.getSource());
		println("Desination directory: "+iniFile.getDestination());
		print("Watch mode (ctrl-c to quit) ... ");
	}
	
	public void monitorSource() throws Exception {

		printStartup();

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			
			File f2 = new File(iniFilename);
			
			@Override
			public void run() {
				try {
					File f1 = new File(iniFile.getSource());
					if (f1.exists() && f1.lastModified() != timestamp1) {
						timestamp1 = f1.lastModified();
						execute();
						printStartup();
					}
					if (f2.exists() && f2.lastModified() != timestamp2) {
						timestamp2 = f2.lastModified();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		};

		timer.schedule(task, 1000, 1000);

	}

	/**
	 * void process()
	 * 
	 * Determines if Main should run in Watch Mode or only
	 * for a single execution.
	 * 
	 */
	
	public void process() throws Exception {
		if (this.watchMode) {
			this.monitorSource();
		} else {
			this.execute();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}