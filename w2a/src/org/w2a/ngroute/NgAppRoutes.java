package org.w2a.ngroute;

import java.util.SortedSet;
import java.util.TreeSet;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w0a.IniFile;
import org.w0a.interfaces.IniFileInterface;
import org.w0a.interfaces.ObjectCompilerInterface;
import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.ngmodule.NgAppModule;
import org.w2a.ngmodule.NgAppRoutingModule;

public class NgAppRoutes implements ObjectCompilerInterface<String>, IniFileInterface {

	String contents;
	NgAppRoutingModule ngAppRoutingModule;
	NgAppModule ngAppModule;
	W2AIniFile iniFile;

	public NgAppRoutes(String contents, NgAppRoutingModule ngAppRoutingModule, NgAppModule ngAppModule,
			W2AIniFile iniFile) {
		this.contents = contents;
		this.ngAppRoutingModule = ngAppRoutingModule;
		this.ngAppModule = ngAppModule;
		this.iniFile = iniFile;
	}

	@Override
	public IniFile getXIniFile() {
		return iniFile;
	}

	@Override
	public void input(String text) throws Exception {
	}

	@Override
	public String output() throws Exception {
		SortedSet<NgImport> values = new TreeSet<NgImport>(ngAppModule.getAppImports().values());
		String results = "";
		for (NgImport ngImport : values ) {
			if ( ngImport.isPage() ) {
				String appRoute = ngImport.getAppRoute();
				results += "\t"+appRoute+",\n";
			}
		}
		this.contents = "const appRoutes: Routes = [\n\t"+results.trim()+"\n];";
		return this.contents;
	}

	//
	// Static Members below ...
	//

	/**
	 * convertLink()
	 * 
	 * Take a standard HREF value and convert it into a format that the Routes
	 * class of the Angular 2 framework can understand.
	 * 
	 * String before = "../../about-cpp/about-cpp.html"; String after =
	 * "/about_cpp_about_cpp_component";
	 * 
	 * @param before
	 * @return
	 */

	public static String adjustLink(String before) {
		String dotsRemoved = before.replace("../", "");
		String SlashReplaced = dotsRemoved.replace("/", "_");
		String htmlReplaced = SlashReplaced.replace(".html", "");
		if (htmlReplaced.equals("index"))
			htmlReplaced = "";
		htmlReplaced = NgImport.removeDuplicate(htmlReplaced, "_");
		String hyphenReplaced = htmlReplaced.replace("-", "_");
		String SlashAdded = "/" + hyphenReplaced;
		return SlashAdded;
	}

	public static String adjustAHRefsWithRouterLinks(String html, W2AIniFile iniFile) throws Exception {
		Document doc = HTMLEdit.parse(html);
		Elements links = doc.select("a[href]");
		for (Element link : links) {
			String hrefValue = link.attr("href");
			if ( !(hrefValue.startsWith("http://") || hrefValue.startsWith("https://")) ) {
				String routerLinkValue = NgAppRoutes.adjustLink(hrefValue);
				link.attr("routerLink", routerLinkValue);
				link.removeAttr("href");
			}
		}
		String editted = doc.toString();
		return editted;
	}

	public static String adjustRouterLinksWithParameters(String html, W2AIniFile iniFile) throws Exception {
		Document doc = HTMLEdit.parse(html);
		Elements links = doc.select("a[routerLink]");
		for (Element link : links) {
			String routerLinkValue = link.attr("routerLink");
			String parameters = "";
			for ( int i=0; i<9; i++ ) {
				String parameter = link.attr("ng-parameter"+(i+1));
				if ( parameter.length()>0 ) {
					if ( !routerLinkValue.contains(parameter) ) {
						parameters += "/"+parameter;
					}
					link.removeAttr("ng-parameter"+(i+1));
				}
			}
			routerLinkValue += parameters;
			link.attr("routerLink", routerLinkValue);
		}
		String editted = doc.body().toString();
		editted = HTMLEdit.stripFirstAndLast(editted);
		return editted;
	}

}
