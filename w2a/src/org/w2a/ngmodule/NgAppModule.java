package org.w2a.ngmodule;

import org.w0a.interfaces.ObjectCompilerInterface;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.ngfile.NgFileInterface;
import org.w2a.ngroute.NgImport;

public class NgAppModule extends NgAppModuleBase implements ObjectCompilerInterface<String>, NgFileInterface {
	

	public NgAppModule(W2AIniFile iniFile) throws Exception {
		super(iniFile);
	}

	@Override
	public String getHTMLAssetFilename() throws Exception {
		String filename = HTMLEdit.append(iniFile.getDestination(), "app/app.module.ts");
		return filename;
	}
	
	void enhance() throws Exception {
		
		this.sysImports.input("import { RouterModule } from '@angular/router';");
		this.sysImports.input("import { APP_BASE_HREF } from '@angular/common';");
		
		this.appImports.input("import { AppComponent } from './app.component';");
		this.appImports.input("import { AppRoutingModule } from './app-routing.module';");
		
		for (NgImport ngImport : this.appImports.values() ) {
			if ( ngImport.isComponent() )
				this.ngModule.addDirective("declarations", ngImport.getKey());
		}
		
		this.ngModule.addDirective("imports","RouterModule");
		this.ngModule.addDirective("imports","AppRoutingModule");
		this.ngModule.addDirective("bootstrap","AppComponent");
		
		//
		// TODO: Put an option in W2AIniFile to set the value of APP_BASE_HREF
		//
		
		this.ngModule.addDirective("providers","{'useValue':'/','provide':'APP_BASE_HREF'}");
		
	}

	@Override
	public String output() throws Exception {
		String sysImports = this.sysImports.output();
		String appImports = this.appImports.output();
		String ngModule = this.ngModule.output();
		String output = sysImports + "\n" + appImports + "\n" + ngModule;
		output += "export class AppModule { }\n";
		return output;
	}

}
