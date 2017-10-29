package org.w2a.ngroute;

import java.util.HashMap;
import org.w0a.interfaces.ObjectCompilerInterface;
import org.w2a.W2AIniFile;

@SuppressWarnings("serial")
public class NgRouteMap extends HashMap<String, NgRoute> implements ObjectCompilerInterface<String> {

	W2AIniFile iniFile;
	
	public NgRouteMap(W2AIniFile iniFile) throws Exception {
		this.iniFile = iniFile;
	}
	
	public NgRouteMap(String text, W2AIniFile iniFile) throws Exception {
		this.input(text);
		this.iniFile = iniFile;
	}
	
	@Override
	public void input(String text) throws Exception {
//		String[] parts = text.split("\n");
//		// this.clear();
//		for ( String line : parts ) {
//			if (line.length()==0 )
//				return;
//			NgRoute route = new NgRoute(line,iniFile);
//			this.put(route.getKey(), route);
//		}
	}

	@Override
	public String output() throws Exception {
		String output = "";
		for ( NgRoute value : this.values() ) {
			value.output();
			String out = value.output();
			output += out + "\n";
		}
		return output;
	}
	
	public void put(NgRoute route) throws Exception {
		this.put(route.getKey(), route);
	}
	
}
