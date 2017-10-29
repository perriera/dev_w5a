package org.w2a.ngmodule;

import java.io.IOException;

import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.ngroute.NgImport;
import org.w2a.ngroute.NgRoute;

public class NgAppReusableRoutingModule extends NgAppModuleBase {

	NgAppModule ngAppModule;
	NgAppRoutingModule ngAppRoutingModule;
	String userEdittedAppRoutes;
	String ngAppRoutingModuleOutput;
	String[] sections;
	
	public NgAppReusableRoutingModule(NgAppModule ngAppModule, W2AIniFile iniFile) throws Exception {
		super(iniFile);
		this.ngAppModule = ngAppModule;
		this.ngAppRoutingModule = new NgAppRoutingModule(ngAppModule,iniFile);
	}

	@Override
	public String getHTMLAssetFilename() throws Exception {
		return ngAppRoutingModule.getHTMLAssetFilename();
	}
	
	@Override
	public void input(String contents) throws Exception {
		
		// TODO: Implement a STRICT formatting checker 
		
		this.sections = contents.split("\n\n");
		this.sysImports.input(sections[0]);
		this.appImports.input(sections[1]);
		this.userEdittedAppRoutes = sections[2];
		this.ngModule.input(sections[3]);
		
		String ngAppModuleOutput = ngAppModule.output();
		this.ngAppRoutingModule.input(ngAppModuleOutput); 
		this.ngAppRoutingModuleOutput = this.ngAppRoutingModule.output();
		
	}

	String updateSysImportsSection(String appModuleSysImports) throws Exception {
		String userEdittedSysImports = "";
		for (NgImport ngImport : this.sysImports.values()) {
			if ( !appModuleSysImports.contains(ngImport.output()) )
				userEdittedSysImports += ngImport.output()+",\n";
		}
		String updatedSysImports = userEdittedSysImports + appModuleSysImports;
		return updatedSysImports;	
	}
	
	String updateAppImportsSection(String appModuleAppImports) throws Exception {
		String userEdittedAppImports = "";
		for (NgImport ngImport : this.appImports.values()) {
			if ( !appModuleAppImports.contains(ngImport.output()) )
				userEdittedAppImports += ngImport.output()+"\n";
		}
		String updatedAppImports = userEdittedAppImports + appModuleAppImports;
		return updatedAppImports;	
	}
	
	String adjust404Issue(String before) throws IOException {
		String after = before;
		after = NgRoute.moveToBottomOfList("404",before, this.getIniFile());
		after = NgRoute.moveToBottomOfList("**",after, this.getIniFile());
		return after;
	}

	String updateAppRoutesSection(String userEdittedAppRoutes) throws Exception {
		
		//
		// TODO: Write a test to ensure that there is ALWAYS a trailing comma 
		//       at the end of the old/preserved routes.
		//
	
		if ( userEdittedAppRoutes.trim().contains("}\n];") )
			userEdittedAppRoutes = userEdittedAppRoutes.replace("}\n];", "},\n];");

		String newAppRoutes = "";
		for (NgImport ngImport : this.ngAppModule.appImports.values()) {
			if (ngImport.isPage() && ngImport.isComponent())
				if ( !userEdittedAppRoutes.contains("component: "+ngImport.getKey()) ) {
					String route = ngImport.getRoute();
					newAppRoutes += "\t"+route+",\n";
				}
		}
		String updatedAppRoutes = HTMLEdit.stripFirstAndLast(userEdittedAppRoutes);
		updatedAppRoutes = "const appRoutes: Routes = [\n" + updatedAppRoutes;		
		updatedAppRoutes += newAppRoutes + "];";
		
		updatedAppRoutes = adjust404Issue(updatedAppRoutes).trim();
		
		if ( updatedAppRoutes.trim().contains("},\n];") )
			updatedAppRoutes = updatedAppRoutes.replace("},\n];", "}\n];");

		return updatedAppRoutes;
	}

	String updateNgModuleSection(String oldNgModuleSection) throws Exception { 
		String userEdittedNgModuleSection = this.ngModule.output();
		if ( userEdittedNgModuleSection.length()==0 )
			return oldNgModuleSection;
		userEdittedNgModuleSection += "export class AppRoutingModule { }\n";
		return userEdittedNgModuleSection;
	}
	
	@Override
	void enhance() throws Exception {
		
		// this.ngAppRoutingModuleOutput = ngAppRoutingModule.output();
		String[] newAppRoutingModuleSections = ngAppRoutingModuleOutput.split("\n\n");
		
		this.sections[0] = updateSysImportsSection(newAppRoutingModuleSections[0]);
		this.sections[1] = updateAppImportsSection(newAppRoutingModuleSections[1]);
		this.sections[2] = updateAppRoutesSection(this.userEdittedAppRoutes);
		this.sections[3] = updateNgModuleSection(newAppRoutingModuleSections[3]);
		
	}
	
	@Override
	public String output() throws Exception {
		this.enhance();
		String output = "";
		for (String section: this.sections ) {
			output += section+"\n\n";
		}
		return output.trim()+"\n";
	}

	
}
