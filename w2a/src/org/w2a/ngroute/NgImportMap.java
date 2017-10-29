package org.w2a.ngroute;

import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import org.w0a.interfaces.ObjectCompilerInterface;
import org.w2a.W2AIniFile;

@SuppressWarnings("serial")
public class NgImportMap extends HashMap<String, NgImport> implements ObjectCompilerInterface<String> {

	W2AIniFile iniFile;
	
	public NgImportMap(W2AIniFile iniFile) throws Exception {
		this.iniFile = iniFile;
	}
	
	public NgImportMap(String text, W2AIniFile iniFile) throws Exception {
		this.input(text);
		this.iniFile = iniFile;
	}
	
	@Override
	public void input(String text) throws Exception {
		String[] parts = text.split("\n");
		// this.clear();
		for ( String line : parts ) {
			if (line.length()==0 )
				return;
			NgImport route = new NgImport(line,iniFile);
			this.put(route.getKey(), route);
		}
	}

	@Override
	public String output() throws Exception {
		SortedSet<NgImport> values = new TreeSet<NgImport>(this.values());
		String output = "";
		for ( NgImport value : values ) {
			value.output();
			String out = value.output();
			output += out + "\n";
		}
		return output;
	}
	
	public void put(NgImport route) throws Exception {
		this.put(route.getKey(), route);
	}
	
}
