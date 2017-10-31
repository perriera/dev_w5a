package org.w5a.requests;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.w5a.Client;

public class UploadRequest extends FreeportRequest implements RequestInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1987888190397588968L;

	public String filename;
	public byte[] buffer;

	public UploadRequest(String filename) {
		this.filename = filename;
	}

	public UploadRequest() {
	}

	public void request() throws Exception {

		Socket socket = new Socket(Client.serverAddress, Client.port);

		ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		output.writeObject(this);
		output.flush();

		try {
			ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			UploadRequest response = (UploadRequest) input.readObject();
			response.resolve();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			socket.close();
		}

	}

	@Override
	public void resolve() throws Exception {

		Path path = Paths.get("files/" + filename);
		this.buffer = Files.readAllBytes(path);

		Socket socket = new Socket(this.getAddress(), this.getFreeport());

		ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		output.writeObject(this);
		output.flush();
		socket.close();

	}

}
