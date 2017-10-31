package org.w8a.task;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.w6a.request.RequestInterface;
import org.w6a.request.TimestampRequest;

@SuppressWarnings("serial")
abstract public class SocketTask implements SocketInterface {

	int port;

	public SocketTask(int port) {
		this.port = port;
	}

	@Override
	public void printStatus() {
		System.out.println(this.getClass().getSimpleName()+": "+this.port);
	}

	@Override
	public void resolve(RequestInterface request) throws Exception {
		ServerSocket listener;
		try {
			listener = new ServerSocket(this.port);
			try {
				while (true) {
					Socket socket = listener.accept();
					try {

						BufferedInputStream buffer = new BufferedInputStream(socket.getInputStream());
						ObjectInputStream input = new ObjectInputStream(buffer);
						Object object = input.readObject();
						input.close();
						buffer.close();

						socket = listener.accept();

						TaskInterface task = null;

						if (object instanceof RequestInterface) {
							
							((RequestInterface)object).resolve(((RequestInterface)object));

							this.dowork();
							
							if (object instanceof TimestampRequest) {
								task = new ResolveTimestampRequest((TimestampRequest) object);
							}

							if (task != null) {
								task.resolve(request);
								BufferedOutputStream buffer2 = new BufferedOutputStream(socket.getOutputStream());
								ObjectOutputStream output = new ObjectOutputStream(buffer2);
								output.writeObject(object);
								output.close();
								buffer.close();
							}

						}

					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						socket.close();
					}

				}

			} finally {
				listener.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}


}
