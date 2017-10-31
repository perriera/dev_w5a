package org.w8a.demo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import org.w6a.request.RequestInterface;
import org.w6a.request.TimestampRequest;
import org.w8a.task.ResolveTimestampRequest;
import org.w8a.task.TaskInterface;

public class Server2 {

	/**
	 * Runs the server.
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket listener = new ServerSocket(9090);
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

						if (object instanceof TimestampRequest) {
							task = new ResolveTimestampRequest((TimestampRequest) object);
						}

						if (task != null) {
							task.resolve(null);
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
	}

}
