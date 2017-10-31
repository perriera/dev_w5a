package org.w6a.client;

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

import org.w6a.request.FreeportRequest;
import org.w6a.request.RequestInterface;
import org.w6a.request.TimestampRequest;

public class Client {
	
	public static int serverPort = 1024;
	public static String serverAddress = "localhost";

	public static void main(String[] args) throws IOException {
		// String serverAddress = JOptionPane.showInputDialog(
		// "Enter IP Address of a machine that is\n" +
		// "running the date service on port 9090:");
		
		FreeportRequest request = new FreeportRequest(Client.serverPort);
		
		try {
			request.resolve(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Socket socket = new Socket(Client.serverAddress, Client.serverPort);
//		
//		
//
//		BufferedOutputStream buffer = new BufferedOutputStream(socket.getOutputStream());
//		ObjectOutputStream output = new ObjectOutputStream(buffer);
//
//		TimestampRequest request = new TimestampRequest();
//		output.writeObject(request);
//		output.close();
//		buffer.close();
//		socket.close();
//		
//		socket = new Socket(serverAddress, 9090);
//
//		BufferedInputStream buffer2 = new BufferedInputStream(socket.getInputStream());
//		ObjectInputStream input = new ObjectInputStream(buffer2);
//		Object result = null;
//		try {
//			result = input.readObject();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		finally {
//			input.close();
//			buffer.close();
//			socket.close();
//		}
//		
//		if ( result!=null && result instanceof RequestInterface ) {
//			((RequestInterface)result).printStatus();
//		}

		System.exit(0);
	}

}
