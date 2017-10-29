package org.w2a.angular2.noncli;

import org.w1a.HTMLEdit;
import org.w2a.angular2.NgAngular2Interface;
import org.w2a.ngtypes.NgTextFile;

public class NgAngular2CSSFile implements NgAngular2Interface {

	String directory;
	
	@Override
	public void input(String before) throws Exception {
		this.directory = before;
	}


	@Override
	public String output() throws Exception {
		
		String[] parts = this.directory.split("/");
		String cssFilename = parts[parts.length-1] + ".component.css";
		String path = HTMLEdit.append(this.directory, cssFilename);
		
		NgTextFile.output("", path);
		
		return null;
	}

}
