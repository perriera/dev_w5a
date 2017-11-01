package org.w5a.requests;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConvertRequest extends UploadRequest implements RequestInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -325838185099451063L;

	public ConvertRequest(String filename) {
		super(filename);
	}
	
	void download() throws Exception {
		
		Socket socket = new Socket(this.getAddress(), this.getFreeport());

		ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		ConvertRequest object = (ConvertRequest) input.readObject();

		Path path = Paths.get("files/" + object.filename);
		Files.write(path, object.buffer);

		socket.close();
		
	}

	@Override
	public void resolve() throws Exception {

		this.download();
		this.successful = true;

	}

}
