package org.w4a.tasks;

import java.io.File;

import org.w2a.W2AIniFile;
import org.w4a.exceptions.BogusZipFileLoadedIntoUploadsException;
import org.w4a.requests.UploadRequest;
import org.w4a.server.Server;
import org.w4a.server.ServerUtils;

public class PrepareIniFileForUse  {
	
	W2AIniFile w2aIniFile;
	
	public static W2AIniFile useOrCreateDefaultIniFile(File zipFile) throws Exception {
		W2AIniFile w2aIniFile = new W2AIniFile();
		String zipFilename = zipFile.getPath();
		if (!zipFilename.endsWith(".zip")&&!zipFilename.endsWith(".ini"))
			throw new BogusZipFileLoadedIntoUploadsException(zipFilename);
		String filename = zipFile.getPath().replace(".zip", ".ini");
		if ( !new File(filename).exists() ) 
			ServerUtils.cp(new File("files/inifiles/default.webflow.ini"), new File(filename));
		w2aIniFile.setFilename(filename);
		w2aIniFile.setSource(zipFile.getPath());
		return w2aIniFile;
	}
	
	public PrepareIniFileForUse(Server server, UploadRequest request) throws Exception {
	
		File webflowArchive = request.getWebflowFile();

		if ( request.getIniFile()!=null )
			w2aIniFile = new W2AIniFile(request.getIniFile());
		else {
			w2aIniFile = useOrCreateDefaultIniFile(webflowArchive);
		}
		
		String source = server.getXIniFile().getUploadsPath(webflowArchive);
		String destination = server.getXIniFile().getDownloadsPath(webflowArchive);
		w2aIniFile.setSource(source);
		w2aIniFile.setDestination(destination);
		request.setIniFile(new File(w2aIniFile.getFilename()));

	}

	public W2AIniFile getW2AIniFile() {
		return w2aIniFile;
	}


}
