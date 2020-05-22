package edu.umb.cs.cs681.hw11;

import java.util.LinkedList;

public class TestClient implements Runnable {
	private Customer customer= new Customer(new Address("Field's Corner St", "Dorchester", "MA", 2124));;

	public void run() {
		System.out.println(customer.getAddress());
	    customer.setAddress(new Address("Shawmut St", "Ashmont", "MA", 2116));
		System.out.println(customer.getAddress());
		customer.setAddress(customer.getAddress().change("Park St", "Dorchester", "MA", 2123));
		System.out.println(customer.getAddress());
	}

	public static void main(String[] args) {
		LinkedList<Thread> thread = new LinkedList<Thread>();
		Thread threads;
		for (int i = 0; i < 5; i++) {
			threads = new Thread(new TestClient());
			threads.start();
			thread.add(threads);
		}
		thread.forEach((Thread t) -> {
				try {
					t.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		});
	}
}