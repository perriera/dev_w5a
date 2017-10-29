package org.w4a.tasks;

import java.io.File;

import org.w0a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w4a.W4AIniFile;
import org.w4a.exceptions.SkeletonDoesNotExistException;
import org.w4a.exceptions.SkeletonIniDoesNotExistException;
import org.w4a.exceptions.SkeletonWasNotSetupProperlyException;
import org.w4a.exceptions.WorkAreaDoesNotExistException;
import org.w4a.server.ServerUtils;

public class InjectSkeletonPackage implements TaskInterface {
	
	String workarea;
	W4AIniFile iniFile;
	W2AIniFile w2aIniFile;
	
	public InjectSkeletonPackage(String workarea, W4AIniFile iniFile) {
		this.workarea = workarea;
		this.iniFile = iniFile;
	}

//	protected IniFile getSkeletonIniFile(File webFlowZipFile) throws Exception {
//		String targetDir = webFlowZipFile.getName();
//		targetDir = targetDir.replace(".zip", "");
//		String skeletonProject = "skeleton.zip";
//		String processingDir = this.getIniFile().getProcessingDir();
//		IniFile iniFile = new IniFile("files/skeleton.ini");
//		skeletonProject = "files/" + skeletonProject;
//		iniFile.setSource(skeletonProject);
//		String destinationDir = processingDir + "/" + targetDir;
//		iniFile.setDestination(destinationDir);
//		return iniFile;
//	}
//	
//	protected IniFile getPreviousVersionIniFile(File webFlowZipFile) throws Exception {
//		String targetDir = webFlowZipFile.getName();
//		targetDir = targetDir.replace(".zip", "");
//		String previousVersionProject = this.getIniFile().getPreviousVersion();
//		String processingDir = this.getIniFile().getProcessingDir();
//		IniFile iniFile = new IniFile(findIniFile(webFlowZipFile));
//		iniFile.setSource(previousVersionProject);
//		String destinationDir = processingDir + "/" + targetDir;
//		iniFile.setDestination(destinationDir);
//		return iniFile;
//	}
//	
//	protected IniFile extractSkeletonFile(File webFlowZipFile) throws Exception {
//		IniFile iniFile = getSkeletonIniFile(webFlowZipFile);
//		org.w0a.Main task = new org.w0a.Main(iniFile, false);
//		task.execute();
//		return iniFile;
//	}
//
//	protected IniFile extractPreviousVersion(File webFlowZipFile) throws Exception {
//		extractSkeletonFile(webFlowZipFile);
//		IniFile iniFile = getPreviousVersionIniFile(webFlowZipFile);
//		org.w0a.Main task = new org.w0a.Main(iniFile, false);
//		task.execute();
//		return iniFile;
//	}
	
	@Override
	public void doPreConditions() throws Exception {
		File skeletonFile = iniFile.getSkeletonFile();
		if ( !skeletonFile.exists() ) 
			throw new SkeletonDoesNotExistException(skeletonFile.getPath());
		if ( !new File(workarea).exists() ) 
			throw new WorkAreaDoesNotExistException(workarea);
		File skeletonIniFile = iniFile.getSkeletonIniFile();
		if ( !skeletonIniFile.exists() ) 
			throw new SkeletonIniDoesNotExistException(skeletonIniFile.getPath());
	}

	@Override
	public boolean doUseCaseTrigger() throws Exception {
		String target = HTMLEdit.append(workarea, "skeleton");
		return !new File(target).exists();
	}

	@Override
	public void doPrepareUseCase() throws Exception {
		File skeletonIniFile = this.iniFile.getSkeletonIniFile();
		File path = ServerUtils.cp(skeletonIniFile,this.workarea);
		this.w2aIniFile = new W2AIniFile(path.getPath());
		this.w2aIniFile.setSource(this.iniFile.getSkeletonFile().getPath());
		this.w2aIniFile.setDestination(this.workarea);
	}

	@Override
	public void doWishCase() throws Exception {
		org.w0a.Main task = new org.w0a.Main(this.w2aIniFile, false);
		task.execute();
	}

	@Override
	public void doCleanupUseCase() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doPostConditions() throws Exception {
		String checkPoint = "src/app/app.component.ts";
		File checkFile = new File(HTMLEdit.append(this.workarea, checkPoint));
		if (!checkFile.exists() )
			throw new SkeletonWasNotSetupProperlyException(workarea);
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
