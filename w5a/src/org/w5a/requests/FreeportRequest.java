package org.w5a.requests;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.w5a.Client;
import org.w5a.Sockets;

/*
 * class FreeportRequest
 * 
 * The purpose of the class FreeportRequest object is to acquire
 * a free port from the server and make it available to be sent
 * over the socket for use in a dedicated Socket/SocketServer 
 * combination. It is used as a base class for all classes that
 * require a free port from a port pool. 
 * 
 * To work properly a dedicated address and port known as the 
 * Client.PORTAUTHORITYADDRESS and the Client.PORTAUTHORITYPORT
 * must be accessed where the free port will be assigned. This
 * transaction must be resolved very quickly and only the free
 * port (and address assigned with it) are to be used for any
 * specialized socket communications.
 * 
 */
abstract public class FreeportRequest implements RequestInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3375455506878919228L;
	
	String address;
	int freeport;
	boolean successful;
	
	/**
	 * POJO 
	 * @return
	 */
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setFreeport(int freeport) {
		this.freeport = freeport;
	}

	public int getFreeport() {
		return freeport;
	}

	@Override
	public boolean isSuccessful() throws Exception {
		return successful;
	}

	@Override
	public void setSuccessful(boolean state) throws Exception {
		this.successful = state;
	}
	
	/**
	 * Socket getPortAuthoritySocket()
	 * @return A socket using as the PORTAUTHORITYADDRESS:PORTAUTHORITYPORT values
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	
	public Socket getPortAuthoritySocket() throws UnknownHostException, IOException {
		return new Socket(Client.PORTAUTHORITYADDRESS, Client.PORTAUTHORITYPORT);
	}
	
	/**
	 * Socket getFreeportSocket()
	 * @return A socket using a free port address and a free port from the port pool.
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	
	public Socket getFreeportSocket() throws UnknownHostException, IOException {
		return new Socket(this.getAddress(), this.getFreeport());
	}
	
	/**
	 * ServerSocket getFreeportServerSocket()
	 * @return A ServerSocket for use on the server side to work with the free port.
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	
	public ServerSocket getFreeportServerSocket() throws UnknownHostException, IOException {
		return new ServerSocket(this.getFreeport());
	}
	
	/**
	 *  void request()
	 *  
	 *  This method opens a client socket connect using getPortAuthoritySocket() 
	 *  and writes this object as a request (which is a 'FreeportRequest' ) to be
	 *  resolved on the server.
	 *  
	 *  When response comes back the connection to the getPortAuthoritySocket() is closed
	 *  and a call is made to the .resolve() method of this object to do the actual
	 *  socket / server socket transaction for the derived class. If no exceptions
	 *  are thrown this request is marked as a success.
	 *  
	 */
	
	public void request() throws Exception {

		/*
		 * The first order of business is to connect to the getPortAuthoritySocket() ... 
		 */
		
		Socket socket = getPortAuthoritySocket();
		
		/*
		 * Then send over this FreeportRequest to the getPortAuthoritySocket()
		 * and wait for a response ... 
		 */
		
		Sockets.write(socket, this);
		
		/*
		 * When the request returns it will have it's free port and socket 
		 * address assigned ...
		 */
		
		FreeportRequest response = (FreeportRequest) Sockets.read(socket);
		
		/*
		 * Now close up the connection to the getPortAuthoritySocket() so that
		 * other FreeportRequest can occur without having to wait for this 
		 * operation to complete first.
		 */
		
		socket.close();
		
		/*
		 * Call the derived classes .resolve() method to do the actual 
		 * free port connection which will assume that it's server socket is 
		 * now running on the server's host ready for connection ... 
		 */
		
		response.resolve();
		
		/*
		 * Mark the operation as successful ...
		 */
		
		this.successful = response.successful;

	}
	
}
