package edu.umb.cs.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class File {
	private boolean changed = false;
	private ReentrantLock lock;
	
	public File() {
		lock = new ReentrantLock();
	}
	
	public void change() {
		lock.lock();
		try {
			changed = true;
		} finally{
			lock.unlock();
		}
	}
	
	public void save() {
		lock.lock();
		try {
			if(!changed)
				return;
			else 
				System.out.println("File saved at " + System.currentTimeMillis() + "\n");
			changed = false;
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		File file = new File();
		Editor editor = new Editor(file);
		AutoSaver autosaver = new AutoSaver(file);
		Thread t1 = new Thread(editor);
		Thread t2 = new Thread(autosaver);		
		System.out.println("Thread-Safe Editor");
		System.out.println("Thread-Safe Autosaver");
		t1.start();
		t2.start();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
//            e.printStackTrace();
		}
		t1.interrupt();
		editor.setDone();
				
		t2.interrupt();
		autosaver.setDone();
		try {
			t1.join();
			t2.join();
		} catch (Exception e) {
//                e.printStackTrace();
			}
		}
	}
