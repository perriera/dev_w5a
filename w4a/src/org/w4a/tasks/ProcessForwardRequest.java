package org.w4a.tasks;

import java.io.File;

import org.w2a.W2AIniFile;
import org.w4a.W4AIniFile;
import org.w4a.exceptions.ForwardRequestNotProcessedProperlyException;
import org.w4a.server.Server;
import org.w4a.validators.ValidateWebflowArchive;

public class ProcessForwardRequest extends ProcessPrepareUseCase implements TaskInterface {

	public ProcessForwardRequest(Server server, W2AIniFile w2aIniFile, W4AIniFile w4aIniFile) throws Exception {
		super(server,w2aIniFile,w4aIniFile);
	}

	@Override
	public void doPreConditions() throws Exception {
		new ValidateWebflowArchive(this.w2aIniFile).validate();
	}

	@Override
	public boolean doUseCaseTrigger() throws Exception {
		String webflowArchive = w2aIniFile.getSource();
		return server.existsInUploads(webflowArchive);
	}

	@Override
	public void doWishCase() throws Exception {
		
		org.w2a.Main task = new org.w2a.Main(this.w2aIniFile, false);
		task.execute();
		
	}

	@Override
	public void doPostConditions() throws Exception {
		if ( new File(this.workarea).exists() )
			throw new ForwardRequestNotProcessedProperlyException(this.workarea);
		if ( !new File(this.destination).exists() )
			throw new ForwardRequestNotProcessedProperlyException(this.destination);
	}

	@Override
	public void doAlternateCase() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doExceptionCase() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
