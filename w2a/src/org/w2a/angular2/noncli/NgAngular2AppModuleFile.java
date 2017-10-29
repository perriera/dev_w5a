package org.w2a.angular2.noncli;

import org.w0a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.angular2.NgAngular2Interface;
import org.w2a.ngmodule.NgAppModule;
import org.w2a.ngsymbol.NgSymbol;

public class NgAngular2AppModuleFile implements NgAngular2Interface {

	W2AIniFile iniFile;
	String directory;
	
	public NgAngular2AppModuleFile(W2AIniFile iniFile) {
		this.iniFile = iniFile;
	}

	@Override
	public void input(String before) throws Exception {
		this.directory = before;
	}

	@Override
	public String output() throws Exception {
		
		String appDir = this.iniFile.getAppDir();
		String desDir = iniFile.getDestination();
		String prelude = HTMLEdit.append(desDir, appDir);
		
		String[] parts = this.directory.split("/");
		String specFilename = parts[parts.length-1] + ".component";
		String path = HTMLEdit.append(this.directory, specFilename).replace(prelude, "");

		String includeText = path;
		String identifierText = NgSymbol.nonCamelToCamel(specFilename);

		String importString = "import { ((identifierText)) } from './((includeText))';";
		importString = importString.replace("((includeText))", includeText);
		importString = importString.replace("((identifierText))", identifierText);

		NgAppModule appModule = new NgAppModule(this.iniFile);
		appModule.readFile();
		appModule.getAppImports().input(importString);
		appModule.getNgModule().addDirective("declarations",identifierText);
		appModule.writeFile();
		
		return null;
		
	}
	
//	String extension = ".ts";
//	String[] parts = this.directory.split("/");
//	String specFilename = parts[parts.length-1] + ".component"+extension;
//	String path = HTMLEdit.append(this.directory, specFilename);
//	String includeText = specFilename.replace(extension, "");
//	String identifierText = NgSymbol.nonCamelToCamel(includeText);
//	
//	includeText = includeText.replace(".component", "");
//	
//	String contents = NgTextFile.input("files/templates/angular2/cli/template"+extension);
//	contents = contents.replace("((includeText))", includeText);
//	contents = contents.replace("((identifierText))", identifierText);
//	
//	NgTextFile.output(contents, path);
//	
//	return null;

}
