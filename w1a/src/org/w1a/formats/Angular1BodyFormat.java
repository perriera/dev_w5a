package org.w1a.formats;

import java.io.PrintWriter;
import org.w0a.extractor.SourceExtractor;
import org.w0a.interfaces.SourceEntryInterface;
import org.w0a.types.SourceHTMLFileEntry;
import org.w0a.types.SourceIndexFileEntry;
import org.w1a.HTMLEdit;
import org.w1a.W1AIniFile;

@SuppressWarnings("serial")
public class Angular1BodyFormat extends SourceExtractor {

	protected SourceExtractor extractor;
	
	public Angular1BodyFormat(SourceExtractor extractor) {
		this.extractor = extractor;
	}
	
	@Override
	public W1AIniFile getXIniFile() {
		return (W1AIniFile)extractor.getXIniFile();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.w0a.base.SourceArchiveInterface#input(org.w0a.IniFile)
	 */
	
	@Override
	public void input() throws Exception {
		extractor.input();
		for ( Entry<String, SourceEntryInterface> entry : extractor ) {
			String key = entry.getKey();
			if ( !getXIniFile().isIgnored(key) ) {
				if ( entry.getValue() instanceof SourceIndexFileEntry ) {
					SourceIndexFileEntry e = (SourceIndexFileEntry)entry.getValue();
					extractor.put(entry.getKey(), new Angular1IndexBodyFileEntry(e));	
				} else {
					if ( entry.getValue() instanceof SourceHTMLFileEntry ) {
						SourceHTMLFileEntry e = (SourceHTMLFileEntry)entry.getValue();
						extractor.put(entry.getKey(), new Angular1HTMLBodyFileEntry(e));	
					}
				}
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.w0a.base.SourceArchiveInterface#output(org.w0a.IniFile)
	 */
	
	@Override
	public void output() throws Exception {
		extractor.output();
		createRoutesFile();
	}

	private void createRoutesFile() throws Exception {
		
		/*
		 ** routes.js
		 **
		 ** Purpose: The source file for all Angular routes classes that deal
		 * explcitly with config functions.
		 **
		 */

		String routesFile = getXIniFile().getRoutesFile();
		String desDir = getXIniFile().getDestination();
		String fn = HTMLEdit.writeDirectory(desDir,routesFile);

		PrintWriter writer = new PrintWriter(fn, "UTF-8");
		writer.println("/* " + this.getClass().getName() + " generated file */");
		writer.println();
		writer.println("webFlowApp.config(function ($routeProvider) {");
		writer.println();
		writer.println("\t$routeProvider");
		writer.println();

		for ( Entry<String, SourceEntryInterface> entry : extractor ) {
			String key = entry.getKey();
			SourceEntryInterface value = entry.getValue();
			if ( value instanceof SourceHTMLFileEntry ) {
				if ( !getXIniFile().isIgnored(key) ) {
					String link = "/";
					if ( !(value instanceof SourceIndexFileEntry) )
						link += HTMLEdit.getLink(key);
					if (key.equals("index.html")) {
						link = "/";
						key = key.replace(".html", ".htm");
					}
					writer.println("\t.when('" + link + "', { templateUrl: '" + key + "' })");
				}
			}
		}


		writer.println();
		writer.println("});");
		writer.println();
		
		/*
		 ** .JS Confirmation
		 */

		writer.println("console.log('"+routesFile+" loaded');");
		writer.println();

		writer.close();
		
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cleanup() throws Exception {
		// TODO Auto-generated method stub
		
	}


	
}
