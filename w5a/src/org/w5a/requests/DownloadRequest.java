package org.w5a.requests;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DownloadRequest extends UploadRequest implements RequestInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -325838185099451063L;

	public DownloadRequest(String filename) {
		super(filename);
	}
	
	@Override
	public void resolve() throws Exception {

		Socket socket = new Socket(this.getAddress(), this.getFreeport());

		BufferedInputStream buffer = new BufferedInputStream(socket.getInputStream());
		ObjectInputStream input = new ObjectInputStream(buffer);
		DownloadRequest object = (DownloadRequest)input.readObject();
		
		Path path = Paths.get("files/"+object.filename);
	    Files.write(path, object.buffer); 
	    
	    socket.close();

	}

}
