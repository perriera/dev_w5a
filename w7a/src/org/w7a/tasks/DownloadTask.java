package org.w7a.tasks;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.w5a.requests.Sockets;
import org.w5a.requests.UploadRequest;
import org.w7a.Directory;
import org.w7a.managers.PortManager;

@SuppressWarnings("serial")
public class DownloadTask extends UploadTask {

	public DownloadTask(UploadRequest request) throws InterruptedException {
		super(request);
	}

	@Override
	public void resolve() throws Exception {
		
		ServerSocket listener = new ServerSocket(this.request.getFreeport());
		try {

			Socket socket = listener.accept();

			try {

				Path path = Paths.get(Directory.getUploadFilename(this.request.filename));
				this.request.buffer = Files.readAllBytes(path);
				
				Sockets.write(socket,this.request);
				this.setSuccessful(Sockets.read(socket).isSuccessful());

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				socket.close();
			}

		} finally {
			listener.close();
			PortManager.getInstance().put(this.request.getFreeport());
		}
		
	}
	
}
