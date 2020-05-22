package edu.umb.cs.cs681.hw11;

import java.util.concurrent.locks.ReentrantLock;

public class Customer {
	protected Address address;
	ReentrantLock lock = new ReentrantLock();

	public Customer(Address addr) {
		address = addr;
	}

	public void setAddress(Address addr) {
		lock.lock();
		try {
			address = addr;
		} finally {
			lock.unlock();
		}
	}

	public Address getAddress() {
		lock.lock();
		try {
			return address;
		} finally {
			lock.unlock();
		}
	}
}
