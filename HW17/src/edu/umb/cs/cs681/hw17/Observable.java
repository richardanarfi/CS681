package edu.umb.cs.cs681.hw17;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Observable {
	private AtomicBoolean changed = new AtomicBoolean(false);
    protected ConcurrentLinkedQueue<Observer> observers;
	
    
    protected void addObserver(Observer observer) {
    	if(!observers.contains(observer)) {
    		observers.add(observer);
    		}
		changed.set(false);
    }

    protected void setChanged() {
        changed.set(true);
    }
    
    protected void clearChanged() {
        changed.set(false);
    }

    public void notifyObservers(Object arg) {
    	System.out.println(getClass().getSimpleName() + " Notify Observers...");		
	    if(hasChanged()) {
			for (Observer observer: observers) {
				observer.update(this, arg);
			}				
	    	clearChanged();
	    }		
    }

    public boolean hasChanged() {
        return changed.get();
    }
    
    public int countObservers() {
    	return observers.size();
    }
	
    protected void deleteObserver(Observer o) {
    	observers.remove(o);
    }

    protected void deleteObservers() {
    	observers.removeAll(observers);
    }
}