package org.w4a.requests;

import java.io.File;

import org.w2a.W2AIniFile;
import org.w4a.W4AIniFile;
import org.w4a.server.Server;
import org.w4a.tasks.ProcessReverseRequest;

public class ReverseUploadRequest extends UploadRequest {

	File zippedFile;

	public ReverseUploadRequest(File webflowFile, File zippedFile) {
		super(webflowFile);
		this.zippedFile = zippedFile;
	}
	
	public File getZippedFile() {
		return zippedFile;
	}

	@Override
	public void doTask(Server server, W2AIniFile w2aIniFile, W4AIniFile w4aIniFile) throws Exception {
		server.doTask( new ProcessReverseRequest(server,w2aIniFile,w4aIniFile,this.zippedFile) );
	}

}
