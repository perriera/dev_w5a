package org.w7a;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.w0a.HTMLEdit;

public class Directory {

	public static String uploadsDirectory = "./uploads";
	public static String testDirDirectory = "./files";

	private static Directory instance = null;

	public static Directory getInstance() {
		if (Directory.instance == null)
			Directory.instance = new Directory();
		return Directory.instance;
	}

	void rmdirs(File f) throws IOException {
		if (f.exists()) {
			if (f.isDirectory()) {
				for (File c : f.listFiles())
					rmdirs(c);
			}
			if (!f.delete())
				throw new FileNotFoundException("Failed to delete file: " + f);
		}
	}
	
	public static void create() throws IOException {
		clearUploads();
	}
	
	public static void destroy() throws IOException {
		Directory.getInstance().rmdirs(new File(Directory.uploadsDirectory));
	}

	public static void clearUploads() throws IOException {
		Directory.getInstance().rmdirs(new File(Directory.uploadsDirectory));
		Directory.getInstance().mkdirs(new File(Directory.uploadsDirectory));
	}

	public File getPath(File file) {
		String name = file.getName();
		String path = file.getPath();
		path = path.replace(name, "");
		return new File(path);
	}

	public File mkdirs(String fullpathname) throws IOException {
		File file = new File(fullpathname);
		file.mkdirs();
		return file;
	}

	public File mkdirs(File file) throws IOException {
		return mkdirs(file.getPath());
	}

	public static String getUploadFilename(String filename) throws IOException {
		return HTMLEdit.append(Directory.uploadsDirectory, filename);
	}

	public static String getTestDirFilename(String filename) {
		return HTMLEdit.append(Directory.testDirDirectory, filename);
	}

}
