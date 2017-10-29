package org.w4a.tasks;

import java.io.File;

import org.w0a.HTMLEdit;
import org.w4a.W4AIniFile;
import org.w4a.exceptions.NodeModulesDirNotSpecifiedException;
import org.w4a.exceptions.NodeModulesNotMovedBackToOriginalLocationException;
import org.w4a.server.ServerUtils;

@Deprecated
public class RemoveNodeModules implements TaskInterface {
	
	String workarea;
	W4AIniFile iniFile;
	File destination;
	String taskLocationOfNodeModules;
	
	public RemoveNodeModules(String workarea, W4AIniFile iniFile) {
		this.workarea = workarea;
		this.iniFile = iniFile;
	}

	@Override
	public void doPreConditions() throws Exception {
		if ( this.iniFile.getNodeModulesDir() == null ) 
			throw new NodeModulesDirNotSpecifiedException(this.iniFile.getFilename());
//		if ( this.iniFile.getNodeModulesDir().exists() ) 
//			throw new NodeModulesNoUsedException(this.iniFile.getFilename());
	}

	@Override
	public boolean doUseCaseTrigger() throws Exception {
		this.taskLocationOfNodeModules = HTMLEdit.append(workarea, "node_modules");
		return new File(taskLocationOfNodeModules).exists();
	}

	@Override
	public void doPrepareUseCase() throws Exception {
		this.destination = this.iniFile.getNodeModulesDir();
	}

	@Override
	public void doWishCase() throws Exception {
		ServerUtils.mv(new File(this.taskLocationOfNodeModules),this.destination.getPath());
	}

	@Override
	public void doCleanupUseCase() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doPostConditions() throws Exception {
		if ( new File(taskLocationOfNodeModules).exists() )
			throw new NodeModulesNotMovedBackToOriginalLocationException(this.destination.getPath());
		if ( !this.destination.exists() )
			throw new NodeModulesNotMovedBackToOriginalLocationException(this.destination.getPath());
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
