package edu.umb.cs.cs681.hw01;

import java.util.HashMap;
import java.util.LinkedList;

public class StockQuoteObservable extends Observable {
	
	
	private HashMap<String, Float> map;
	
	public StockQuoteObservable() {
		observers = new LinkedList<Observer>();
		map = new HashMap<>();
	}
	
	public HashMap<String, Float> getMap() {
		return map;
	}	
	
	public void changeQuote(String t,float q) {
		
		this.map.put(t,q);
		setChanged();
		notifyObservers(new StockEvent(t,q));
		System.out.println();
		System.out.println(getClass().getSimpleName());
		System.out.println("See quote:");
		System.out.println(map);
	}

}
