package edu.umb.cs.cs681.hw13;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestClient {
	private double balance = 0;
	private Boolean done = false;
	private ReentrantLock lock;
	private Condition sufficientFundsCondition;
	private Condition belowUpperLimitFundsCondition;
	private TestClient account;

	public TestClient() {
		lock = new ReentrantLock();
		done = false;		
		sufficientFundsCondition = lock.newCondition();
		belowUpperLimitFundsCondition = lock.newCondition();
		account = this;
	}

	public void setDone(){
		lock.lock();
		try { 
			done = true; 
		} finally {
			lock.unlock(); 
		} 
	}

	public void deposit(double amount) {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getId() + " current balance: " + balance);
			while (balance >= 300) {

				System.out.println(Thread.currentThread().getId() + "  belowUpperLimitFundsCondition funds");
				belowUpperLimitFundsCondition.await();

			}
			balance += amount;
			System.out.println("Deposit : " + amount + " New Balance: " + balance);
			sufficientFundsCondition.signalAll();
		} catch (InterruptedException e) {
//			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void withdraw(double amount) {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getId() + " current balance: " + balance);

			while (balance <= 0) {
				System.out.println(Thread.currentThread().getId() + "  Insufficient funds");
				sufficientFundsCondition.await();
			}
			balance -= amount;
			System.out.println("WithDraw : " + amount + " New Balance: " + balance);
			belowUpperLimitFundsCondition.signalAll();
		} catch (InterruptedException e) {
//			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	private class DepositRunnable implements Runnable {

		public void run() {
			try {
				while (true) {
					if (done) {
						break;
					}
					account.deposit(100);
					Thread.sleep(10);
				}
		} catch (InterruptedException e) {
//			e.printStackTrace();
			}
		}
	}

	private class WithdrawRunnable implements Runnable {

		public void run() {
			try {
				while (true) {
					if (done) {
						break;
					}
					account.withdraw(100);
					Thread.sleep(10);
				}
		} catch (InterruptedException e) {
//			e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		TestClient threadSafeBA = new TestClient();
		LinkedList<Thread> threadList = new LinkedList<Thread>();
		for (int i = 0; i < 10; i++) {
			Thread depositThread = new Thread(threadSafeBA.new DepositRunnable());
			depositThread.start();
			threadList.add(depositThread);
			Thread withdrawThread = new Thread(threadSafeBA.new WithdrawRunnable());
			withdrawThread.start();
			threadList.add(withdrawThread);
		}
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
//            e.printStackTrace();
		}
		for (Thread t : threadList) {
			t.interrupt();
			threadSafeBA.setDone();
			try {
				t.join();
			} catch (Exception e) {
//                e.printStackTrace();
			}
		}
	}
}