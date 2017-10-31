package org.w7a.tasks;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.w5a.requests.UploadRequest;
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

				Path path = Paths.get("files/" + this.request.filename);
				this.request.buffer = Files.readAllBytes(path);
				
				BufferedOutputStream buffer = new BufferedOutputStream(socket.getOutputStream());
				ObjectOutputStream output = new ObjectOutputStream(buffer);
				output.writeObject(this.request);
				output.flush();

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
