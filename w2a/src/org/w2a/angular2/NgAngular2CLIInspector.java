package org.w2a.angular2;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;

import org.w2a.W2AIniFile;
import org.w2a.ngfile.NgFile;

public class NgAngular2CLIInspector extends NgAngular2CLI implements NgAngular2Interface {

	public NgAngular2CLIInspector(W2AIniFile iniFile, NgFile ngFile) {
		super(iniFile,ngFile);
	}

	@Override
	public String output() throws Exception {
		
		if ( !iniFile.getNonCli() )
			return super.output();
		
//		String desDir = iniFile.getDestination();
//		String componentFilename = ngFile.getComponentDir();
		
		inspect();

		return directory;
		
	}
	
	void inspect() throws Exception {
		
		String desDir = iniFile.getDestination();
//		String componentFilename = ngFile.getComponentDir();
		// ./mock-project/src/app/pages/about-cpp/bjarne/frequently-requested-papers/frequently-requested-papers-4/frequently-requested-papers-4.component.html
		// FrequentlyRequestedPapers4Component
		
		desDir = desDir.replace("/src", "");
		HashMap<String, File> map1 = loadDirectory(new HashMap<String, File>(), new File(desDir));
		super.output();
		HashMap<String, File> map2 = loadDirectory(new HashMap<String, File>(), new File(desDir));
		compare(map1,map2);
		
	}
	
	void compare(HashMap<String, File> map1, HashMap<String, File>  map2) throws Exception {
		
		HashMap<String, File> diffMap1 = newFileListings(map1,map2);
		for ( String path : diffMap1.keySet() ) {
			System.out.println(path);
		}
		
		try {
		HashMap<String, File> diffMap2 = changedFileListings(map1,map2);
		for ( String path : diffMap2.keySet() ) {
			System.out.println(path);
		}
		} catch ( Exception e) {
			System.out.println(e);
		}
		
		System.out.println("x");
	}

	HashMap<String, File> loadDirectory(HashMap<String, File> map, File folder) {
		File[] listOfFiles = folder.listFiles();
		for ( File file : listOfFiles ) {
			map.put(file.getPath(), file);
			if ( file.isDirectory() )
				loadDirectory(map,file);
			// System.out.println(file.getPath());
		}
		return map;
	}
	
	HashMap<String, File> newFileListings(HashMap<String, File> map1, HashMap<String, File> map2) {
		HashMap<String, File> diffMap = new HashMap<String, File>();
		for ( String path : map2.keySet() ) {
			if ( !map1.containsKey(path) )
				diffMap.put(path, map2.get(path));
		}
		return diffMap;
	}
	
	HashMap<String, File> changedFileListings(HashMap<String, File> map1, HashMap<String, File> map2) {
		HashMap<String, File> diffMap = new HashMap<String, File>();
		System.out.println(map1.size());
		System.out.println(map2.size());
		for ( Entry<String,File> entry : map1.entrySet() ) {
			File file1 = entry.getValue();
			File file2 = map2.get(entry.getKey());
			if ( file2==null ) {
				System.out.println(file1.getPath());
				continue;
			}
			long test1 = file1.lastModified();
			long test2 = file2.lastModified();
			if ( file1!=null && file2!=null && test1!=test2 ) {
				diffMap.put(entry.getKey(), file2);
			}
		}
		return diffMap;
	}
	
	
}
