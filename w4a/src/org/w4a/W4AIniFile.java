package org.w4a;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.ini4j.InvalidFileFormatException;
import org.w1a.HTMLEdit;

public class W4AIniFile extends org.w0a.IniFile {

	public W4AIniFile(String filename) throws InvalidFileFormatException, FileNotFoundException, IOException {
		super(filename);
		// TODO Auto-generated constructor stub
	}

	/**
	 * boolean 	getWatchMode
	 * @return	Whether or not w0a is being run in watch mode where the
	 * 			existence of the source file is continually monitored.
	 * @throws 	IOException
	 */
	
	public String getMainDirectory() throws Exception {
		String mainDirectory = getDirective("Server","mainDirectory");
		if ( mainDirectory==null ) {
			mainDirectory = "./server";
		}
		return mainDirectory;
	}
	
	/**
	 * boolean 	getWatchMode
	 * @return	Whether or not w0a is being run in watch mode where the
	 * 			existence of the source file is continually monitored.
	 * @throws 	IOException
	 */
	
	public String getIniFilesDirectory() throws Exception {
		String iniFilesDirectory = getDirective("Server","iniFilesDirectory");
		if ( iniFilesDirectory==null ) {
			String serverDir = getMainDirectory();
			iniFilesDirectory = HTMLEdit.append(serverDir, "/inifiles");
		}
		return iniFilesDirectory;
	}

	public String getProcessingDir() throws Exception {
		String processing = getDirective("Server","processing");
		if ( processing==null ) {
			String serverDir = getMainDirectory();
			processing = HTMLEdit.append(serverDir, "/processing");
		}
		return processing;
	}

	public String getUploadsDir() throws Exception {
		return this.getSource();
	}
	
	public String getDownloadsDir() throws Exception {
		return this.getDestination();
	}

	public String getUploadsPath(File webflowArchive) throws Exception {
		String path = HTMLEdit.append(this.getUploadsDir(), webflowArchive.getName()); 
		return path;
	}

	public String getDownloadsPath(File webflowArchive) throws Exception {
		String path = HTMLEdit.append(this.getDownloadsDir(), webflowArchive.getName()); 
		return path;
	}

	public File getNodeModulesDir() throws Exception {
		String nodeModulesDir = getDirective("Server","nodeModulesDir");
		return nodeModulesDir!=null ? new File(nodeModulesDir) : null;
	}

	public String getResourcesDirectory() throws Exception {
		String resourcesDirectory = getDirective("Server","resourcesDirectory");
		if ( resourcesDirectory==null ) {
			String serverDir = getMainDirectory();
			resourcesDirectory = HTMLEdit.append(serverDir, "/resources");
		}
		return resourcesDirectory;
	}

	public File getSkeletonIniFile() throws Exception {
		String iniFilesDirectory = getIniFilesDirectory();
		String path = HTMLEdit.append(iniFilesDirectory, "skeleton.ini");
		return new File(path);
	}
	
	public File getSkeletonFile() throws Exception {
		String resourcesDirectory = getResourcesDirectory();
		String path = HTMLEdit.append(resourcesDirectory, "skeleton.zip");
		return new File(path);
	}

	public String getDiagnosticsDir() throws Exception {
		String diagnosticsDir = getDirective("Server","diagnosticsDir");
		if ( diagnosticsDir==null ) {
			String serverDir = getMainDirectory();
			diagnosticsDir = HTMLEdit.append(serverDir, "/diagnostics");
		}
		return diagnosticsDir;
	}
	
}


