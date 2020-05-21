package edu.umb.cs.cs681.hw10;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton {
	private ConcurrentSingleton() {};
	private static AtomicReference<ConcurrentSingleton> instance = new AtomicReference<ConcurrentSingleton>(null);

	// Factory method to create or return the singleton instance
	public static ConcurrentSingleton getInstance(){
		if(instance.get()==null) { 
			instance.set(new ConcurrentSingleton()); 
			}
		return instance.get();
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
