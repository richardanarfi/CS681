package edu.umb.cs.cs681.hw05;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator implements Runnable {
    
	
	public RunnableCancellablePrimeGenerator(long from, long to) {
		super(from, to);
	}

	ReentrantLock lock = new ReentrantLock();
	private boolean done = false;

	public void setDone() {
		lock.lock();
		try {
			done = true;			
		} finally {
			lock.unlock();
		}
	}
	
	public void generatePrimes() {
		for (long n = from; n <= to; n++) {
			lock.lock();
			try {
				if (done) {
					System.out.println("Stopped...done accessed with lock");
					this.primes.clear();
					break;
				}
				if (isPrime(n)) {
					this.primes.add(n);
				}
			} finally {
				lock.unlock();
			}
		}
	}
	
	public void run() {
		generatePrimes();
	}
	
	public static void main(String[] args) {
		// Without accessing done.
		System.out.println("Prime generator [1, 100]");
		RunnableCancellablePrimeGenerator g = new RunnableCancellablePrimeGenerator(1, 100);
		Thread t = new Thread(g);
		t.start();		
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		g.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + g.getPrimes().size() + " prime numbers are found.\n");
		
		// Accessing done
		System.out.println("Prime generator [1, 100] ...access done with lock");
		RunnableCancellablePrimeGenerator g2 = new RunnableCancellablePrimeGenerator(1, 100);
		Thread t2 = new Thread(g2);
		t2.start();
        g2.setDone();		
		try {
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		g2.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + g2.getPrimes().size() + " prime numbers are found.\n");
	}
}
