package org.w4a.requests;

import java.io.File;

import org.w2a.W2AIniFile;
import org.w4a.W4AIniFile;
import org.w4a.server.Server;
import org.w4a.tasks.ProcessForwardRequest;

public class ForwardUploadRequest extends UploadRequest {

	public ForwardUploadRequest(File webflowFile) {
		super(webflowFile);
	}

	@Override
	public void doTask(Server server, W2AIniFile w2aIniFile, W4AIniFile w4aIniFile) throws Exception {
		server.doTask( new ProcessForwardRequest(server,w2aIniFile,w4aIniFile) );
	}


}
