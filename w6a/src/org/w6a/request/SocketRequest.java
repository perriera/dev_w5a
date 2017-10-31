package org.w6a.request;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.w6a.client.Client;

abstract public class SocketRequest implements RequestInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3994249825426613632L;
	
	int port;

	public SocketRequest(int port) {
		this.port = port;
	}
	
	@Override
	public void printStatus() {
		System.out.println(this.getClass().getSimpleName()+": "+this.port);
	}
	
	@Override
	public void resolve(RequestInterface ignore) throws Exception {
		
		Socket socket = new Socket(Client.serverAddress, this.port);

		BufferedOutputStream buffer = new BufferedOutputStream(socket.getOutputStream());
		ObjectOutputStream output = new ObjectOutputStream(buffer);

		RequestInterface request = this.makeRequest();
		output.writeObject(request);
		output.close();
		buffer.close();
		socket.close();
		
		socket = new Socket(Client.serverAddress, this.port);

		BufferedInputStream buffer2 = new BufferedInputStream(socket.getInputStream());
		ObjectInputStream input = new ObjectInputStream(buffer2);
		Object result = null;
		try {
			result = input.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			input.close();
			buffer.close();
			socket.close();
		}
		
		if ( result!=null && result instanceof RequestInterface ) {
			((RequestInterface)result).printStatus();
		}
		
	}

	
}
