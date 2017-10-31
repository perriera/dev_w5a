package org.w7a.tasks;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.w5a.requests.UploadRequest;
import org.w7a.managers.PortManager;

@SuppressWarnings("serial")
public class UploadTask extends UploadRequest implements TaskInterface {

	UploadRequest request;

	public UploadTask(UploadRequest request) throws InterruptedException {
		this.request = request;
		int freeport = PortManager.getInstance().take();
		this.request.setAddress("localhost");
		this.request.setFreeport(freeport);
	}

	@Override
	public void resolve() throws Exception {
		ServerSocket listener = new ServerSocket(this.request.getFreeport());
		try {

			Socket socket = listener.accept();

			try {

				ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				UploadRequest object = (UploadRequest)input.readObject();
				
				Path path = Paths.get("files/"+object.filename);
			    Files.write(path, object.buffer); 

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
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
