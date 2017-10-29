package org.w2a.ngmodule;

import org.w0a.interfaces.IniFileInterface;
import org.w0a.interfaces.ObjectCompilerInterface;
import org.w0a.interfaces.SourceExtractorInterface;
import org.w2a.W2AIniFile;
import org.w2a.ngtypes.NgTextFile;

public class NgModuleCompilier implements ObjectCompilerInterface<SourceExtractorInterface>, IniFileInterface {

	protected SourceExtractorInterface sourceExtractorInterface;

	@Override
	public W2AIniFile getXIniFile() {
		return (W2AIniFile) sourceExtractorInterface.getXIniFile();
	}
	
	@Override
	public void input(SourceExtractorInterface before) throws Exception {
		this.sourceExtractorInterface = before;
		sourceExtractorInterface.input();
	}

	@Override
	public SourceExtractorInterface output() throws Exception {
		
		sourceExtractorInterface.output();
		 
		NgAppModule appModule = new NgAppModule(getXIniFile());
		appModule.readFile();
		
		String appModuleOutput = appModule.output();
		NgTextFile.output(appModuleOutput, appModule);

		if ( getXIniFile().forwardGenerateAppRoutesOnly() || NgAppRoutingModule.newProject(getXIniFile())) {
			NgAppRoutingModule appRoutingModule = new NgAppRoutingModule(appModule,getXIniFile());
			String appModuleRoutingOutput = appRoutingModule.output();
			NgTextFile.output(appModuleRoutingOutput, appRoutingModule);
		} 
		else {
			NgAppReusableRoutingModule appReusableRoutingModule = new NgAppReusableRoutingModule(appModule,getXIniFile());
			appReusableRoutingModule.readFile();
			String appModuleReusableRoutingOutput = appReusableRoutingModule.output();
			NgTextFile.output(appModuleReusableRoutingOutput, appReusableRoutingModule);
		}
		
		return sourceExtractorInterface;
		
	}

 

}
