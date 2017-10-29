package org.w2a.angular2;

import java.io.File;
import java.util.Date;

import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.exceptions.Angular2CLINgException;
import org.w2a.exceptions.Angular2CLINgTimedOutException;
import org.w2a.exceptions.NotAnAppComponentDirectoryException;
import org.w2a.exceptions.YouHaveToBeInsideAnAngularCliProjectException;
import org.w2a.ngfile.NgFile;

public class NgAngular2CLI implements NgAngular2Interface {

	W2AIniFile iniFile;
	NgFile ngFile;
	String directory;
	
	public NgAngular2CLI(W2AIniFile iniFile, NgFile ngFile) {
		this.iniFile = iniFile;
		this.ngFile = ngFile;
	}

	public NgAngular2CLI(W2AIniFile iniFile) {
		this.iniFile = iniFile;
		this.ngFile = new NgFile(this.iniFile);
	}

	@Override
	public void input(String directory) throws Exception {
		this.directory = directory;
	}
	
	@Override
	public String output() throws Exception {
		
		String desDir = iniFile.getDestination();
		String componentFilename = ngFile.getComponentDir();
		
		File test = new File(componentFilename);
		if (!test.exists()) {
			
			String testAppDir=HTMLEdit.append(desDir,"app/app.component.html");
			if ( !new File(testAppDir).exists() )
				throw new NotAnAppComponentDirectoryException("AppComponent",desDir);
			
			String compName = ngFile.getComponentName();
			String where = HTMLEdit.append(directory, compName);
			String nodeCmd = iniFile.getNodeGCCommand(where);
			Process p = Runtime.getRuntime().exec(nodeCmd, null, new File(desDir));
			while (!test.exists()) {
				long timeout = new Date().getTime() + 30*1000;
				String line = HTMLEdit.getOutput(p.getInputStream());
				if ( line==null ) {
					line = HTMLEdit.getOutput(p.getErrorStream());
					if ( line!=null && line.equals("You have to be inside an angular-cli project in order to use the generate command.") )
						throw new YouHaveToBeInsideAnAngularCliProjectException();
					else 
						throw new Angular2CLINgException(line,componentFilename);
				}
				Thread.sleep(100);
				long currentTime = new Date().getTime();
				if ( currentTime>timeout )
					throw new Angular2CLINgTimedOutException(line,componentFilename);
			}
		}
		
		return directory;
		
	}

}
