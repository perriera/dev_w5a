package org.w5a;

import org.w5a.requests.DownloadRequest;
import org.w5a.requests.UploadRequest;

public class Client {
	
	public static String serverAddress = "localhost";
	public static int port = 9090;

	public static void main(String[] args) throws Exception {
		
		DownloadRequest downloadRequest = new DownloadRequest("freeformjs.webflow.zip");
		downloadRequest.request();
		
		System.exit(0);
		
	}
	
}
