package org.w3a;

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
import java.util.Timer;
import java.util.TimerTask;
import java.io.File;

import org.w0a.IniFile;
import org.w1a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.ngfile.NgFile;
import org.w2a.ngtypes.NgTextFile;
import org.w3a.exceptions.IniFileMissingException;
import org.w3a.exceptions.UnableToMoveNodeModulesFolderException;
import org.w3a.exceptions.W3AException;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class Main extends org.w2a.Main {

	/*
	 * Properties / WebflowInterface implementation
	 */

	public Main(String[] args, W3AIniFile iniFile) throws Exception {
		super(args, iniFile);
	}

	public Main(String[] args) throws Exception {
		this(args, new W3AIniFile(args[0]));
	}

	//@Override
	public W3AIniFile getIniFile() {
		return (W3AIniFile) iniFile;
	}

	/**
	 * void execute()
	 * 
	 * Carries out the process of converting a WebFlow ZIP archive into it's
	 * HTML format.
	 * 
	 * @throws Exception
	 * 
	 */

	public String findIniFile(File zipFile) throws Exception {
		String iniFilename = this.getIniFile().getIniFileRepositoryName(zipFile);
		if (!new File(iniFilename).exists())
			throw new IniFileMissingException(iniFilename);
		return iniFilename;
	}

	boolean isFileProcessed(File zipFile) throws Exception {
		String repository = this.getIniFile().getDownloadsArchiveName(zipFile);
		return new File(repository).exists();
	}

	protected IniFile getSkeletonIniFile(File webFlowZipFile) throws Exception {
		String targetDir = webFlowZipFile.getName();
		targetDir = targetDir.replace(".zip", "");
		String skeletonProject = "skeleton.zip";
		String processingDir = this.getIniFile().getProcessingDir();
		IniFile iniFile = new IniFile("files/skeleton.ini");
		skeletonProject = "files/" + skeletonProject;
		iniFile.setSource(skeletonProject);
		String destinationDir = processingDir + "/" + targetDir;
		iniFile.setDestination(destinationDir);
		return iniFile;
	}
	
	protected IniFile getPreviousVersionIniFile(File webFlowZipFile) throws Exception {
		String targetDir = webFlowZipFile.getName();
		targetDir = targetDir.replace(".zip", "");
		String previousVersionProject = this.getIniFile().getPreviousVersion();
		String processingDir = this.getIniFile().getProcessingDir();
		IniFile iniFile = new IniFile(findIniFile(webFlowZipFile));
		iniFile.setSource(previousVersionProject);
		String destinationDir = processingDir + "/" + targetDir;
		iniFile.setDestination(destinationDir);
		return iniFile;
	}
	
	protected IniFile extractSkeletonFile(File webFlowZipFile) throws Exception {
		IniFile iniFile = getSkeletonIniFile(webFlowZipFile);
		org.w0a.Main task = new org.w0a.Main(iniFile, false);
		task.execute();
		return iniFile;
	}

	protected IniFile extractPreviousVersion(File webFlowZipFile) throws Exception {
		extractSkeletonFile(webFlowZipFile);
		IniFile iniFile = getPreviousVersionIniFile(webFlowZipFile);
		org.w0a.Main task = new org.w0a.Main(iniFile, false);
		task.execute();
		return iniFile;
	}

	protected void installNodeModules(W2AIniFile w2aIniFile, boolean forward) throws Exception {
		String from = this.getIniFile().getProcessingDir();
		String to = w2aIniFile.getDestination();
		to = to.replace("/src", "");
		String node_modules_dir_path_from = HTMLEdit.append(from, "node_modules");
		String node_modules_dir_path_to = HTMLEdit.append(to, "node_modules");
		File node_modules_dir_from = new File(node_modules_dir_path_from);
		File node_modules_dir_to = new File(node_modules_dir_path_to);
		boolean result = false;
		if (forward)
			result = node_modules_dir_from.renameTo(node_modules_dir_to);
		else
			result = node_modules_dir_to.renameTo(node_modules_dir_from);
		if (!result)
			throw new UnableToMoveNodeModulesFolderException(w2aIniFile.getFilename());
	}

	protected W2AIniFile setupWorkarea(File webFlowZipFile) throws Exception {
		String w2aIniFilename = findIniFile(webFlowZipFile);
		W2AIniFile w2aIniFile = new W2AIniFile(w2aIniFilename);
		IniFile iniFile = null;
		if ( !this.getIniFile().hasPreviousVersion() ) {
			iniFile = extractSkeletonFile(webFlowZipFile);
			w2aIniFile.setForwardGenerateAppRoutesOnly(true);
		}
		else {
			iniFile = extractPreviousVersion(webFlowZipFile);
			w2aIniFile.setForwardGenerateAppRoutesOnly(false);
		}
		String b1 = iniFile.getDestination();
		String c1 = webFlowZipFile.getName();
		String d1 = this.getIniFile().getUpLoadsDir();
		String sourceWebflowArchiveFullFilename = d1 + "/" + c1;
		String sourceProcessingDirectory = b1;
		w2aIniFile.setSource(sourceWebflowArchiveFullFilename);
		w2aIniFile.setDestination(sourceProcessingDirectory + "/src");
		return w2aIniFile;
	}

	private void archiveDir(String sourceDir, String destinationZipFile) throws Exception {
		ZipFile zipFile = new ZipFile(destinationZipFile);
		String folderToAdd = sourceDir;
		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
		zipFile.addFolder(folderToAdd, parameters);
	}

	protected void deleteProcessedFiles(String processingDir, File webFlowZipFile) throws Exception {
		String dirName = webFlowZipFile.getName().replace(".zip", "");
		String targetName = HTMLEdit.append(processingDir, dirName);
		NgFile.deleteDir(targetName);
		String w2aIniFilename = findIniFile(webFlowZipFile);
		W2AIniFile w2aIniFile = new W2AIniFile(w2aIniFilename);
		String destinationZipFile = w2aIniFile.getSource();
		NgFile.deleteFile(destinationZipFile);
		if ( this.hasSrcZip(webFlowZipFile) )
			new File(this.getSrcZipFilename(webFlowZipFile)).delete();
	}

	protected void packageDownload(File webFlowZipFile) throws Exception {
		String w2aIniFilename = findIniFile(webFlowZipFile);
		W2AIniFile w2aIniFile = new W2AIniFile(w2aIniFilename);
		String destinationZipFile = w2aIniFile.getSource();
		destinationZipFile = destinationZipFile.replace("/uploads", "/downloads");
		String sourceDir = w2aIniFile.getDestination();
		archiveDir(sourceDir, destinationZipFile);
		String processingDir = this.getIniFile().getProcessingDir();
		deleteProcessedFiles(processingDir, webFlowZipFile);
	}

	protected void freeform(File webFlowZipFile) throws Exception {
		String iniFile = findIniFile(webFlowZipFile);
		String[] args = { iniFile };
		org.w2a.Main task = new org.w2a.Main(args);
		task.execute();
	}

	protected void deletePreviousVersionInDownloadsDirectoryFromPreviousSubmission(W2AIniFile w2aIniFile,
			File webFlowZipFile) throws Exception {
		String destinationZipFile = this.getIniFile().getDownloadsArchiveName(webFlowZipFile);
		NgFile.deleteFile(destinationZipFile);
	}
	
	String getSrcZipFilename(File webFlowZipFile) throws Exception {
		String name = webFlowZipFile.getName();
		name = name.replace(".zip", ".src.zip");
		String srcName = name;
		String desDir = this.getIniFile().getUpLoadsDir();
		String srcPathname = HTMLEdit.append(desDir, srcName);
		return srcPathname;
	}
	
	boolean hasSrcZip(File webFlowZipFile) throws Exception {
		String srcPathname = getSrcZipFilename(webFlowZipFile);
		return new File(srcPathname).exists();
	}
	
	boolean isSrcZip(File webFlowZipFile) {
		return webFlowZipFile.getName().endsWith(".src.zip");
	}
	
	boolean isWebflowZip(File webFlowZipFile) {
		return webFlowZipFile.getName().endsWith(".zip");
	}

	void adjustPreviousVersionParameter(File webFlowZipFile) throws Exception {
		if ( hasSrcZip(webFlowZipFile) ) {
			this.getIniFile().setPreviousVersion(getSrcZipFilename(webFlowZipFile));
		}
		else
			this.getIniFile().setPreviousVersion(null);
	}
	
	static String getErrorFilename(W3AIniFile iniFile) throws Exception {
		String desDir = iniFile.getProcessingDir();
		String filename = HTMLEdit.append(desDir, "server.exception.txt");
		return filename;
	}
	
	void reportExceptionToClient(File webFlowZipFile, W3AException e) throws Exception {
		String msg = e.getIssue();
		String filename = getErrorFilename(this.getIniFile());
		NgTextFile.output(msg, filename);
	}

	public void executeTask(File webFlowZipFile) throws Exception {
		
		try {
			
			adjustPreviousVersionParameter(webFlowZipFile);
			W2AIniFile w2aIniFile = setupWorkarea(webFlowZipFile);
			
			if (isFileProcessed(webFlowZipFile)) {
				deletePreviousVersionInDownloadsDirectoryFromPreviousSubmission(w2aIniFile, webFlowZipFile);
			}
			
			try {
				installNodeModules(w2aIniFile, true);
				freeform(webFlowZipFile);
			} finally {
				installNodeModules(w2aIniFile, false);
			}
			
			packageDownload(webFlowZipFile);
		
		}
		catch ( W3AException e ) {
			System.err.println("\n\t"+e.getIssue()+"\n");
			reportExceptionToClient(webFlowZipFile,e);
			webFlowZipFile.delete();
		}
		
	}
		
	public void execute() throws Exception {
		
		File folder = new File(getIniFile().getUpLoadsDir());
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (!this.getIniFile().isIgnored(listOfFiles[i].getName())) {
				if (listOfFiles[i].isFile()) {
					File webFlowZipFile = listOfFiles[i];
					if ( isSrcZip(webFlowZipFile) )
						continue;
					if ( isWebflowZip(webFlowZipFile) )
						executeTask(webFlowZipFile);
				}
			}
		}
		
	}

	/**
	 * void getFormatter()
	 * 
	 * Determines the WebflowArchive object to be used. This method should be
	 * overridden to allow Main to utilize specialized WebflowArchive
	 * derivatives.
	 * 
	 */

	public void monitorSource() throws Exception {

		printStartup();

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			File f2 = new File(iniFilename);

			@Override
			public void run() {
				try {
					File f1 = new File(getIniFile().getUpLoadsDir());
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

	public static void prepare(String project, W3AIniFile iniFile, String from, boolean forward, boolean guess, String reverse) throws Exception {
		String clientIniFile = project + ".ini";
		String clientZipFile = project + ".zip";
		String toIniFilesIni = HTMLEdit.append(iniFile.getIniFileRepositoryDir(), clientIniFile);
		String toUpLoadsZip = HTMLEdit.append(iniFile.getUpLoadsDir(), clientZipFile);
		String fromIniFilesIni = HTMLEdit.append(from, clientIniFile);
		String fromUpLoadsZip = HTMLEdit.append(from, clientZipFile);
		String downloadsProjectZip = HTMLEdit.append(iniFile.getDownLoadsDir(), clientZipFile);
		NgFile.deleteFile(toIniFilesIni);
		NgFile.deleteFile(toUpLoadsZip);
		NgFile.deleteFile(downloadsProjectZip);
		NgFile.copyFile(fromIniFilesIni, toIniFilesIni);
		NgFile.copyFile(fromUpLoadsZip, toUpLoadsZip);
		String fromUpLoadsSrcZip = fromUpLoadsZip.replace(".zip", ".src.zip");
		String toUpLoadsSrcZip = toUpLoadsZip.replace(".zip", ".src.zip");
		if ( !guess ) {
			File file = new File(toUpLoadsSrcZip);
			String x = file.getName();
			toUpLoadsSrcZip = toUpLoadsSrcZip.replace(x, reverse+".zip");
		}
		NgFile.deleteFile(toUpLoadsSrcZip);
		iniFile.setPreviousVersion(null);
		if ( !forward ) {
			NgFile.copyFile(fromUpLoadsSrcZip, toUpLoadsSrcZip);
		}
	}

	public static void prepareForward(String project, W3AIniFile iniFile, String from, boolean guess, String reverse) throws Exception {
		prepare(project, iniFile, from, true, guess, reverse);
	}
	
	public static void prepareReverse(String project, W3AIniFile iniFile, String from, boolean guess, String reverse) throws Exception {
		iniFile.setPreviousVersion(reverse);
		prepare(project, iniFile, from, false, guess, reverse);
	}
	
	public static boolean projectProcessed(W3AIniFile iniFile, String project) throws Exception {
		String clientZipFile = project + ".zip";
		String downloadsProjectZip = HTMLEdit.append(iniFile.getDownLoadsDir(), clientZipFile);
		// String downloadsProjectSrcZip = downloadsProjectZip.replace(".zip", ".src.zip");
		return new File(downloadsProjectZip).exists();
	}

	public static boolean projectProcessedInError(W3AIniFile iniFile) throws Exception {
		String filename = getErrorFilename(iniFile);
		boolean test = new File(filename).exists();
		return test;
	}

	public static void prepareReverseInError(String project, W3AIniFile iniFile, String from, boolean guess, String reverse) throws Exception {
		prepareReverse(project,iniFile,from,guess, reverse);
		NgFile.deleteFile(getErrorFilename(iniFile));
	}

}