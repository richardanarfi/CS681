package edu.umb.cs.cs681.hw21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorTestClient {

	public static void main(String[] args) {

		System.out.println("Generating Primes...\n");
		
        // With single thread executor
		System.out.println("With single thread executor");
		RunnablePrimeGenerator g = new RunnablePrimeGenerator(1, 2000000);
		
		double startTime1 = System.currentTimeMillis();
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(g);
		executor.shutdown();
//		executor.shutdownNow();
		try {
			executor.awaitTermination(60, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	    double overhead1 = System.currentTimeMillis() - startTime1;
		
		long primeNum = g.getPrimes().size();
		System.out.println(primeNum + " prime numbers are found in total.\n");

        // With pool size 2	
		System.out.println("With pool size 2");
		RunnablePrimeGenerator g2_1 = new RunnablePrimeGenerator(1, 1000000);
		RunnablePrimeGenerator g2_2 = new RunnablePrimeGenerator(1000001, 2000000);
		
		double startTime2 = System.currentTimeMillis();
		ExecutorService executor2 = Executors.newFixedThreadPool(2);
		executor2.execute(g2_1);
		executor2.execute(g2_2);
		executor2.shutdown();
//		executor.shutdownNow();
		try {
			executor2.awaitTermination(60, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	    double overhead2 = System.currentTimeMillis() - startTime2;
		
		long primeNum2 = g2_1.getPrimes().size() + g2_2.getPrimes().size();
		System.out.println(primeNum2 + " prime numbers are found in total.\n");    
	    
        // With pool size 4	
		System.out.println("With pool size 4");
		RunnablePrimeGenerator g4_1 = new RunnablePrimeGenerator(1, 500000);
		RunnablePrimeGenerator g4_2 = new RunnablePrimeGenerator(500001, 1000000);
		RunnablePrimeGenerator g4_3 = new RunnablePrimeGenerator(1000001, 1500000);
		RunnablePrimeGenerator g4_4 = new RunnablePrimeGenerator(1500001, 2000000);
		
		double startTime4 = System.currentTimeMillis();
		ExecutorService executor4 = Executors.newFixedThreadPool(4);
		executor4.execute(g4_1);
		executor4.execute(g4_2);
		executor4.execute(g4_3);
		executor4.execute(g4_4);
		executor4.shutdown();
//		executor.shutdownNow();
		try {
			executor4.awaitTermination(60, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	    double overhead4 = System.currentTimeMillis() - startTime4;
		
		long primeNum4 = g4_1.getPrimes().size() + g4_2.getPrimes().size() + g4_3.getPrimes().size() + g4_4.getPrimes().size();
		System.out.println(primeNum4 + " prime numbers are found in total.\n");		
	    
        // With pool size 8
		System.out.println("With pool size 8");
		RunnablePrimeGenerator g8_1 = new RunnablePrimeGenerator(1, 250000);
		RunnablePrimeGenerator g8_2 = new RunnablePrimeGenerator(250001, 500000);
		RunnablePrimeGenerator g8_3 = new RunnablePrimeGenerator(500001, 750000);
		RunnablePrimeGenerator g8_4 = new RunnablePrimeGenerator(750001, 1000000);
		RunnablePrimeGenerator g8_5 = new RunnablePrimeGenerator(1000001,1250000);
		RunnablePrimeGenerator g8_6 = new RunnablePrimeGenerator(1250001,1500000);
		RunnablePrimeGenerator g8_7 = new RunnablePrimeGenerator(1500001,1750000);
		RunnablePrimeGenerator g8_8 = new RunnablePrimeGenerator(1750001,2000000);
		
		double startTime8 = System.currentTimeMillis();
		ExecutorService executor8 = Executors.newFixedThreadPool(8);
		executor8.execute(g8_1);
		executor8.execute(g8_2);
		executor8.execute(g8_3);
		executor8.execute(g8_4);
		executor8.execute(g8_5);
		executor8.execute(g8_6);
		executor8.execute(g8_7);
		executor8.execute(g8_8);
		executor8.shutdown();
//		executor.shutdownNow();
		try {
			executor8.awaitTermination(60, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	    double overhead8 = System.currentTimeMillis() - startTime8;
		
		long primeNum8 = g8_1.getPrimes().size() + g8_2.getPrimes().size() + g8_3.getPrimes().size() + g8_4.getPrimes().size() + g8_5.getPrimes().size() + g8_6.getPrimes().size() + g8_7.getPrimes().size() + g8_8.getPrimes().size();
		System.out.println(primeNum8 + " prime numbers are found in total.\n");		
	    
        // With pool size 16
		System.out.println("With pool size 16");
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
		
		double startTime16 = System.currentTimeMillis();
		ExecutorService executor16 = Executors.newFixedThreadPool(16);
		executor16.execute(g16_1);
		executor16.execute(g16_2);
		executor16.execute(g16_3);
		executor16.execute(g16_4);
		executor16.execute(g16_5);
		executor16.execute(g16_6);
		executor16.execute(g16_7);
		executor16.execute(g16_8);
		executor16.execute(g16_9);
		executor16.execute(g16_10);
		executor16.execute(g16_11);
		executor16.execute(g16_12);
		executor16.execute(g16_13);
		executor16.execute(g16_14);
		executor16.execute(g16_15);
		executor16.execute(g16_16);
		executor16.shutdown();
//		executor.shutdownNow();
		try {
			executor16.awaitTermination(60, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	    double overhead16 = System.currentTimeMillis() - startTime16;
		
		long primeNum16 = g16_1.getPrimes().size() + g16_2.getPrimes().size() + g16_3.getPrimes().size() + g16_4.getPrimes().size() + g16_5.getPrimes().size() + g16_6.getPrimes().size() + g16_7.getPrimes().size() + g16_8.getPrimes().size() + g16_9.getPrimes().size() + g16_10.getPrimes().size() + g16_11.getPrimes().size() + g16_12.getPrimes().size() + g16_13.getPrimes().size() + g16_14.getPrimes().size() + g16_15.getPrimes().size() + g16_16.getPrimes().size();
		System.out.println(primeNum16 + " prime numbers are found in total.\n");		
	
		System.out.println("\nSUMMARY OF RESULTS\n");
		System.out.println("=============================");
		System.out.println("|   Pool Size  | Time (sec) |");
		System.out.println("=============================");
	    System.out.println("|       1      |    " + (overhead1/1000) + "   |" );		
		System.out.println("-----------------------------");
	    System.out.println("|       2      |    " + (overhead2/1000) + "   |" ); 
		System.out.println("-----------------------------");
	    System.out.println("|       4      |    " + (overhead4/1000) + "   |" );
		System.out.println("-----------------------------");
	    System.out.println("|       8      |    " + (overhead8/1000) + "   |" );
		System.out.println("-----------------------------");
	    System.out.println("|      16      |    " + (overhead16/1000) + "   |" );
		System.out.println("=============================");

	}
}