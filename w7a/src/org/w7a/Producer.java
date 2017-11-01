package org.w7a;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import org.w5a.Client;
import org.w5a.Sockets;
import org.w5a.requests.ConvertRequest;
import org.w5a.requests.DownloadRequest;
import org.w5a.requests.RequestInterface;
import org.w5a.requests.UploadRequest;
import org.w7a.tasks.ConvertTask;
import org.w7a.tasks.DownloadTask;
import org.w7a.tasks.TaskInterface;
import org.w7a.tasks.UploadTask;

public class Producer implements Runnable {

//	String requestName = requestObject.getClass().getSimpleName();
//	String taskName = requestName.replace("Request", "Task");
//	Class<?> cl = Class.forName("org.w7a.tasks."+taskName);
//	Constructor<?> cons = cl.getConstructor(requestObject.getClass());
//	TaskInterface taskObject = (TaskInterface)cons.newInstance(requestObject);
//	taskQueue.put(taskObject);
	
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

						RequestInterface requestObject = Sockets.read(socket);

						if (requestObject instanceof ConvertRequest) {
							taskQueue.put(new ConvertTask((ConvertRequest) requestObject));
						} else {
							if (requestObject instanceof DownloadRequest) {
								taskQueue.put(new DownloadTask((DownloadRequest) requestObject));
							} else {
								if (requestObject instanceof UploadRequest) {
									taskQueue.put(new UploadTask((UploadRequest) requestObject));
								}
							}
						}

						Sockets.write(socket, requestObject);

						System.out.println("Produced:" + requestObject + ":by thread:" + threadNo);

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