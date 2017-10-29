package org.w2a.ngmodule;

import org.w0a.exceptions.FeatureNotRequiredException;
import org.w0a.interfaces.ObjectCompilerInterface;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.ngfile.NgFile;
import org.w2a.ngfile.NgFileInterface;
import org.w2a.ngroute.NgImport;
import org.w2a.ngroute.NgImportMap;
import org.w2a.ngtypes.NgTextFile;

abstract public class NgAppModuleBase implements ObjectCompilerInterface<String>, NgFileInterface  {

	NgImportMap sysImports;
	NgImportMap appImports;
	NgModule ngModule;
	W2AIniFile iniFile;
	
	public NgAppModuleBase(W2AIniFile iniFile) throws Exception {
		this.sysImports = new NgImportMap(iniFile);
		this.appImports = new NgImportMap(iniFile);
		this.ngModule = new NgModule();
		this.iniFile = iniFile;
	}
	
	@Override
	public NgFile getNgFile() throws Exception {
		throw new FeatureNotRequiredException();
	}
	
	public NgImportMap getSysImports() {
		return sysImports;
	}

	public NgImportMap getAppImports() {
		return appImports;
	}

	public NgModule getNgModule() {
		return ngModule;
	}

	public W2AIniFile getIniFile() {
		return iniFile;
	}
	
	public String readFile() throws Exception {
		String contents = NgTextFile.input(getHTMLAssetFilename(), iniFile);
		input(contents);
		return contents;
	}

	
	public void writeFile() throws Exception {
		NgTextFile.output(this.output(),getHTMLAssetFilename());
	}

	public String readFile(String filename) throws Exception {
		String contents = HTMLEdit.loadLocalFile(filename);
		input(contents);
		return contents;
	}

	@Override
	public void input(String contents) throws Exception {
		String[] sections = contents.split("\n\n");
		this.sysImports.input(sections[0]);
		this.appImports.input(sections[1]);
		this.ngModule.input(sections[2]);
		this.enhance();
	}
	
	abstract void enhance() throws Exception;
	
	public void addAppImport(NgImport route) throws Exception {
		this.appImports.input(route.output());
	}
	
	public void addSysImport(NgImport route) throws Exception {
		this.sysImports.input(route.output());
	}
	
	public void addImport(String string) throws Exception {
		NgImport anImport = new NgImport(string, iniFile);
		addImport(anImport);
	}

	public void addImport(NgImport route) throws Exception {
		if ( route.getPath().startsWith("@") )
			addSysImport(route);
		else
			addAppImport(route);
	}

	public void addModule(String section, String value) {
		this.ngModule.addDirective(section, value);
	}
	
}
