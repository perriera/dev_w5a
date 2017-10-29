package org.w2a.angular2.noncli;

import org.w2a.HTMLEdit;
import org.w2a.angular2.NgAngular2Interface;
import org.w2a.ngsymbol.NgSymbol;
import org.w2a.ngtypes.NgTextFile;

public class NgAngular2SpecFile implements NgAngular2Interface {

	String directory;
	
	@Override
	public void input(String before) throws Exception {
		this.directory = before;
	}


	@Override
	public String output() throws Exception {
		
		String extension = ".spec.ts";
		String[] parts = this.directory.split("/");
		String specFilename = parts[parts.length-1] + ".component"+extension;
		String path = HTMLEdit.append(this.directory, specFilename);
		String includeText = specFilename.replace(extension, "");
		String identifierText = NgSymbol.nonCamelToCamel(includeText);
		
		String contents = HTMLEdit.loadLocalFile("../w2a/files/templates/angular2/cli/template"+extension);
		
		contents = contents.replace("((includeText))", includeText);
		contents = contents.replace("((identifierText))", identifierText);
		
		NgTextFile.output(contents, path);
		
		return null;

	}

}
