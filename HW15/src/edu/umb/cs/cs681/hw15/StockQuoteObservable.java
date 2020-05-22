package edu.umb.cs.cs681.hw15;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable {
	
	
	private HashMap<String, Float> map;
	private ReentrantLock sqlock = new ReentrantLock();
	
	public StockQuoteObservable() {
		observers = new LinkedList<Observer>();
		map = new HashMap<>();
	}
	
	public HashMap<String, Float> getMap() {
		sqlock.lock();
		try {
		    return map;
		} finally {
			sqlock.unlock();
		}
	}	
	
	public void changeQuote(String t,float q) {
		sqlock.lock();
		try {		
		    this.map.put(t,q);
		    setChanged();
		    notifyObservers(new StockEvent(t,q));
		    System.out.println();
		    System.out.println(getClass().getSimpleName());
		    System.out.println("See quote:");
		    System.out.println(map);
		} finally {
			sqlock.unlock();
		}
	}
}
