package org.w2a.ngmodule;

import org.w0a.IniFile;
import org.w0a.interfaces.SourceExtractorInterface;

public class NgModuleCompilerLoader implements SourceExtractorInterface {

	SourceExtractorInterface sourceExtractorInterface;
	NgModuleCompilier ngModuleCompilier;
	
	public NgModuleCompilerLoader(SourceExtractorInterface sourceExtractorInterface) {
		this.sourceExtractorInterface = sourceExtractorInterface;
		this.ngModuleCompilier = new NgModuleCompilier();
	}

	@Override
	public IniFile getXIniFile() {
		return sourceExtractorInterface.getXIniFile();
	}

	@Override
	public void input() throws Exception {
		this.ngModuleCompilier.input(sourceExtractorInterface);
	}

	@Override
	public void output() throws Exception {
		this.ngModuleCompilier.output();
	}

	@Override
	public void prepare() throws Exception {
		sourceExtractorInterface.prepare();
	}

	@Override
	public void cleanup() throws Exception {
		sourceExtractorInterface.cleanup();
	}

}
