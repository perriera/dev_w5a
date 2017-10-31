package org.w6a.demo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import javax.swing.JOptionPane;

import org.w6a.request.RequestInterface;
import org.w6a.request.TimestampRequest;

public class Client2 {

	public static void main(String[] args) throws IOException {
		// String serverAddress = JOptionPane.showInputDialog(
		// "Enter IP Address of a machine that is\n" +
		// "running the date service on port 9090:");
		String serverAddress = "localhost";
		Socket socket = new Socket(serverAddress, 9090);

		BufferedOutputStream buffer = new BufferedOutputStream(socket.getOutputStream());
		ObjectOutputStream output = new ObjectOutputStream(buffer);

		TimestampRequest request = new TimestampRequest();
		output.writeObject(request);
		output.close();
		buffer.close();
		socket.close();
		
		socket = new Socket(serverAddress, 9090);

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

		System.exit(0);
	}

}
