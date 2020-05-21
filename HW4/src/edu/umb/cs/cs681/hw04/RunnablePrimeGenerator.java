package edu.umb.cs.cs681.hw04;

public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable {
	
	public RunnablePrimeGenerator(long from, long to) {
		super(from, to);
	}
	
	public void run() {
		generatePrimes();
	}

	public static void main(String[] args) {

		System.out.println("Generating Primes...");
		
        // With 1 thread
		RunnablePrimeGenerator g = new RunnablePrimeGenerator(1, 2000000);
		Thread t = new Thread(g);
		
		long startTime1Thread = System.currentTimeMillis();
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {}
		
	    long overheadThread1 = System.currentTimeMillis() - startTime1Thread;

//	    g.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
		
		long primeNum = g.getPrimes().size();
		System.out.println("\n" + primeNum + " prime numbers are found in total.");
		
		System.out.println("=============================");
		System.out.println("| # of threads | Time (sec) |");
		System.out.println("=============================");
	    System.out.println("|       1      |   " + ((double)(overheadThread1)/1000) + "   |" );

        // With 2 threads	
		RunnablePrimeGenerator g2_1 = new RunnablePrimeGenerator(1, 1000000);
		RunnablePrimeGenerator g2_2 = new RunnablePrimeGenerator(1000001, 2000000);
		Thread t1 = new Thread(g2_1);
		Thread t2 = new Thread(g2_2);
		
		long startTime2Threads = System.currentTimeMillis();
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {}
		
	    long overheadThread2 = System.currentTimeMillis() - startTime2Threads;
		System.out.println("-----------------------------");
	    System.out.println("|       2      |    " + ((double)(overheadThread2)/1000) + "   |" );    
	    
        // With 4 threads	
		RunnablePrimeGenerator g4_1 = new RunnablePrimeGenerator(1, 500000);
		RunnablePrimeGenerator g4_2 = new RunnablePrimeGenerator(500001, 1000000);
		RunnablePrimeGenerator g4_3 = new RunnablePrimeGenerator(1000001, 1500000);
		RunnablePrimeGenerator g4_4 = new RunnablePrimeGenerator(1500001, 2000000);
		Thread t4_1 = new Thread(g4_1);
		Thread t4_2 = new Thread(g4_2);
		Thread t4_3 = new Thread(g4_3);
		Thread t4_4 = new Thread(g4_4);
		
		long startTime4Threads = System.currentTimeMillis();
		t4_1.start();
		t4_2.start();
		t4_3.start();
		t4_4.start();
		try {
			t4_1.join();
			t4_2.join();
			t4_3.join();
			t4_4.join();
		} catch (InterruptedException e) {}

	    long overheadThread4 = System.currentTimeMillis() - startTime4Threads;
		System.out.println("-----------------------------");
	    System.out.println("|       4      |    " + ((double)(overheadThread4)/1000) + "   |" );
	    
        // With 8 threads
		
		RunnablePrimeGenerator g8_1 = new RunnablePrimeGenerator(1, 250000);
		RunnablePrimeGenerator g8_2 = new RunnablePrimeGenerator(250001, 500000);
		RunnablePrimeGenerator g8_3 = new RunnablePrimeGenerator(500001, 750000);
		RunnablePrimeGenerator g8_4 = new RunnablePrimeGenerator(750001, 1000000);
		RunnablePrimeGenerator g8_5 = new RunnablePrimeGenerator(1000001,1250000);
		RunnablePrimeGenerator g8_6 = new RunnablePrimeGenerator(1250001,1500000);
		RunnablePrimeGenerator g8_7 = new RunnablePrimeGenerator(1500001,1750000);
		RunnablePrimeGenerator g8_8 = new RunnablePrimeGenerator(1750001,2000000);
		Thread t8_1 = new Thread(g8_1);
		Thread t8_2 = new Thread(g8_2);
		Thread t8_3 = new Thread(g8_3);
		Thread t8_4 = new Thread(g8_4);
		Thread t8_5 = new Thread(g8_5);
		Thread t8_6 = new Thread(g8_6);
		Thread t8_7 = new Thread(g8_7);
		Thread t8_8 = new Thread(g8_8);
		
		long startTime8Threads = System.currentTimeMillis();
		t8_1.start();
		t8_2.start();
		t8_3.start();
		t8_4.start();
		t8_5.start();
		t8_6.start();
		t8_7.start();
		t8_8.start();
		try {
			t8_1.join();
			t8_2.join();
			t8_3.join();
			t8_4.join();
			t8_5.join();
			t8_6.join();
			t8_7.join();
			t8_8.join();
		} catch (InterruptedException e) {}

	    long overheadThread8 = System.currentTimeMillis() - startTime8Threads;
		System.out.println("-----------------------------");
	    System.out.println("|       8      |    " + ((double)(overheadThread8)/1000) + "   |" );
	    
        // With 16 threads
		
		RunnablePrimeGenerator g16_1 = new RunnablePrimeGenerator(1,      125000);
		RunnablePrimeGenerator g16_2 = new RunnablePrimeGenerator(125001, 250000);
		RunnablePrimeGenerator g16_3 = new RunnablePrimeGenerator(250001, 375000);
		RunnablePrimeGenerator g16_4 = new RunnablePrimeGenerator(375001, 500000);
		RunnablePrimeGenerator g16_5 = new RunnablePrimeGenerator(500001, 625000);
		RunnablePrimeGenerator g16_6 = new RunnablePrimeGenerator(625001, 750000);
		RunnablePrimeGenerator g16_7 = new RunnablePrimeGenerator(750001, 875000);
		RunnablePrimeGenerator g16_8 = new RunnablePrimeGenerator(875001,  1000000);
		RunnablePrimeGenerator g16_9 = new RunnablePrimeGenerator(1000001, 1125000);
		RunnablePrimeGenerator g16_10 = new RunnablePrimeGenerator(1125001,1250000);
		RunnablePrimeGenerator g16_11 = new RunnablePrimeGenerator(1250001,1375000);
		RunnablePrimeGenerator g16_12 = new RunnablePrimeGenerator(1375001, 1500000);
		RunnablePrimeGenerator g16_13 = new RunnablePrimeGenerator(1500001, 1625000);
		RunnablePrimeGenerator g16_14 = new RunnablePrimeGenerator(1625001, 1750000);
		RunnablePrimeGenerator g16_15 = new RunnablePrimeGenerator(1750001, 1875000);
		RunnablePrimeGenerator g16_16 = new RunnablePrimeGenerator(1875001, 2000000);
		
		Thread t16_1 = new Thread(g16_1);
		Thread t16_2 = new Thread(g16_2);
		Thread t16_3 = new Thread(g16_3);
		Thread t16_4 = new Thread(g16_4);
		Thread t16_5 = new Thread(g16_5);
		Thread t16_6 = new Thread(g16_6);
		Thread t16_7 = new Thread(g16_7);
		Thread t16_8 = new Thread(g16_8);
		Thread t16_9 = new Thread(g16_9);
		Thread t16_10 = new Thread(g16_10);
		Thread t16_11 = new Thread(g16_11);
		Thread t16_12 = new Thread(g16_12);
		Thread t16_13 = new Thread(g16_13);
		Thread t16_14 = new Thread(g16_14);
		Thread t16_15 = new Thread(g16_15);
		Thread t16_16 = new Thread(g16_16);
		long startTime16Threads = System.currentTimeMillis();
		t16_1.start();
		t16_2.start();
		t16_3.start();
		t16_4.start();
		t16_5.start();
		t16_6.start();
		t16_7.start();
		t16_8.start();
		t16_9.start();
		t16_10.start();
		t16_11.start();
		t16_12.start();
		t16_13.start();
		t16_14.start();
		t16_15.start();
		t16_16.start();
		try {
			t16_1.join();
			t16_2.join();
			t16_3.join();
			t16_4.join();
			t16_5.join();
			t16_6.join();
			t16_7.join();
			t16_8.join();
			t16_9.join();
			t16_10.join();
			t16_11.join();
			t16_12.join();
			t16_13.join();
			t16_14.join();
			t16_15.join();
			t16_16.join();
		} catch (InterruptedException e) {}

	    long overheadThread16 = System.currentTimeMillis() - startTime16Threads;
		System.out.println("-----------------------------");
	    System.out.println("|      16      |    " + ((double)(overheadThread16)/1000) + "   |" );
		System.out.println("=============================");

	}

}
