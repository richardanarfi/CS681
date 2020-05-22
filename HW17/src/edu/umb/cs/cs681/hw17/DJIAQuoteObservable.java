package edu.umb.cs.cs681.hw17;

import java.util.concurrent.ConcurrentLinkedQueue;

public class DJIAQuoteObservable extends Observable {
	
	private float quote;

	public DJIAQuoteObservable() {
		observers = new ConcurrentLinkedQueue<Observer>();
	}	
	public float getQuote() {
		return quote;
	}

	public void changeQuote(float q){		
		this.quote = q;
		setChanged();
		notifyObservers(new DJIAEvent(q));
    	System.out.println();
    	System.out.println(getClass().getSimpleName());
		System.out.println("See quote:");
    	System.out.println(quote);	
	}

}

