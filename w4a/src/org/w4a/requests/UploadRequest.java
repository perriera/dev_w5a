package org.w4a.requests;

import java.io.File;

import org.w2a.W2AIniFile;
import org.w4a.W4AIniFile;
import org.w4a.server.Server;

abstract public class UploadRequest {

	File webflowFile;
	File iniFile;

	public UploadRequest(File webflowFile) {
		this.webflowFile = webflowFile;
	}
	
	public File getWebflowFile() {
		return webflowFile;
	}
	
	abstract public void doTask(Server server, W2AIniFile w2aIniFile, W4AIniFile w4aIniFile) throws Exception;

	public void setIniFile(File iniFile) {
		this.iniFile = iniFile;
	}

	public File getIniFile() {
		return iniFile;
	}
	
}
