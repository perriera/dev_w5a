package org.w4a.exceptions;

import java.io.File;

@SuppressWarnings("serial")
public class DuplicateSrcArchivesUploadedException extends W4AException {
	File srcSipEntry1;
	File srcSipEntry2;
	public DuplicateSrcArchivesUploadedException(File srcSipEntry1, File srcSipEntry2) {
		super(srcSipEntry1.getName());
		this.srcSipEntry1 = srcSipEntry1;
		this.srcSipEntry2 = srcSipEntry2;
	}
	
	@Override
	public String getPublicIssue() {
		return "Two zipped Angular 2 /src directories have been loaded by mistake.";
	}
	
	@Override
	public String getIssue() {
		return "Two zipped Angular 2 /src directories have been loaded by mistake: "
					+srcSipEntry1.getName()+" & "+srcSipEntry2.getName();
	}
	
}
