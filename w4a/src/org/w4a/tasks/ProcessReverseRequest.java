package org.w4a.tasks;

import java.io.File;

import org.w2a.W2AIniFile;
import org.w4a.W4AIniFile;
import org.w4a.exceptions.ReverseRequestNotProcessedProperlyException;
import org.w4a.server.Server;
import org.w4a.server.ServerUtils;
import org.w4a.validators.ValidateWebflowArchive;
import org.w4a.validators.ValidateZippedArchive;

public class ProcessReverseRequest extends ProcessForwardRequest implements TaskInterface {

	File zippedFile;
	
	public ProcessReverseRequest(Server server, W2AIniFile w2aIniFile, W4AIniFile w4aIniFile, File zippedFile) throws Exception {
		super(server,w2aIniFile,w4aIniFile);
		this.zippedFile = zippedFile;
	}

	@Override
	public void doPreConditions() throws Exception {
		new ValidateWebflowArchive(this.w2aIniFile).validate();
		String oldSource = this.w2aIniFile.setSource(zippedFile.getPath());
		new ValidateZippedArchive(this.w2aIniFile).validate();
		this.w2aIniFile.setSource(oldSource);
	}

	@Override
	public boolean doUseCaseTrigger() throws Exception {
		String webflowArchive = w2aIniFile.getSource();
		boolean test1 = server.existsInUploads(webflowArchive) ;
		boolean test2 = server.existsInUploads(zippedFile.getPath());
		return test1 && test2;
	}

	public void doPrepareUseCase() throws Exception {
		super.doPrepareUseCase();
		ServerUtils.unzip(this.zippedFile,this.workarea);
		this.zippedFile.delete();
	}
	
	@Override
	public void doCleanupUseCase() throws Exception {
		super.doCleanupUseCase();
		// zippedFile.delete();
	}
	
	@Override
	public void doPostConditions() throws Exception {
		super.doPostConditions();
		if ( zippedFile.exists() )
			throw new ReverseRequestNotProcessedProperlyException(this.destination);
	}
	
}
