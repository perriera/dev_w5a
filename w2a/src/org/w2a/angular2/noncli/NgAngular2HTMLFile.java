package org.w2a.angular2.noncli;

import org.w2a.HTMLEdit;
import org.w2a.angular2.NgAngular2Interface;
import org.w2a.ngtypes.NgTextFile;

public class NgAngular2HTMLFile implements NgAngular2Interface {

	String directory;
	
	@Override
	public void input(String before) throws Exception {
		this.directory = before;
	}


	@Override
	public String output() throws Exception {
		
		String contents = HTMLEdit.loadLocalFile("../w2a/files/templates/angular2/cli/template.html");
		
		String[] parts = this.directory.split("/");
		String cssFilename = parts[parts.length-1] + ".component.html";
		String path = HTMLEdit.append(this.directory, cssFilename);
		
		NgTextFile.output(contents, path);
		
		return null;
		
	}

}
