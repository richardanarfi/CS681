package edu.umb.cs.cs681.hw15;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable {
	private AtomicBoolean changed = new AtomicBoolean(false);
	private ReentrantLock obslock = new ReentrantLock();
    protected LinkedList<Observer> observers;
	
    
    protected void addObserver(Observer observer) {
		obslock.lock();
		try {
    	if(!observers.contains(observer)) {
    		observers.add(observer);
    		}
		    changed.set(false);
		} finally {
			obslock.unlock();
		}
    }

    protected void setChanged() {
		obslock.lock();
		try {
            changed.set(true);
		} finally {
		    obslock.unlock();	
		}
    }
    
    protected void clearChanged() {
		obslock.lock();
		try {
        changed.set(false);
		} finally {
		    obslock.unlock();	
		}
    }

    public void notifyObservers(Object arg) {
    	System.out.println(getClass().getSimpleName() + " Notify Observers...");
		obslock.lock();
		try {		
		    if(hasChanged()) {
				for (Observer observer: observers) {
					observer.update(this, arg);
				}				
		    	clearChanged();
		    }
		} finally {
		    obslock.unlock();	
		}			
    }

    public boolean hasChanged() {
		obslock.lock();
		try {
            return changed.get();
		} finally {
		    obslock.unlock();	
		}
    }
    
    public int countObservers() {
		obslock.lock();
		try {
    	    return observers.size();
		} finally {
		    obslock.unlock();	
		}
    }
	
    protected void deleteObserver(Observer o) {
		obslock.lock();
		try {
    	    observers.remove(o);
		} finally {
		    obslock.unlock();	
		}
    }

    protected void deleteObservers() {
		obslock.lock();
		try {
    	    observers.removeAll(observers);
		} finally {
		    obslock.unlock();	
		}
    }
}