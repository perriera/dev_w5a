package org.w5a.requests;

import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.w5a.Client;
import org.w5a.Sockets;

public class UploadRequest extends FreeportRequest implements RequestInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5388491813919122014L;
	
	public String filename;
	public byte[] buffer;
	boolean successful = false;

	public UploadRequest(String filename) {
		this.filename = filename;
	}

	public UploadRequest() {
	}

	public void request() throws Exception {

		Socket socket = new Socket(Client.serverAddress, Client.port);
		Sockets.write(socket, this);
		UploadRequest response = (UploadRequest) Sockets.read(socket);
		response.resolve();
		this.successful = response.successful;
		socket.close();

	}

	@Override
	public void resolve() throws Exception {

		Path path = Paths.get("files/" + filename);
		this.buffer = Files.readAllBytes(path);

		Socket socket = new Socket(this.getAddress(), this.getFreeport());
		Sockets.write(socket, this);
		this.successful = Sockets.read(socket).isSuccessful();
		socket.close();

	}

	@Override
	public boolean isSuccessful() throws Exception {
		return this.successful;
	}

	@Override
	public void setSuccessful(boolean state) throws Exception {
		this.successful = state;
	}

}
