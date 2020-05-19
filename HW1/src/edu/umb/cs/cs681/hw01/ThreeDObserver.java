package edu.umb.cs.cs681.hw01;

public class ThreeDObserver implements Observer {
public void update(Observable obs,Object arg) {
	if(arg instanceof DJIAEvent) {
			DJIAEvent dJIAEvent = (DJIAEvent) arg;
			System.out.println("3DObserver: This is an instance of a DJIAEvent class.\n");
			System.out.println("DJIA:" +dJIAEvent.getDjia() +" \n");
		}else if(arg instanceof StockEvent) {
			StockEvent stockevent = (StockEvent) arg;
			System.out.println("3DObserver: This is an instance of a StockEvent class.\n");
			System.out.println(stockevent.getTicker()+ "  "+ stockevent.getQuote()+" \n");
		}
	}
}
