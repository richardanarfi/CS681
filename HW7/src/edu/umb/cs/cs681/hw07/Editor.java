package edu.umb.cs.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class Editor implements Runnable{
	private boolean done = false;
	private File aFile;
	ReentrantLock lockEd = new ReentrantLock();
	
	public Editor(File file) {
		aFile = file;
	}
	
	public void run() {
		while(true) {
			lockEd.lock();
			try {
				if(done) {
					System.out.println("Done with Editor!");
					break;
				}
				System.out.println("Editing...");
				aFile.change();
				aFile.save();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
//					e.printStackTrace();
					}
				}finally {
					lockEd.unlock();
					}
			}
		}
	
	public void setDone() {
		lockEd.lock();
		try {
		    done = true;
		} finally {
			lockEd.unlock();
	    }
	}
}
