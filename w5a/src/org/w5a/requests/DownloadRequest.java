package org.w5a.requests;

import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.w5a.Sockets;

/*
 *  class DownloadRequest
 *  
 *  REQUESTS A FILE ON THE SERVER MACHINE TO BE "DOWNLOADED" TO THE CLIENT MACHINE
 *  
 *  The purpose of the class DownloadRequest object is to send a file
 *  on the server machine to the client machine.
 *  
 *  It requires a FreeportRequest object as is a type of FileRequest.
 *  
 */

public class DownloadRequest extends FileRequest implements RequestInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1625034809475025201L;

	public DownloadRequest(String filename) {
		super(filename);
	}

	@Override
	public void resolve() throws Exception {

		/*
		 * Connect to the getFreeportSocket() ...  
		 */
		
		Socket socket = this.getFreeportSocket();
	
		/*
		 * Assume that the DownloadTask object has already
		 * loaded the file on the server and has prepared it's
		 * own instance of this object to be sent over which
		 * contains a byte buffer of the file requested.
		 */
		
		DownloadRequest object = (DownloadRequest) Sockets.read(socket);

		/*
		 * File a place on the client machine to save the file
		 * and convert the byte buffer into a file ... 
		 */
		
		Path path = Paths.get("files/" + object.filename);
		Files.write(path, object.buffer);
		
		/*
		 * (WE ONLY NEED TO SEND BACK A BOOLEAN HERE)
		 */
		
		object.setSuccessful(true);
		Sockets.write(socket, object);
		
		/*
		 * Close the getFreeportSocket().
		 */
		
		socket.close();
		
		/*
		 * Mark this operation as successful.
		 */
		
		this.successful = true;

	}

}
