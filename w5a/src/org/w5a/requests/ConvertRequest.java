package org.w5a.requests;

import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.w5a.Sockets;

/*
 *  class UploadRequest
 *  
 *  REQUESTS A FILE ON THE CLIENT MACHINE TO BE "UPLOADED" TO THE SERVER MACHINE
 *  
 *  The purpose of the class UploadRequest object is to send a file
 *  on the client's machine to the server.
 *  
 *  It requires a FreeportRequest object as is a type of FileRequest.
 *  
 */

public class ConvertRequest extends FileRequest implements RequestInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5388491813919122014L;
	
	public ConvertRequest(String filename) {
		super(filename);
	}
	
	public ConvertRequest() {
		
	}

	@Override
	public void resolve() throws Exception {

		/*
		 * Connect to the getFreeportSocket() ...  
		 */
		
		Socket socket = this.getFreeportSocket();

		/*
		 * Read in the file from the client operating system
		 * to be uploaded to the server (as a byte buffer) ... 
		 */
		
		Path path = Paths.get("files/" + filename);
		this.buffer = Files.readAllBytes(path);
		
		/*
		 * Send the serialized UploadRequest object over to 
		 * the server ... 
		 * 
		 */
		
		Sockets.write(socket, this);
		
		/*
		 * Wait for a response to let us know if was successful ...
		 */
		
		this.successful = Sockets.read(socket).isSuccessful();
		
		/*
		 * Close the getFreeportSocket().
		 */
		
		socket.close();

	}


}
