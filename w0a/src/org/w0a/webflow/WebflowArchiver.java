package org.w0a.webflow;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.w0a.exceptions.FeatureNotImplementedException;
import org.w0a.interfaces.IniFileInterface;
import org.w0a.interfaces.SourceEntryInterface;
import org.w0a.types.SourceHTMLFileEntry;

@SuppressWarnings("serial")
public class WebflowArchiver extends WebflowExtractor {

	public WebflowArchiver(IniFileInterface iniFile) {
		super(iniFile);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.w0a.base.SourceArchiveInterface#input(org.w0a.IniFile)
	 */
	
	@Override
	public void input() throws Exception {
		super.input();
		String srcDir = getXIniFile().getDestination();
		
		for ( Entry<String, SourceEntryInterface> entry : this ) {
			String file = srcDir+entry.getKey();
			if ( entry.getValue() instanceof SourceHTMLFileEntry ) {
				try {
					FileInputStream fis = new FileInputStream(file);
					((SourceHTMLFileEntry)entry.getValue()).input(fis);
					fis.close();
				}
				catch ( FileNotFoundException e) {
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
		throw new FeatureNotImplementedException();
	}

}
