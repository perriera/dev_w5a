package org.w7a;

import java.util.concurrent.BlockingQueue;

import org.w7a.tasks.TaskInterface;

public class Consumer implements Runnable {
	
	private final BlockingQueue<TaskInterface> taskQueue;
	private int threadNo;

	public Consumer(BlockingQueue<TaskInterface> taskQueue, int threadNo) {
		this.taskQueue = taskQueue;
		this.threadNo = threadNo;
	}

	@Override
	public void run() {
		while (true) {
			try {
				TaskInterface task = taskQueue.take();
				task.resolve();
				System.out.println("Consumed: " + task + ":by thread:" + threadNo);
			} catch (Exception err) {
				err.printStackTrace();
			}
		}
	}
}