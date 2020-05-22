package edu.umb.cs.cs681.hw17;

public class TestClient {

	public static void main(String[] args) {
		StockQuoteObservable stockquote = new StockQuoteObservable();
		stockquote.changeQuote("Boeing", 128);
		stockquote.addObserver( (Observable o, Object obj)->{System.out.println("Observer1: " + obj);} );
		stockquote.addObserver( (Observable o, Object obj)->{System.out.println("Observer2: " + obj);} );
		System.out.println("\nObservers counted: " + stockquote.countObservers() + "\n");
		stockquote.changeQuote("Apple", 282);
		stockquote.notifyObservers(stockquote.getMap());
		stockquote.changeQuote("Apple", 283);
		stockquote.notifyObservers(stockquote.getMap());
		stockquote.addObserver( (Observable o, Object obj)->{System.out.println("Observer3: " + obj);} );
		stockquote.changeQuote("Caterpillar", 114);
		stockquote.notifyObservers(stockquote.getMap());
        System.out.println("\nObservers counted: " + stockquote.countObservers() + "\n");		
        
        System.out.println();		
		
		DJIAQuoteObservable djiaquote = new DJIAQuoteObservable();
		djiaquote.changeQuote(23809);
		djiaquote.addObserver( (Observable o, Object obj)->{System.out.println("Observer1: " + obj);} );
		djiaquote.addObserver( (Observable o, Object obj)->{System.out.println("Observer2: " + obj);} );
		System.out.println("\nObservers counted: " + djiaquote.countObservers() + "\n");
		djiaquote.changeQuote(23780);
		djiaquote.notifyObservers(djiaquote.getQuote());
		System.out.println("\nObservers counted: " + djiaquote.countObservers() + "\n");
		djiaquote.addObserver( (Observable o, Object obj)->{System.out.println("Observer3: " + obj);} );
		djiaquote.changeQuote(23701);
		djiaquote.notifyObservers(djiaquote.getQuote());
		System.out.println("\nObservers counted: " + djiaquote.countObservers() + "\n");
	}
}
