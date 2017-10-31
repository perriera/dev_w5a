package org.w8a.server;

import java.io.IOException;
import org.w6a.client.Client;
import org.w8a.task.ResolveFreeportRequest;

public class Server {

	/**
	 * Runs the server.
	 */
	public static void main(String[] args) throws IOException {
		
		ResolveFreeportRequest request = new ResolveFreeportRequest(Client.serverPort);
		
		try {
			request.resolve(null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
