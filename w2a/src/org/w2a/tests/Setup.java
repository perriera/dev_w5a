package org.w2a.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import net.lingala.zip4j.core.*;
import net.lingala.zip4j.model.*;
import net.lingala.zip4j.util.*;

import org.junit.Assert;
import org.w0a.IniFile;

public class Setup {

	public static String assignmentFile = "files/assignment.webflow.zip";

	public static void deleteDir(File file) {
		File[] contents = file.listFiles();
		if (contents != null) {
			for (File f : contents) {
				deleteDir(f);
			}
		}
		file.delete();
	}
	
	static String getZipFilename(String iniFilename) throws Exception {
		IniFile iniFile = new IniFile(iniFilename);
		String where = iniFile.getDestination();
		String zipFilename = where.replace("/src","/src.zip");
		return zipFilename;
	}

	public static void zipupTestFiles(String iniFilename) throws Exception {

		IniFile iniFile = new IniFile(iniFilename);
		String where = iniFile.getDestination();
		String zipFilename = getZipFilename(iniFilename);
		
		if (new File(zipFilename).isFile()) {
			return;
		}

		// Initiate ZipFile object with the path/name of the zip file.
		ZipFile zipFile = new ZipFile(zipFilename);

		// Initiate Zip Parameters which define various properties such
		// as compression method, etc.
		ZipParameters parameters = new ZipParameters();

		// set compression method to store compression
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);

		// Set the compression level
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

		// Add folder to the zip file
		zipFile.addFolder(where, parameters);

	}

	public static void unZipTestFiles(String iniFilename) throws Exception {
		IniFile iniFile = new IniFile(iniFilename);
		String where = iniFile.getDestination();
		String zipFilename = getZipFilename(iniFilename);
		ZipFile zipFile = new ZipFile(zipFilename);
		where = where.replace("/src", "/");
		zipFile.extractAll(where);
	}
	
	public static void deleteZipFile(String iniFilename) throws Exception {
		String zipFilename = getZipFilename(iniFilename);
		if (new File(zipFilename).isFile()) {
			Assert.assertTrue(new File(zipFilename).delete());
		}
	}
	
	public static void deleteTestFiles(String filename) throws Exception {
		IniFile iniFile = new IniFile(filename);
		String where = iniFile.getDestination() + "/index.html";
		if (new File(where).isFile()) {
			Assert.assertTrue(new File(iniFile.getDestination() + "/index.html").delete());
			deleteDir(new File(iniFile.getDestination()));
		}
	}

	public static void copyFile(File source, File dest) throws IOException {
		if (!dest.exists())
			Files.copy(source.toPath(), dest.toPath());
	}

}
