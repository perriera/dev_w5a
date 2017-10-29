package org.w2a.ngfile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.w2a.HTMLEdit;
import org.w2a.W2AIniFile;

/*
 * NgFile
 * 
 * This class is an attempt to centralize all file naming issues related to 
 * the Angular 2 CLI interface. A great deal of confusion has made itself 
 * readily apparent in the prototyping of this framework arising from the 
 * location and naming of files and components. 
 * 
 * @author perry
 *
 */

public class NgFile {

	String key;
	String directory;
	W2AIniFile iniFile;
	
	public NgFile(String key, String directory, W2AIniFile iniFile) {
		this.key = key;
		this.directory = directory;
		this.iniFile = iniFile;
	}
	
	public NgFile(W2AIniFile iniFile) {
		this.key = "home.html";
		this.directory = "";
		this.iniFile = iniFile;
	}
	
	/**
	 * getComponentName()
	 * 
	 * A component name consists of both the filename (without the extension) and the 
	 * sub directories leading up the filename. It is derived from the key used to 
	 * reference the original HTML file in the Webflow.com archive. It also has a tweak 
	 * such that if the filename is the same as the directory which precedes it then
	 * the filename is returned without the preceding sub directory name. The purpose
	 * of this is to allow components to have a base for that component code above
	 * any sub components that may be within that sub directory.
	 * 
	 * @return
	 */
	
	public String getComponentName() {
		String result = pagesSubDirName(this.key);
		return result;
	}

	public String getComponentHTMLFilename() throws Exception {
		return this.getComponentDir();
	}
	
	public String getComponentDirectory() throws Exception {
		String name = getComponentName()+".component.html";
		String directory = getComponentHTMLFilename().replace(name, "");
		return directory;
	}
	
	public String getComponentItem(String ext) throws Exception {
		String directory = getComponentHTMLFilename().replace(".html", ext);
		return directory;
	}
	
	private static String removeExtension(String name) {
		if (name.startsWith(".."))
			return ".." + name.substring(2).replaceFirst("[.][^.]+$", "");
		if (name.startsWith("."))
			return "." + name.substring(1).replaceFirst("[.][^.]+$", "");
		return name.replaceFirst("[.][^.]+$", "");
	}

	private static String pagesSubDirName(String name) {
		String result = removeExtension(name);
		String parts[] = result.split("/");
		if (parts.length < 2)
			return result;
		String dir = parts[parts.length - 2];
		String nam = parts[parts.length - 1];
		if (dir.equals(nam)) {
			for (int i = parts.length - 3; i > -1; i--)
				dir = parts[i] + "/" + dir;
			return dir;
		}
		return result;
	}

	public String getComponentDir() throws Exception {
		String componentName = getComponentName();
		String desDir = iniFile.getDestination();
		String appDir = iniFile.getAppDir();
		String filename = componentName;
		if (componentName.indexOf('/') > 0)
			filename = componentName.substring(componentName.lastIndexOf('/'));
		String componentFilename = HTMLEdit.append(desDir, appDir, directory, componentName, filename + ".component.html");
		return componentFilename;
	}
	
	public String getIndexFilename() throws Exception {
		String desDir = iniFile.getDestination();
		String componentFilename = HTMLEdit.append(desDir, "index.html");
		return componentFilename;
	}
	
	public String getAppComponentFilename() throws Exception {
		String desDir = iniFile.getDestination();
		String componentFilename = HTMLEdit.append(desDir, "app/app.component.html");
		return componentFilename;
	}
	
	public String getHomeComponentFilename() throws Exception {
		String desDir = iniFile.getDestination();
		String componentFilename = HTMLEdit.append(desDir, "app/app.component.html");
		return componentFilename;
	}

	public String getCSSFilename() throws Exception {
		String desDir = iniFile.getDestination();
		String componentFilename = HTMLEdit.append(desDir, "styles.css");
		return componentFilename;
	}

	public String getAssetsDirectory() throws Exception {
		String desDir = iniFile.getDestination();
		String componentFilename = HTMLEdit.append(desDir, "assets");
		return componentFilename;
	}

	public String getAppModuleFilename() throws Exception {
		String moduleFilename = getAppComponentFilename().replace("component.html", "module.ts");
		return moduleFilename;
	}
	
	static public void copyFile(File source, File dest) throws IOException {
		if (!dest.exists())
			Files.copy(source.toPath(), dest.toPath());
	}

	public static void copyFile(String source, String dest) throws IOException {
		copyFile(new File(source), new File(dest));
	}
	
	public static void deleteFile(File to) throws IOException {
		if (to.exists())
			to.delete();
	}
	
	static public void deleteDir(String file) {
		deleteDir(new File(file));
	}
	
	static public void deleteDir(File file) {
	    File[] contents = file.listFiles();
	    if (contents != null) {
	        for (File f : contents) {
	            deleteDir(f);
	        }
	    }
	    file.delete();
	}
	
	
	public static void deleteFile(String to) throws IOException {
		deleteFile(new File(to));
	}

	public static String filename(String path) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static File getPath(File file) {
		String name = file.getName();
		String path = file.getPath();
		path = path.replace(name, "");
		return new File(path);
	}
	
	public static File mkdirs(String fullpathname) throws IOException {
		File file = getPath(new File(fullpathname));
		file.mkdirs();
		return file;
	}
	
}
