package org.w5a.requests;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Sockets {

	public static void write(Socket socket, RequestInterface object) throws IOException {
		ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		output.writeObject(object);
		output.flush();
	}
	
	public static RequestInterface read(Socket socket) throws IOException, ClassNotFoundException {
		ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		RequestInterface object = (RequestInterface) input.readObject();
		return object;
	}
	
}
