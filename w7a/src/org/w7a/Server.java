package org.w7a;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.w7a.tasks.TaskInterface;

public class Server {

	/**
	 * Runs the server.
	 */
	public static void main(String[] args) throws IOException {
		
		BlockingQueue<TaskInterface> sharedQueue = new LinkedBlockingQueue<TaskInterface>();

		ExecutorService pes = Executors.newFixedThreadPool(1);
		ExecutorService ces = Executors.newFixedThreadPool(2);

		pes.submit(new Producer(sharedQueue,1));
		ces.submit(new Consumer(sharedQueue,1));
		ces.submit(new Consumer(sharedQueue,2));
		
	}

}
