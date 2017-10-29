package org.w3a.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.ini4j.InvalidFileFormatException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w2a.HTMLEdit;
import org.w3a.Main;
import org.w3a.W3AIniFile;

public class MainTest1 {

	String iniFilename;
	String srcFilename;
	String desFilename;
	String doneFilename;
	
	String dest;
	String iniDir;
	String iniTestFile;
	String to;
	String done;
	
	@Before
	public void setup() throws InvalidFileFormatException, FileNotFoundException, IOException, Exception {
		String testIniFile = "freeformjs.webflow.ini";
		this.iniFilename = "files/formaltests/"+testIniFile;
		this.srcFilename = "files/formaltests/freeformjs.webflow.zip";
		this.desFilename = "freeformjs.webflow.zip";
		this.doneFilename = "freeformjs.webflow.zip";
		W3AIniFile w3aIniFile = new W3AIniFile(iniFilename);
		this.dest = w3aIniFile.getUpLoadsDir();
		this.iniDir = w3aIniFile.getIniFileRepositoryDir();
		this.to = HTMLEdit.append(dest, desFilename);
		this.done = HTMLEdit.append(dest, doneFilename);
		this.done = this.to.replace("/uploads/", "/downloads/");
		TestUtilities.deleteFile(to);
		TestUtilities.deleteFile(done);
		this.iniTestFile = HTMLEdit.append(iniDir, testIniFile);
		TestUtilities.deleteFile(this.iniTestFile);
		String testzipinuploadfrompreviousrun = w3aIniFile.getUpLoadsArchiveName(new File("freeformjs.webflow.zip"));
		TestUtilities.deleteFile(testzipinuploadfrompreviousrun);
		String testzipindownloadfrompreviousrun = w3aIniFile.getDownloadsArchiveName(new File("freeformjs.webflow.zip"));
		TestUtilities.deleteFile(testzipindownloadfrompreviousrun);
		TestUtilities.deleteFile(this.iniTestFile);
		TestUtilities.copyFile(iniFilename,iniTestFile);
	}
	
	@Test
	public void test_construct() throws Exception {
		String[] args = { iniFilename, "-w" };
		Main main = new Main(args);
		Assert.assertNotNull(main);
	}

	@Test
	public void test_file_upload_detection() throws Exception {
		String[] args = { iniFilename, "-w" };
		boolean fileuploaded = false;
		boolean alldone = false;
		Main.main(args);
		while(!alldone) {
			Thread.sleep(1000);
			if ( !fileuploaded && !new File(to).exists() ) {
				TestUtilities.copyFile(srcFilename, to);
				fileuploaded = true;
			}
			if ( fileuploaded ) {
				alldone = true;
			}
		}
	}

	@Test
	public void test_findIniFile() throws Exception {
		String[] args = { iniFilename, "-w" };
		Main main = new Main(args);
		File zipFile = new File(this.srcFilename);
		String repository = main.findIniFile(zipFile);
		Assert.assertTrue(repository.equals("../../freeformjs-server/dev/inifiles/freeformjs.webflow.ini"));
	}
	
	@Test
	public void test_file_upload_extracted() throws Exception {
		String[] args = { iniFilename, "-w" };
		boolean fileuploaded = false;
		boolean alldone = false;
		Main.main(args);
		while(!alldone) {
			Thread.sleep(1000);
			if ( !fileuploaded && !new File(to).exists() ) {
				TestUtilities.copyFile(srcFilename, to);
				fileuploaded = true;
			}
			if ( fileuploaded && new File(done).exists() && !new File(to).exists() ) {
				alldone = true;
			}
		}
	}

	
}
