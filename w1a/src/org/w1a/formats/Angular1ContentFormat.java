package org.w1a.formats;

import org.w0a.extractor.SourceExtractor;
import org.w0a.interfaces.SourceEntryInterface;
import org.w0a.types.SourceHTMLFileEntry;
import org.w0a.types.SourceIndexFileEntry;
import org.w1a.W1AIniFile;


@SuppressWarnings("serial")
public class Angular1ContentFormat extends Angular1BodyFormat {

	public Angular1ContentFormat(SourceExtractor extractor) {
		super(extractor);
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
					extractor.put(entry.getKey(), new Angular1IndexContentFileEntry(e));	
				} else {
					if ( entry.getValue() instanceof SourceHTMLFileEntry ) {
						SourceHTMLFileEntry e = (SourceHTMLFileEntry)entry.getValue();
						extractor.put(entry.getKey(), new Angular1HTMLContentFileEntry(e));	
					}
				}
			}
		}
	}
	
}
