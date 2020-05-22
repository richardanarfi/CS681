package edu.umb.cs.cs681.hw14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;

class AdmissionControl {
	private int currentVisitors;

	private ReentrantLock lock;
	private  volatile boolean done = false;
	private Condition sufficientCondition;

	private AdmissionControl control;

	public AdmissionControl(){
		 control = this;
		 currentVisitors = 0;
		 lock = new ReentrantLock();
		 sufficientCondition = lock.newCondition();
	}

	public void enter() {
		lock.lock();
		try{
			while(currentVisitors >= 5){
				System.out.println("Too many visitors. Please wait for a while!");
				sufficientCondition.await();
			}

			currentVisitors ++;
			System.out.print(Thread.currentThread().getId());
			System.out.println("current Visitors: " + currentVisitors);

		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
		}

	}

	public void exit() {
		lock.lock();
		try{
			System.out.println("current Visitors: " + currentVisitors);
			currentVisitors --;

			System.out.println(Thread.currentThread().getId() + " EXIT");
			System.out.println("current Visitors: " + currentVisitors);
			sufficientCondition.signalAll();
		}
		finally{
			lock.unlock();
		}
	}

	public int countCurrentVisitors() {
		lock.lock();
		try{
			System.out.println(Thread.currentThread().getId() + " current Visitors: " + currentVisitors);
			return currentVisitors;
		}
		finally{
			lock.unlock();
		}
	}

	public void setDone() {
			done = true;
	}

	public void resetDone() {
		 done = false;
	}


	public class EntranceHandler implements Runnable {
		public void run(){
			while(done == false){
				control.enter();
			}
		}
	}

	public class ExitHandler implements Runnable {
		public void run(){
			while(done == false){
				control.exit();
			}
		}
	}

	public class MonitorHandler implements Runnable {
		public void run() {
			control.countCurrentVisitors();
		}
	}
	
	public static void main(String[] args) {

		AdmissionControl control = new AdmissionControl();
		int MAXTHREADS = 4;
		ArrayList<Thread> threads = new ArrayList<Thread>(MAXTHREADS);


		for (int i = 0; i <MAXTHREADS - 2 ; i++) {
			Thread t = new Thread(control.new EntranceHandler());
			threads.add(t);
			t.start();
		}

		Thread tm = new Thread(control.new MonitorHandler());
		threads.add(tm);
		tm.start();

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int i = 0; i <1 ; i++){
			Thread texit = new Thread(control.new ExitHandler());
			threads.add(texit);
			texit.start();
		}

		try {
			Thread.sleep(100);
		} catch (InterruptedException e){
			e.printStackTrace();
		}

		for (int i = 0; i <MAXTHREADS; i++){
			 threads.get(i).interrupt();
		}

		control.setDone();
		for (int i = 0; i <MAXTHREADS; i++){
			try {
				threads.get(i).join();
			}
			catch (Exception e) {
			}
		}

		control.resetDone();
		Thread tt = new Thread(control.new MonitorHandler());
		tt.start();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}