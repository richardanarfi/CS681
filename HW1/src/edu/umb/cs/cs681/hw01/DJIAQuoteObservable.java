package edu.umb.cs.cs681.hw01;

import java.util.LinkedList;

public class DJIAQuoteObservable extends Observable {
	
	private float quote;

	public DJIAQuoteObservable() {
		observers = new LinkedList<Observer>();
	}	
	public float getQuote() {
		return quote;
	}

	public void changeQuote(float q)
	{		
		this.quote = q;
		setChanged();
		notifyObservers(new DJIAEvent(q));
    	System.out.println();
    	System.out.println(getClass().getSimpleName());
		System.out.println("See quote:");
    	System.out.println(quote);		
	}

}

