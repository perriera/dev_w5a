package org.w4a.tasks;

import java.io.File;

import org.w0a.HTMLEdit;
import org.w4a.W4AIniFile;
import org.w4a.exceptions.NodeModulesDirMissingException;
import org.w4a.exceptions.NodeModulesDirNotSpecifiedException;
import org.w4a.exceptions.NodeModulesNotMovedException;
import org.w4a.server.ServerUtils;

@Deprecated
public class InjectNodeModules implements TaskInterface {
	
	String workarea;
	String destination;
	W4AIniFile iniFile;
	
	public InjectNodeModules(String workarea, W4AIniFile iniFile) {
		this.workarea = workarea;
		this.iniFile = iniFile;
	}

	@Override
	public void doPreConditions() throws Exception {
		if ( this.iniFile.getNodeModulesDir() == null ) 
			throw new NodeModulesDirNotSpecifiedException(this.iniFile.getFilename());
		if ( !this.iniFile.getNodeModulesDir().exists() ) 
			throw new NodeModulesDirMissingException(this.iniFile.getFilename());
	}

	@Override
	public boolean doUseCaseTrigger() throws Exception {
		String target = HTMLEdit.append(workarea, "node_modules");
		return !new File(target).exists();
	}

	@Override
	public void doPrepareUseCase() throws Exception {
		this.destination = HTMLEdit.append(workarea, "node_modules");
	}

	@Override
	public void doWishCase() throws Exception {
		ServerUtils.mv(this.iniFile.getNodeModulesDir(),this.destination);
	}

	@Override
	public void doCleanupUseCase() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doPostConditions() throws Exception {
		if ( !new File(this.destination).exists() )
			throw new NodeModulesNotMovedException(workarea);
		if ( this.iniFile.getNodeModulesDir().exists() )
			throw new NodeModulesNotMovedException(workarea);
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
