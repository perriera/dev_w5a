package org.w5a;

import org.w5a.requests.DownloadRequest;
import org.w5a.requests.UploadRequest;

public class Client {
	
	public static String PORTAUTHORITYADDRESS = "localhost";
	public static int PORTAUTHORITYPORT = 9090;

	public static void main(String[] args) throws Exception {
		
		DownloadRequest downloadRequest = new DownloadRequest("freeformjs.webflow.zip");
		downloadRequest.request();
		
		System.exit(0);
		
	}
	
}
