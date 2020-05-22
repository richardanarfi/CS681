package edu.umb.cs.cs681.hw19;

import java.util.ArrayList;

public class Car {
	private int price, year, mileage;
	private String make;
	private String model;
	
	public Car(String make, String model, int price, int year, int mileage) {
		this.make = make;
		this.model = model;
		this.price = price;
		this.year = year;
		this.mileage = mileage;
	}
	
	public String getMake() {
		return make;
	}	
	public String getModel() {
		return model;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getMileage() {
		return mileage;
	}
	
	public static void main(String[] args) {
		
		ArrayList<Car> cars = new ArrayList<Car>();
		
		cars.add(new Car("Benz","C-Class",20000,2018,10000));
		cars.add(new Car("BMW","E-Series",3000,2012,1050000));
		cars.add(new Car("Hyundai","Elantra",10000,2010,50000));
		cars.add(new Car("Honda","Accord",2000,2017,30000));
		cars.add(new Car("Toyota","Camry",1000,2003,75000));
		cars.add(new Car("Audi","A7",100,2018,750));
		
									
		Integer minMileage = cars.stream()
		        .parallel()
				.map((Car car)-> car.getMileage())
				.reduce(0, (result, carMileage)->{
					if(result==0) return carMileage;
					else if(carMileage < result) return carMileage;
					else return result;}, 
					(finalResult, interMediateResult)->{
					if(finalResult < interMediateResult) return finalResult;
					else return interMediateResult;}
					);
				
		Integer maxMileage = cars.stream()
		        .parallel()
				.map((Car car)-> car.getMileage())
				.reduce(0, (result, carMileage)->{
					if(result==0) return carMileage;
					else if(carMileage > result) return carMileage;
					else return result;}, 
					(finalResult, interMediateResult)->{
					if(finalResult > interMediateResult) return finalResult;
					else return interMediateResult;} 
					);

		Integer count = cars.stream()
				.parallel()
                .map( (Car car)-> car.getModel() )
                .reduce(0,
                (result,carModel)-> {return ++result;},
                (finalResult,intermediateResult)->{
                return finalResult + intermediateResult; }
		    	);			

		System.out.println("Minimum Mileage: " + minMileage);
		System.out.println();
		System.out.println("Maximum Mileage: " + maxMileage);
		System.out.println();
		System.out.println("Number of car models: " + count);
	}
}
