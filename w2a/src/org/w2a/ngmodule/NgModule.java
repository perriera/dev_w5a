package org.w2a.ngmodule;

import java.util.HashMap;
import org.w0a.interfaces.ObjectCompilerInterface;
import org.w2a.HTMLEdit;

public class NgModule implements ObjectCompilerInterface<String>  {

	@SuppressWarnings("serial")
	class ParametersMap extends HashMap<String, HashMap<String,String>> {};
	ParametersMap parametersMap = new ParametersMap();
	String[] identifiers = { "declarations", "imports","exports","providers","bootstrap" };	
	
	public void addDirective(String section, String value) {
		if (parametersMap.get(section) == null)
			parametersMap.put(section, new HashMap<String,String>());
		parametersMap.get(section).put(value, value);
	}
	
	void addDirectives(String section, HashMap<String,String> map) {
		if (parametersMap.get(section) == null)
			parametersMap.put(section, new HashMap<String,String>());
		parametersMap.get(section).putAll(map);
	}
	
	@Override
	public void input(String text) throws Exception {
		String json = HTMLEdit.getJsonFromNgModule(text);
		for ( String identifier : identifiers )
			if ( json.contains(identifier+":"))
				this.addDirectives(identifier, HTMLEdit.getJSONMap(json, identifier));
	}

	@Override
	public String output() throws Exception {
		String output = "";
		for ( String identifier : identifiers ) {
			HashMap<String,String> map = this.parametersMap.get(identifier);
			if ( map!=null )
				output += HTMLEdit.formatJSONMap(map, identifier);
		}
		String json = HTMLEdit.formatNgModuleMap(output.trim());
		return json;
	}

	public void clear() {
		this.parametersMap.clear();
	}

}

