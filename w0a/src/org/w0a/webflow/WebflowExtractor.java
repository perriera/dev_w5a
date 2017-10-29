package org.w0a.webflow;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.w0a.IniFile;
import org.w0a.exceptions.FailedToDeleteException;
import org.w0a.extractor.SourceEntryMap;
import org.w0a.extractor.SourceExtractor;
import org.w0a.interfaces.IniFileInterface;
import org.w0a.interfaces.SourceEntryInterface;
import org.w0a.interfaces.SourceExtractorInterface;
import org.w0a.webflow.types.WebflowBinaryFileEntry;
import org.w0a.webflow.types.WebflowCSSFileEntry;
import org.w0a.webflow.types.WebflowDirectoryEntry;
import org.w0a.webflow.types.WebflowHTMLFileEntry;
import org.w0a.webflow.types.WebflowImageFileEntry;
import org.w0a.webflow.types.WebflowIndexFileEntry;
import org.w0a.webflow.types.WebflowJSFileEntry;
import org.w0a.webflow.types.WebflowTextFileEntry;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.FileHeader;

@SuppressWarnings("serial")
public class WebflowExtractor extends SourceExtractor implements SourceExtractorInterface {

	IniFileInterface iniFile;
	
	public WebflowExtractor(IniFileInterface iniFile) {
		this.iniFile = iniFile;
	}
	
	@Override
	public IniFile getXIniFile() {
		return (IniFile)this.iniFile;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.w0a.base.SourceArchiveInterface#input(org.w0a.IniFile)
	 */
	
	@Override
	public void input() throws Exception {
		String zipFilename = getXIniFile().getSource();
		ZipFile zipFile = new ZipFile(zipFilename);
		// Get the list of file headers from the zip file
		List<?> fileHeaderList = zipFile.getFileHeaders();
		// Loop through the file headers
		for (int i = 0; i < fileHeaderList.size(); i++) {
			FileHeader fileHeader = (FileHeader)fileHeaderList.get(i);
			String filename = fileHeader.getFileName();
			filename = fixQuirk001(filename,zipFilename);
			// Extract the file from the specified source
			// System.out.println("Reading " + filename);
			if ( filename.length()>0 && !isIgnoredDirectory(filename, getXIniFile()) ) {
				fileHeader.setFileName(filename);
				SourceEntryInterface file = getSourceEntryTyped(zipFile, fileHeader,getXIniFile());
				file.input(filename);
				this.put(filename, file);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.w0a.base.SourceArchiveInterface#output(org.w0a.IniFile)
	 */
	
	@Override
	public void output() throws Exception {
		for ( Entry<String, SourceEntryInterface> entry : this ) {
			String key = entry.getKey();
			SourceEntryInterface value = entry.getValue();
			if ( getXIniFile().isIgnored(key) )
				if ( getXIniFile().isSkipIgnoredFiles() )
					continue; 
			value.output(key);			
		}
	}
	

	/**
	 * getSourceEntryTyped()
	 * 
	 * The purpose of this method is to determine the type of derived
	 * UncompressedFile class to instantiate based upon the type of
	 * file it is. 
	 * 
	 * @param fileHeader 	is provided by the Zip4J framework containing
	 * 						pertinent information related to each file
	 * 						extracted from the Webflow ZIP file.
	 * 
	 * @return				The UncompressedFile object which will be either
	 * 						a HTML, text or binary file.
	 * 
	 * @throws Exception
	 * 
	 */
	
	public SourceEntryInterface getSourceEntryTyped(ZipFile zipFile, FileHeader fileHeader, IniFile iniFile) throws Exception {
		SourceEntryInterface file = null;
		if (SourceEntryMap.isHtmlFile(fileHeader.getFileName())) {
			if ( SourceEntryMap.isIndexFile(fileHeader.getFileName()) )
				file = new WebflowIndexFileEntry(zipFile, fileHeader, iniFile);
			else
				file = new WebflowHTMLFileEntry(zipFile, fileHeader, iniFile);
		} else {
			if (SourceEntryMap.isCSSFile(fileHeader.getFileName())) {
				file = new WebflowCSSFileEntry(zipFile, fileHeader, iniFile);
			} else {
				if (SourceEntryMap.isJSFile(fileHeader.getFileName())) {
					file = new WebflowJSFileEntry(zipFile, fileHeader, iniFile);
				} else {
					if (SourceEntryMap.isTextFile(fileHeader.getFileName())) {
						file = new WebflowTextFileEntry(zipFile, fileHeader, iniFile);
					} else {
						if (SourceEntryMap.isDirectoryFile(fileHeader.getFileName())) {
							file = new WebflowDirectoryEntry(zipFile, fileHeader, iniFile);
						} else {
							if (SourceEntryMap.isImageFile(fileHeader.getFileName())) {
								file = new WebflowImageFileEntry(zipFile, fileHeader, iniFile);
							} else {
								file = new WebflowBinaryFileEntry(zipFile, fileHeader, iniFile);
							}
						}
					}
				}
			}
		}
		return file;
	}
	
	/**
	 * boolean 	isIgnoredDirectory()
	 * @param 	file
	 * @return	true if the start of the file path is a directory to be ignored
	 * @throws 	IOException
	 */
	
	boolean isIgnoredDirectory( String file, IniFile iniFile ) throws Exception {
		if ( iniFile.getIgnoreDir() == null )
			return false;
		for ( String prefix : iniFile.getIgnoreDir() ) 
			if ( file.startsWith(prefix) )
				return true;
		return false;
	}
	
	/**
	 * String 	fixQuirk001()
	 * @return	Reading a Webflow zip file is different than reading a re-compressed
	 * 			Webflow folder. The directory layout inside the zip file is different
	 * 			by default. This quirk is corrected with this method.
	 * @throws 	IOException
	 */
	
	String fixQuirk001(String filename, String zipFilename) {
		Path p = Paths.get(zipFilename);
		String baseDirectory = p.getFileName().toString();
		baseDirectory = baseDirectory.replace(".zip", "/");
		filename = filename.replace(baseDirectory, "");
		return filename;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * deleteZipFile()
	 * @throws Exception
	 * 
	 * This is called when the archive is no longer required and
	 * it is convenient to remove the original Webflow.zip file.
	 * Only the Main.class has to implement this feature. While this
	 * interface is used by the UncompressedFile class and it's 
	 * derivatives, it should throw a FeatureNotImplementedException
	 * if accidentally called there.
	 * 
	 */

	public void deleteZipFile() throws Exception, FailedToDeleteException {
		String zipFilename = this.getXIniFile().getSource();
		File file = new File(zipFilename);
		if (file.exists() && file.delete()) {
			System.out.println(file.getName() + " is deleted!");
		} else {
			throw new FailedToDeleteException();
		}
	}
	
	@Override
	public void cleanup() throws Exception {
		deleteZipFile();
	}

}
