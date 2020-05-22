package edu.umb.cs.cs681.hw15;

import java.util.concurrent.locks.ReentrantLock;
import java.util.LinkedList;

public class DJIAQuoteObservable extends Observable {
	
	private float quote;
	private ReentrantLock djialock = new ReentrantLock();

	public DJIAQuoteObservable() {
		observers = new LinkedList<Observer>();
	}	
	public float getQuote() {
		djialock.lock();
		try {
		    return quote;
		} finally {
			djialock.unlock();
		}
	}

	public void changeQuote(float q){
		djialock.lock();
		try {		
		    this.quote = q;
		    setChanged();
		    notifyObservers(new DJIAEvent(q));
    	    System.out.println();
    	    System.out.println(getClass().getSimpleName());
		    System.out.println("See quote:");
    	    System.out.println(quote);	
		} finally {
			djialock.unlock();
		}		
	}

}

