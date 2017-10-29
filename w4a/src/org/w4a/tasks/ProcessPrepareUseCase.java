package org.w4a.tasks;

import java.io.File;

import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.exceptions.NoTokenFoundInFilenameException;
import org.w4a.W4AIniFile;
import org.w4a.server.Server;
import org.w4a.server.ServerUtils;

abstract public class ProcessPrepareUseCase implements TaskInterface {

	Server server;
	W2AIniFile w2aIniFile;
	W4AIniFile w4aIniFile;
	String workarea;
	String srcDir;
	String destination;
	
	
	public static String getTokenizedFilename(String filename) throws NoTokenFoundInFilenameException {
		return HTMLEdit.getTokenFromFilename(filename) + ".zip";
	}
	
	public ProcessPrepareUseCase(Server server, W2AIniFile w2aIniFile, W4AIniFile w4aIniFile) throws Exception {
		this.server = server;
		this.w2aIniFile = w2aIniFile;
		this.w4aIniFile = w4aIniFile;
	}
	
	@Override
	public void doPrepareUseCase() throws Exception {
		
		String processDir = this.w4aIniFile.getProcessingDir();
		String filename = ServerUtils.getFilename(this.w2aIniFile.getFilename());
		this.workarea = HTMLEdit.append(processDir, filename.replace(".ini", ""));
		ServerUtils.mkdirs(workarea);
		server.injectSkeletonPackage(workarea);
		this.destination = w2aIniFile.getDestination();
		this.srcDir = HTMLEdit.append(this.workarea, "src");
		w2aIniFile.setDestination(srcDir);
		
		w2aIniFile.setFilename(ServerUtils.moveTo(w2aIniFile.getFilename(), this.workarea,true));
		w2aIniFile.setSource(ServerUtils.moveTo(w2aIniFile.getSource(), this.workarea,true));
		
		String token = HTMLEdit.getTokenFromFilename(this.destination) + ".zip";
		if ( new File(token).exists() )
			new File(token).delete();
		 
	}
	
	@Override
	public void doCleanupUseCase() throws Exception {

		this.destination = ProcessPrepareUseCase.getTokenizedFilename(this.destination);
		ServerUtils.zip(this.srcDir, this.destination);
		ServerUtils.rmdirs(workarea);
		
	}
	
	
}
