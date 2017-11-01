package org.w7a.tasks;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.w5a.Sockets;
import org.w5a.requests.DownloadRequest;
import org.w7a.Directory;

@SuppressWarnings("serial")
public class DownloadTask extends FreeportTask {

	public DownloadTask(DownloadRequest request) throws InterruptedException {
		super(request);
	}

	@Override
	public void resolve() throws Exception {

		ServerSocket listener = this.request.getFreeportServerSocket();
		Socket socket = listener.accept();
		
		DownloadRequest object = (DownloadRequest) this.request;

		Path path = Paths.get(Directory.getUploadFilename(object.filename));
		object.buffer = Files.readAllBytes(path);

		Sockets.write(socket, this.request);
		this.setSuccessful(Sockets.read(socket).isSuccessful());

		socket.close();
		listener.close();

	}

}
