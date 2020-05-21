package edu.umb.cs.cs681.hw09;

import java.util.concurrent.locks.ReentrantLock;
import java.util.LinkedList;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer {
	
	private ReentrantLock lock = new ReentrantLock();
	private boolean done = false;
	
	public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
		super(dividend, from, to);
		if (from >= 2 && to >= from) {
			this.from = from;
			this.to = to;
		} else {
			throw new RuntimeException(
				"from must be >= 2, and to must be >= from." + "from==" + from + " to==" + to);
		}
	}
	
	public void setDone(){
		lock.lock();
		try { 
			done = true; 
		} finally {
			lock.unlock(); 
		} 
	}
	
	public void generatePrimeFactors() {
		long divisor = from;
	    while(true){
			lock.lock();
			try {
				if(done || dividend == 1 || divisor > to)
					break;
				if( divisor > 2 && isEven(divisor)) {
					divisor++;
					continue;
				}
				if(dividend % divisor == 0) {
					factors.add(divisor);
					dividend /= divisor;
				} else {
					if(divisor==2) {divisor++;} 
					else {divisor += 2;}
		    	}
			} finally {
				lock.unlock();
			}
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				System.out.println(e.toString());
				continue;
			}
		}
	}
	
	public void run() {
		generatePrimeFactors();
		lock.lock();
		try {
			System.out.println("Thread #" + Thread.currentThread().getId() + " generated " + factors);
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		// Factorization of 36 with a separate thread
		System.out.println("Factorization of 36");
		RunnableCancellableInterruptiblePrimeFactorizer runnable = new RunnableCancellableInterruptiblePrimeFactorizer(36, 2, (long)Math.sqrt(36));
		Thread thread = new Thread(runnable);
		System.out.println("Thread #" + thread.getId() + 
			" performs factorization in the range of " + runnable.getFrom() + "->" + runnable.getTo());
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Final result: " + runnable.getPrimeFactors() + "\n");
		
		// Factorization of 36 with 2-step thread termination
		System.out.println("Factorization of 36 with 2-step thread termination");
		RunnableCancellableInterruptiblePrimeFactorizer runnable2 = new RunnableCancellableInterruptiblePrimeFactorizer(36, 2, (long)Math.sqrt(36));
		Thread thread2 = new Thread(runnable2);
		System.out.println("Thread #" + thread2.getId() + 
			" performs factorization in the range of " + runnable2.getFrom() + "->" + runnable2.getTo());
		thread2.start();
		runnable2.setDone();
		thread2.interrupt();
		try {
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Final result: " + runnable2.getPrimeFactors() + "\n");
	}
}