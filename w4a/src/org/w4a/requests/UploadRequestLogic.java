package org.w4a.requests;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;
import org.ini4j.InvalidFileFormatException;
import org.w0a.exceptions.NgExceptions;
import org.w0a.interfaces.SourceCompilerInterface;
import org.w1a.HTMLEdit;
import org.w2a.ngfile.NgFile;
import org.w2a.ngtypes.NgTextFile;
import org.w4a.W4AIniFile;
import org.w4a.exceptions.BadInventoryKeyException;
import org.w4a.exceptions.BadRequestKeyException;
import org.w4a.exceptions.BogusZipFileLoadedIntoUploadsException;
import org.w4a.exceptions.DuplicateSrcArchivesUploadedException;
import org.w4a.exceptions.MissingKeyInUploadedFileException;
import org.w4a.exceptions.MissingZipEntryException;
import org.w4a.exceptions.NotUploadedException;
import org.w4a.exceptions.W4AException;
import org.w4a.exceptions.ZippedSrcDroppedIntoWebflowBoxByMistakeException;
import org.w4a.keys.InventoryKey;
import org.w4a.keys.RequestKey;
import org.w4a.keys.UploadedKey;
import org.w4a.server.Server;
import org.w4a.server.ServerUtils;
import org.w4a.validators.ValidateIniFile;
import org.w4a.validators.ValidateWebflowArchive;
import org.w4a.validators.ValidateZippedArchive;

import net.lingala.zip4j.exception.ZipException;

public class UploadRequestLogic implements SourceCompilerInterface {

	Server server;
	HashMap<String, File> reverseWebflowFileMap = new HashMap<String, File>();
	HashMap<String, File> forwardWebflowFileMap = new HashMap<String, File>();
	HashMap<String, File> zippedFileMap = new HashMap<String, File>();
	HashMap<String, File> iniFileMap = new HashMap<String, File>();
	UploadRequestList uploadRequestList = new UploadRequestList();

	public UploadRequestLogic(Server server) {
		this.server = server;
	}

	public HashMap<String, File> getZippedFileMap() {
		return zippedFileMap;
	}

	public UploadRequestList getUploadRequestList() {
		return uploadRequestList;
	}

	public HashMap<String, File> getReverseWebflowFileMap() {
		return reverseWebflowFileMap;
	}

	public HashMap<String, File> getForwardWebflowFileMap() {
		return forwardWebflowFileMap;
	}

	public void parseUploaded(File zipFile) throws Exception {

		try {

			String filename = zipFile.getName();
			UploadedKey uk = new UploadedKey(filename);
			RequestKey rk = new RequestKey(uk.getKey());
			InventoryKey ik = new InventoryKey(rk.getKey());
			Exception known = null;
			
			String key = ik.getToken();
			int strikes = 0;

			try {
				ValidateWebflowArchive test = new ValidateWebflowArchive(zipFile);
				test.validate();
				// test.delete();
				if (RequestKey.isForward(zipFile))
					this.forwardWebflowFileMap.put(key, zipFile);
				else
					this.reverseWebflowFileMap.put(key, zipFile);
				UploadingMonitor.clear(zipFile);
				return;
			} catch (MissingZipEntryException expected) {
				strikes++;
			} catch (ZipException expected) {
				strikes++;
			}

			try {
				ValidateZippedArchive test = new ValidateZippedArchive(zipFile);
				test.validate();
				test.delete();
				if ( this.zippedFileMap.get(key)==null ) {
					this.zippedFileMap.put(key, zipFile);
				} else {
					zipFile.delete();
					this.zippedFileMap.get(key).delete();
					throw new DuplicateSrcArchivesUploadedException(zipFile,this.zippedFileMap.get(key));
				}
				UploadingMonitor.clear(zipFile);
				return;
			} catch (ZippedSrcDroppedIntoWebflowBoxByMistakeException e) {
				known = e;
				ValidateZippedArchive test = new ValidateZippedArchive(zipFile);
				test.delete();
				strikes++;
			} catch (MissingZipEntryException expected) {
				strikes++;
			} catch (ZipException expected) {
				strikes++;
			}

			try {
				ValidateIniFile test = new ValidateIniFile(zipFile);
				test.validate();
				// test.delete();
				this.iniFileMap.put(key, zipFile);
				UploadingMonitor.clear(zipFile);
				return;
			} catch (InvalidFileFormatException expected) {
				strikes++;
			}

			if (strikes > 2) {
				listDirectory(zipFile);
				if ( !(UploadingMonitor.isUploading(zipFile)&&known==null) ) {
					if ( known!=null )
						throw known;
					else
						throw new BogusZipFileLoadedIntoUploadsException(filename);
				}
			}

		}

		catch (MissingKeyInUploadedFileException ex) {
			handleBadUpload(server,zipFile,ex);
		} catch (NotUploadedException ex) {
			reportExceptionToClient(zipFile,ex);
			NgExceptions.diagnostics(ex, zipFile);
		} catch (BadRequestKeyException ex) {
			handleBadUpload(server,zipFile,ex);
		} catch (BadInventoryKeyException ex) {
			handleBadUpload(server,zipFile,ex);
		} catch (BogusZipFileLoadedIntoUploadsException ex) {
			handleBadUpload(server,zipFile,ex);
		} catch (ZippedSrcDroppedIntoWebflowBoxByMistakeException ex) {
			handleBadUpload(server,zipFile,ex);
		} catch (DuplicateSrcArchivesUploadedException ex) {
			handleBadUpload(server,zipFile,ex);
//		}
		} catch (Exception ex) {
			handleBadUpload(server,zipFile,ex);
		}

	}
	
	void listDirectory(File zipFile) throws Exception {
		File pathname = NgFile.getPath(zipFile);
		File folder = new File(pathname.getPath());
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (!server.getXIniFile().isIgnored(listOfFiles[i].getName())) {
				if (listOfFiles[i].isFile()) {
					File file = listOfFiles[i];
					System.out.println(file.getName()+":\t "+file.length());
				}
			}
		}
	}

	static void removeLooseEndsInUploads(Server server, File zipFile) throws Exception {	
		try {
			UploadedKey uk = new UploadedKey(zipFile.getName());
			RequestKey rk = new RequestKey(uk.getKey());
			InventoryKey ik = new InventoryKey(rk.getKey());
			File path = NgFile.getPath(zipFile);
			File[] listOfFiles = path.listFiles();
			for ( File file : listOfFiles ) {
				if (!server.getXIniFile().isIgnored(file.getName())) {
					UploadedKey uk2 = new UploadedKey(file.getName());
					RequestKey rk2 = new RequestKey(uk2.getKey());
					InventoryKey ik2 = new InventoryKey(rk2.getKey());
					if ( ik.getToken().equals(ik2.getToken()) )
						file.delete();
				}
			}
		} catch ( NgExceptions ex ) {
			NgExceptions.diagnostics(ex, zipFile);
		}
	}

	void handleBadUpload(Server server, File zipFile, Exception ex) throws Exception {
		reportExceptionToClient(zipFile,ex);
		NgExceptions.diagnostics(ex, zipFile);
		ServerUtils.mv(zipFile, server.getXIniFile().getDiagnosticsDir());
		removeLooseEndsInUploads(server,zipFile);
	}

	void handleBadUpload(Server server, File zipFile, W4AException ex) throws Exception {
		reportExceptionToClient(zipFile,ex);
		NgExceptions.diagnostics(ex, zipFile);
		ServerUtils.mv(zipFile, server.getXIniFile().getDiagnosticsDir());
		removeLooseEndsInUploads(server,zipFile);
	}
	
	static String getErrorFilename(File webFlowZipFile, W4AIniFile iniFile) throws Exception {
		UploadedKey uk = new UploadedKey(webFlowZipFile.getName());
		RequestKey rk = new RequestKey(uk.getKey());
		InventoryKey ik = new InventoryKey(rk.getKey());
		String desDir = iniFile.getProcessingDir();
		String filename = HTMLEdit.append(desDir, ik.getToken()+".exception.txt");
		return filename;
	}

	void reportExceptionToClient(File webFlowZipFile, W4AException e) throws Exception {
		String msg = e.getPublicIssue();
		String filename = getErrorFilename(webFlowZipFile, server.getXIniFile());
		NgTextFile.output(msg, filename);
	}

	void reportExceptionToClient(File webFlowZipFile, Exception e) throws Exception {
		String msg = e.getMessage();
		String filename = getErrorFilename(webFlowZipFile, server.getXIniFile());
		NgTextFile.output(msg, filename);
	}
	
	void parseUploadsDirectory(String path) throws Exception {

		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (!server.getXIniFile().isIgnored(listOfFiles[i].getName())) {
				if (listOfFiles[i].isFile()) {
					File zipFile = listOfFiles[i];
					parseUploaded(zipFile);
				}
			}
		}

	}

	void parseRequests(String path) throws Exception {

		for (Entry<String, File> entry : reverseWebflowFileMap.entrySet()) {
			String key = entry.getKey();
			File webflowFile = entry.getValue();
			UploadRequest request = null;
			if (zippedFileMap.containsKey(key)) {
				File zippedFile = zippedFileMap.get(key);
				request = new ReverseUploadRequest(webflowFile, zippedFile);
				uploadRequestList.put(key, request);
			}
		}

		for (Entry<String, File> entry : forwardWebflowFileMap.entrySet()) {
			String key = entry.getKey();
			File webflowFile = entry.getValue();
			UploadRequest request = new ForwardUploadRequest(webflowFile);
			uploadRequestList.put(key, request);
		}

		for (Entry<String, File> entry : iniFileMap.entrySet()) {
			String key = entry.getKey();
			File iniFile = entry.getValue();
			UploadRequest request = uploadRequestList.get(key);
			if (request != null)
				request.setIniFile(iniFile);
		}

	}

	@Override
	public void input(String path) throws Exception {

		parseUploadsDirectory(path);
		parseRequests(path);

	}

	@Override
	public String output() throws Exception {
		parseRequests(null);
		return null;
	}

}
