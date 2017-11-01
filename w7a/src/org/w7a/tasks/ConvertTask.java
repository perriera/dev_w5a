package org.w7a.tasks;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.w5a.Sockets;
import org.w5a.requests.ConvertRequest;
import org.w5a.requests.UploadRequest;
import org.w7a.Directory;

@SuppressWarnings("serial")
public class ConvertTask extends FreeportTask {

	public ConvertTask(ConvertRequest request) throws InterruptedException {
		super(request);
	}

	@Override
	public void resolve() throws Exception {

		ServerSocket listener = this.request.getFreeportServerSocket();
		Socket socket = listener.accept();

		ConvertRequest object = (ConvertRequest) Sockets.read(socket);

		Path path = Paths.get(Directory.getUploadFilename(object.filename));
		Files.write(path, object.buffer);

		object.setSuccessful(true);
		Sockets.write(socket, object);
		this.setSuccessful(true);

		socket.close();
		listener.close();

	}

}
