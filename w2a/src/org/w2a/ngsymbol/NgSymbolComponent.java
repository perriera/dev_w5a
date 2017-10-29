package org.w2a.ngsymbol;

import org.w2a.W2AIniFile;
import org.w2a.ngcomponent.NgComponent;

public class NgSymbolComponent extends NgComponent {

	public NgSymbolComponent(String key, String newContent, W2AIniFile iniFile) throws Exception {
		super(key, newContent, iniFile.getSymbolsDir(), iniFile);
	}

	@Override
	public boolean isExternalPage() {
		// TODO Auto-generated method stub
		return false;
	}

}
