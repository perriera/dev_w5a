package org.w2a.ngmodule;

import java.io.File;

import org.w0a.interfaces.ObjectCompilerInterface;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.exceptions.IniFileDoesNotHaveAnAccurateViewDirsValueException;
import org.w2a.ngfile.NgFileInterface;
import org.w2a.ngroute.NgImport;

public class NgAppRoutingModule extends NgAppModuleBase  implements ObjectCompilerInterface<String>, NgFileInterface  {
		
	NgAppModule ngAppModule;
	
	public NgAppRoutingModule(NgAppModule ngAppModule, W2AIniFile iniFile) throws Exception {
		super(iniFile);
		this.ngAppModule = ngAppModule;
		this.input(ngAppModule.output());
	}

	@Override
	public String getHTMLAssetFilename() throws Exception {
		String filename = HTMLEdit.append(iniFile.getDestination(), "app/app-routing.module.ts");
		return filename;
	}
	
	void enhance() throws Exception {
		
		this.sysImports.clear();
		this.sysImports.input("import { NgModule } from '@angular/core';");
		this.sysImports.input("import { Routes, RouterModule } from '@angular/router';");
		
		this.ngModule.clear();
		this.ngModule.addDirective("imports", "RouterModule.forRoot(appRoutes)");
		this.ngModule.addDirective("exports", "RouterModule");
		
	}

	@Override
	public String output() throws Exception {
		
		String sysImports = this.sysImports.output();
		
		String appImports = "";
		boolean informUserOfABadValueinViewsDirOfTheIniFile = true;
		for (NgImport ngImport : this.appImports.values() ) {
			if ( ngImport.isPageComponent() ) {
				informUserOfABadValueinViewsDirOfTheIniFile = false;
				appImports += ngImport.output()+"\n";
			}
		}
		if ( this.appImports.size() >2 && informUserOfABadValueinViewsDirOfTheIniFile )
			throw new IniFileDoesNotHaveAnAccurateViewDirsValueException(iniFile);
		
		String appRoutes = "const appRoutes: Routes = [\n";
		for (NgImport ngImport : this.appImports.values()) {
			if (ngImport.isPageComponent())
				appRoutes += "\t"+ngImport.getRoute()+",\n";
		}
		appRoutes += "];\n";
		
		String ngModule = this.ngModule.output();
		String output = sysImports + "\n" + appImports + "\n" + appRoutes + "\n" + ngModule;
		output += "export class AppRoutingModule { }\n";
		
		return output;
	}

	public static boolean newProject(W2AIniFile iniFile) throws Exception {
		String filename = HTMLEdit.append(iniFile.getDestination(), "app/app-routing.module.ts");
		File test = new File(filename);
		return !test.exists();
	}

}
