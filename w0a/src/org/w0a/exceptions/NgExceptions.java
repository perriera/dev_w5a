package org.w0a.exceptions;

import java.io.File;
import java.util.Date;

@SuppressWarnings("serial")
abstract public class NgExceptions extends Exception {
	public NgExceptions(String name) {
		super(name);
	}

	abstract public String getIssue();
	
	static public void diagnostics(NgExceptions ex, File zipFile) {
		System.err.println("\n\n");
		System.err.println("\t"+ex.getClass().getSimpleName()+" : "+ex.getMessage());
		System.err.println("\t"+ex.getIssue());
		System.err.println("\t"+zipFile.getPath());
		System.err.println("\t"+new Date());
		System.err.println("\n");
	}
	
	static public void diagnostics(Exception ex, File zipFile) {
		System.err.println("\n\n");
		System.err.println("\t"+ex.getClass().getSimpleName()+" : "+ex.getMessage());
		System.err.println("\t This is a generic exception that may need attention! ");
		System.err.println("\t"+zipFile.getPath());
		System.err.println("\t"+new Date());
		System.err.println("\n");
	}
}
