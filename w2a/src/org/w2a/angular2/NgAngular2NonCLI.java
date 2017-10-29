package org.w2a.angular2;

import java.io.File;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.angular2.noncli.NgAngular2AppModuleFile;
import org.w2a.angular2.noncli.NgAngular2CSSFile;
import org.w2a.angular2.noncli.NgAngular2HTMLFile;
import org.w2a.angular2.noncli.NgAngular2SpecFile;
import org.w2a.angular2.noncli.NgAngular2TsFile;
import org.w2a.exceptions.NotAnAppComponentDirectoryException;
import org.w2a.ngfile.NgFile;

public class NgAngular2NonCLI implements NgAngular2Interface {

	W2AIniFile iniFile;
	NgFile ngFile;
	String directory;
	NgAngular2Interface[] array;

	public NgAngular2NonCLI(W2AIniFile iniFile, NgFile ngFile) {
		this.iniFile = iniFile;
		this.ngFile = ngFile;
	}

	public NgAngular2NonCLI(W2AIniFile iniFile) {
		this.iniFile = iniFile;
	}

	@Override
	public void input(String directory) throws Exception {

		this.directory = directory;

		NgAngular2Interface[] array = { new NgAngular2CSSFile(), new NgAngular2HTMLFile(), new NgAngular2SpecFile(),
				new NgAngular2TsFile(), new NgAngular2AppModuleFile(this.iniFile) };

		String desDir = iniFile.getDestination();
		String appDir = iniFile.getAppDir();
		String compName = ngFile.getComponentName();
		String where = HTMLEdit.append(directory, compName);
		String z = HTMLEdit.append(desDir, appDir, where);

		File path = new File(z);
		this.directory = path.getPath();

		for (NgAngular2Interface step : array) {
			step.input(this.directory);
		}

		this.array = array;

	}

	@Override
	public String output() throws Exception {

		String desDir = iniFile.getDestination();
		String componentFilename = ngFile.getComponentDir();

		File test = new File(componentFilename);
		if (!test.exists()) {

			String testAppDir = HTMLEdit.append(desDir, "app/app.component.html");
			if (!new File(testAppDir).exists())
				throw new NotAnAppComponentDirectoryException("AppComponent", desDir);

			File path = new File(this.directory);
			path.mkdirs();

			for (NgAngular2Interface step : array) {
				step.output();
			}

		}

		return directory;

	}

}
