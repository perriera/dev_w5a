package org.w7a;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import org.w5a.Client;
import org.w5a.requests.DownloadRequest;
import org.w5a.requests.UploadRequest;
import org.w7a.tasks.DownloadTask;
import org.w7a.tasks.TaskInterface;
import org.w7a.tasks.UploadTask;

public class Producer implements Runnable {

	private final BlockingQueue<TaskInterface> taskQueue;
	private int threadNo;

	public Producer(BlockingQueue<TaskInterface> taskQueue, int threadNo) {
		this.taskQueue = taskQueue;
		this.threadNo = threadNo;
	}

	@Override
	public void run() {

		try {

			ServerSocket listener = new ServerSocket(Client.port);

			try {

				while (true) {

					Socket socket = listener.accept();

					try {

						BufferedInputStream buffer = new BufferedInputStream(socket.getInputStream());
						ObjectInputStream input = new ObjectInputStream(buffer);
						Object object = input.readObject();

						if (object instanceof DownloadRequest) {
							taskQueue.put(new DownloadTask((DownloadRequest) object));
						} else {
							if (object instanceof UploadRequest) {
								taskQueue.put(new UploadTask((UploadRequest) object));
							}
						}

						BufferedOutputStream buffer2 = new BufferedOutputStream(socket.getOutputStream());
						ObjectOutputStream output = new ObjectOutputStream(buffer2);
						output.writeObject(object);
						output.flush();

						System.out.println("Produced:" + object + ":by thread:" + threadNo);

					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						socket.close();
					}

				}

			} finally {
				listener.close();
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}