package edu.umb.cs.cs681.hw01;

import java.util.LinkedList;

abstract class Observable {

    protected boolean status;
    protected LinkedList<Observer> observers;
    
    protected void addObserver(Observer observer) {
    	if(!observers.contains(observer)) {
    		observers.add(observer);
    		}
    	status = false;
    }

    protected void setChanged() {
    	status = true;
    }
    
    protected void clearChanged() {
    	status = false;
    }

    public void notifyObservers(Object arg) {
    	System.out.println(getClass().getSimpleName() + " Notify Observers...");
		if(hasChanged()) {
			observers.forEach((Observer observers) -> observers.update(this, arg));
			clearChanged();
		}
    }

    public boolean hasChanged() {
    	return status;
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