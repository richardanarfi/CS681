package edu.umb.cs.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class AutoSaver implements Runnable{
	private boolean done = false;
	private File aFile;
	ReentrantLock lockA = new ReentrantLock();
	
	public AutoSaver(File file) {
		aFile = file;
	}
	public void run() {
		while(true) {
			lockA.lock();
			try {
				if(done) {
					System.out.println("Done with Autosaver!");
					break;
				}
				System.out.println("Autosaving...");
				aFile.save();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
//					e.printStackTrace();
					}
				} finally {
					lockA.unlock();
				}
		}
	}
	
	public void setDone() {
		lockA.lock();
		try {
		    done = true;
		} finally {
			lockA.unlock();
	    }
    }
}
