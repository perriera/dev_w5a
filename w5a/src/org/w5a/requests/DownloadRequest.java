package org.w5a.requests;

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
		DownloadRequest object = (DownloadRequest) Sockets.read(socket);

		Path path = Paths.get("files/" + object.filename);
		Files.write(path, object.buffer);
		
		object.setSuccessful(true);
		Sockets.write(socket, object);
		socket.close();
		
		this.successful = true;

	}

}
