package org.w4a.requests;

import java.io.File;
import java.util.Date;
import java.util.HashMap;

public class UploadingMonitor2 {

	static HashMap<String, Date> timeMap = new HashMap<String, Date>();
	static int length = 60 * 1000 * 2;
	static int duration = length;

	public static boolean isUploading(File zipFile) {
		boolean uploaading = true;
		String filename = zipFile.getName();
		if (!timeMap.containsKey(filename)) {
			timeMap.put(filename, new Date());
		} else {
			Date marker = timeMap.get(filename);
			Date current = new Date();
			if (current.getTime() > marker.getTime() + duration) {
				uploaading = false;
				timeMap.remove(filename);
			}
		}
		return uploaading;
	}
	
	public static void clear(File zipFile) {
		String filename = zipFile.getName();
		timeMap.remove(filename);
	}
	
	public static void testMode() {
	    System.err.println("UploadingMonitor testMode() is on!");
		duration = -1;
	}

	public static void testModeOff() {
		System.err.println("UploadingMonitor testMode() is off");
		duration = length;
	}
	
	public static boolean isFileUploading() {
		return timeMap.size()>0;
	}

}
