package org.w7a.tasks;

import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.w5a.requests.ConvertRequest;
import org.w7a.Directory;
import org.w7a.managers.PortManager;

@SuppressWarnings("serial")
public class ConvertTask extends UploadTask {

	public ConvertTask(ConvertRequest request) throws InterruptedException {
		super(request);
	}
	
	void download() throws Exception {
		ServerSocket listener = new ServerSocket(this.request.getFreeport());
		try {

			Socket socket = listener.accept();

			try {

				Path path = Paths.get(Directory.getUploadFilename(this.request.filename));
				this.request.buffer = Files.readAllBytes(path);
				
				ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
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

	@Override
	public void resolve() throws Exception {
		
		this.download();
		this.setSuccessful(true);
		
	}
	
}
