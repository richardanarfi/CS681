package edu.umb.cs.cs681.hw12;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable {
	private ReentrantLock lock;
	private boolean done = false;
	private static ArrayList<Path> pathArr = new ArrayList<Path>();

	public RequestHandler() {
		lock = new ReentrantLock();
	}

	public void setDone() {
		lock.lock();
		try {
			done = true;
		} finally {
			lock.unlock();
		}
	}

	public void run() {
		Random random = new Random();

		while (true) {
			lock.lock();
			try {
				if (done) {
					break;
				}
			} finally {
				lock.unlock();
			}
			Path filePath = pathArr.get(random.nextInt(pathArr.size()));
			AccessCounter.getInstance().increment(filePath);
			AccessCounter.getInstance().getCount(filePath);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				continue;
			}
		}
	}

	public static void main(String[] args) {

		pathArr.add(Paths.get("a.html"));
		pathArr.add(Paths.get("b.html"));
		pathArr.add(Paths.get("c.html"));

		ArrayList<Thread> threadArr = new ArrayList<>();
		RequestHandler request = new RequestHandler();

		for (int i = 0; i < 12; i++) {
			Thread thread = new Thread(request);
			threadArr.add(thread);
			thread.start();
		}
		threadArr.forEach((Thread t) -> {
			try {
				t.interrupt();
				request.setDone();
				t.join();
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		});
	}
}
