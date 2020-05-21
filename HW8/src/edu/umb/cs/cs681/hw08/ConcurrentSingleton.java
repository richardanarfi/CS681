package edu.umb.cs.cs681.hw08;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSingleton {
	private ConcurrentSingleton() {};
	private static ConcurrentSingleton instance = null;
	private static ReentrantLock lock = new ReentrantLock();
	// Factory method to create or return the singleton instance
	public static ConcurrentSingleton getInstance(){
		lock.lock();
		try{
			if(instance==null){ 
				instance = new ConcurrentSingleton(); 
				}
			return instance;
			}finally{
				lock.unlock();
				}
		}
	
	public static void main(String[] args) throws InterruptedException {
		LinkedList<Thread> threads = new LinkedList<Thread>();
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(() -> {
				System.out.println(ConcurrentSingleton.getInstance());
			});
			threads.add(t);
			t.start();
		}
		for (Thread t : threads) {
			t.join();
		}
	}
}
