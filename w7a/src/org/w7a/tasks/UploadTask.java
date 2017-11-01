package org.w7a.tasks;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.w5a.Sockets;
import org.w5a.requests.UploadRequest;
import org.w7a.Directory;
import org.w7a.managers.PortManager;

@SuppressWarnings("serial")
public class UploadTask extends UploadRequest implements TaskInterface {

	UploadRequest request;

	public UploadTask(UploadRequest request) throws InterruptedException {
		this.request = request;
		this.request.setAddress("localhost");
		this.request.setFreeport(PortManager.getInstance().take());
	}

	protected void finalize() throws Throwable {
		PortManager.getInstance().put(this.request.getFreeport());
		super.finalize();
	}

	@Override
	public void resolve() throws Exception {

		ServerSocket listener = new ServerSocket(this.request.getFreeport());
		Socket socket = listener.accept();

		UploadRequest object = (UploadRequest) Sockets.read(socket);

		Path path = Paths.get(Directory.getUploadFilename(object.filename));
		Files.write(path, object.buffer);

		object.setSuccessful(true);
		Sockets.write(socket, object);
		this.setSuccessful(true);

		socket.close();
		listener.close();

	}

}
