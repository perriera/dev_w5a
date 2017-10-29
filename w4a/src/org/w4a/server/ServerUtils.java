package org.w4a.server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.w1a.HTMLEdit;
import org.w4a.exceptions.FileAlreadyExistsException;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class ServerUtils {

	public static String getFilename(String path) {
		return new File(path).getName();
	}

	public static void mkdirs(String dir) {
		new File(dir).mkdirs();
	}

	public static void rmdirs(String dir) {
		rmdirs(new File(dir));
	}
	
	public static void rmdirs(File file) {
		File[] contents = file.listFiles();
		if (contents != null) {
			for (File f : contents) {
				rmdirs(f);
			}
		}
		file.delete();
	}

	public static boolean exists(String path) {
		return new File(path).exists();
	}

	public static void mv(File file, String target) {
		if ( new File(target).isDirectory() ) {
			String filename = file.getName();
			target = HTMLEdit.append(target, filename);
		}
		file.renameTo(new File(target));
	}

	static public File cp(File source, File dest) throws IOException {
		if (!dest.exists()) 
			dest.delete();
		Files.copy(source.toPath(), dest.toPath());
		return dest;
	}
	
	static public File cp(File source, String dest) throws IOException {
		String filename = source.getName();
		File target = new File(HTMLEdit.append(dest, filename));
		if (!target.exists())
			Files.copy(source.toPath(), target.toPath());
		return target;
	}

	static public void zip(String sourceDir, String destinationZipFile) throws Exception {
		ZipFile zipFile = new ZipFile(destinationZipFile);
		String folderToAdd = sourceDir;
		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
		zipFile.addFolder(folderToAdd, parameters);
	}

	public static void unzip(File zippedFile, String destination) throws Exception {
		String source = zippedFile.getPath();
		ZipFile zipFile = new ZipFile(source);
		zipFile.extractAll(destination);
	}

	public static String moveTo(String src, String des, boolean overwrite) throws Exception {
		File from = new File(src);
		File to = new File(des);
		if ( to.isDirectory() ) {
			String xyz = HTMLEdit.append(to.getPath(), from.getName());
			to = new File(xyz);
		}
		if (to.exists()&&!overwrite)
			throw new FileAlreadyExistsException(to.getPath());
		else {
			if (to.exists()) {
				to.delete();
				System.out.println(to.getPath()+" has been overwritten");
			}
		}
		from.renameTo(to);
		return to.getPath();
	}

}
