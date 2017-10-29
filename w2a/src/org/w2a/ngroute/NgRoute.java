package org.w2a.ngroute;

import org.w2a.HTMLEdit;

import java.io.IOException;

import org.w0a.interfaces.ObjectCompilerInterface;
import org.w0a.types.SourceKeyPathInterface;
import org.w2a.W2AIniFile;

public class NgRoute implements ObjectCompilerInterface<String>, SourceKeyPathInterface, Comparable<NgRoute> {

	String key;
	String path;
	String route;
	W2AIniFile iniFile;

	public NgRoute(String text, W2AIniFile iniFile) throws Exception {
		this.iniFile = iniFile;
		this.input(text);
	}

	@Override
	public String getKey() throws Exception {
		return key;
	}

	@Override
	public String getPath() throws Exception {
		return path;
	}

	@Override
	public void input(String text) throws Exception {
		String[] partA = text.split("\\{");
		String firstPart = partA[1];
		String[] partB = firstPart.split("\\}");
		String identifier = partB[0];
		String[] parts = partB[1].split(" ");
		this.key = identifier.trim();
		String[] stripped = parts[2].split("'");
		this.path = stripped[1];
		this.route = getAppRoute();
	}

	@Override
	public String output() throws Exception {
		String out = "";
		out += "import { " + this.key + " } from '" + this.path + "';";
		return out;
	}

	//
	// Special features ...
	//

	static public String removeDuplicate(String path, String marker) {
		String[] parts = path.split(marker);
		if (parts.length > 1 && parts[parts.length - 1].equals(parts[parts.length - 2])) {
			path = "";
			for (int i = 0; i < parts.length - 1; i++)
				path += "_" + parts[i];
			path = path.substring(1);
		}
		return path;
	}

	public String formatURL(String original) throws Exception {
		if (this.key.equals("HomeComponent"))
			return "";
		String viewPage = iniFile.getViewsDir();
		viewPage = HTMLEdit.append("./", viewPage, "/");
		original = original.replace(viewPage, "");
		original = original.replace(".component", "");
		original = removeDuplicate(original, "/");
		original = original.replace("/", "_");
		original = original.replace("-", "_");
		original = original.replace("._", "");
		return original;
	}

	public String getFormattedURL() throws Exception {
		return formatURL(this.path);
	}

	public String getAppRoute() throws Exception {
		String url = getFormattedURL();
		String route = "{ path: '" + url + "', component: " + this.key + "  }";
		return route;
	}

	public String getRoute() {
		return this.route;
	}

	public boolean isPage() throws Exception {
		String pageIdentifier = iniFile.getViewsDir();
		pageIdentifier = HTMLEdit.append("./", pageIdentifier);
		return this.path.startsWith(pageIdentifier);
	}

	public boolean isComponent() {
		return this.key.contains("Component");
	}

	@Override
	public int compareTo(NgRoute other) {
		int result = this.key.compareTo(other.key);
		return result;
	}

	public boolean isAppComponent() {
		return this.key.equals("AppComponent");
	}

	public boolean isPageComponent() throws Exception {
		return isComponent() && isPage();
	}

	public static String moveToBottomOfList(String pattern, String text, W2AIniFile iniFile) throws IOException {
		String body = text.replace("}\n]", "},\n]");
		body = HTMLEdit.stripFirstAndLast(body.trim());
		String[] parts = body.split("\n");
		String lineInQuestion = null;
		String searchFor = "'"+pattern+"'";
		for ( String line : parts ) {
			if ( line.contains(searchFor) ) {
				lineInQuestion = line;
				break;
			}
		}
		if ( lineInQuestion==null )
			return text;
		String results = "const appRoutes: Routes = [\n";
		for ( String line : parts ) {
			if ( !line.contains(searchFor) ) 
				results += line + "\n";
		}
		results += lineInQuestion + "\n";
		results += "];\n";
		results = results.replace("},\n]", "}\n]");
		return results;
	}
		

}
