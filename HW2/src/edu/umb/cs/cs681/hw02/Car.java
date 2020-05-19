package edu.umb.cs.cs681.hw02;

import java.util.ArrayList;

public class Car {
	private int price, year, mileage;
	private String model;
	
	public Car(String model, int price, int year, int mileage) {
		this.model = model;
		this.price = price;
		this.year = year;
		this.mileage = mileage;
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
		
		cars.add(new Car("Benz", 20000, 2018, 10000));
		cars.add(new Car("BMW", 3000, 2012, 1050000));
		cars.add(new Car("Hyundai", 10000, 2010, 50000));
		cars.add(new Car("Honda", 2000, 2017, 30000));
		cars.add(new Car("Toyota", 1000, 2003, 75000));
		cars.add(new Car("Audi", 100, 2018, 750));
		
		Integer minPrice = cars.stream()
				.map((Car car)-> car.getPrice())
				.reduce(0, (result, carPrice)->{
					if(result==0) return carPrice;
					else if(carPrice < result) return carPrice;
					else return result;} );
		
		Integer maxPrice = cars.stream()
				.map((Car car)-> car.getPrice())
				.reduce(0, (result, carPrice)->{
					if(result==0) return carPrice;
					else if(carPrice > result) return carPrice;
					else return result;} );
				
		Integer minYear = cars.stream()
				.map((Car car)-> car.getYear())
				.reduce(0, (result, carYear)->{
					if(result==0) return carYear;
					else if(carYear < result) return carYear;
					else return result;} );
				
		Integer maxYear = cars.stream()
				.map((Car car)-> car.getYear())
				.reduce(0, (result, carYear)->{
					if(result==0) return carYear;
					else if(carYear > result) return carYear;
					else return result;} );
					
		Integer minMileage = cars.stream()
				.map((Car car)-> car.getMileage())
				.reduce(0, (result, carMileage)->{
					if(result==0) return carMileage;
					else if(carMileage < result) return carMileage;
					else return result;} );
				
		Integer maxMileage = cars.stream()
				.map((Car car)-> car.getMileage())
				.reduce(0, (result, carMileage)->{
					if(result==0) return carMileage;
					else if(carMileage > result) return carMileage;
					else return result;} );

		Integer count = cars.stream()
				.map((Car car)-> car.getPrice())
				.reduce(0, (result, carPrice)->{
					if(carPrice < 5000) result++;
		    		return result;} );
		    				
		System.out.println("Minimum Price: " + minPrice);
		System.out.println("Maximum Price: " + maxPrice);
		System.out.println();
		System.out.println("Minimum Year: " + minYear);
		System.out.println("Maximum Year: " + maxYear);
		System.out.println();
		System.out.println("Minimum Mileage: " + minMileage);
		System.out.println("Maximum Mileage: " + maxMileage);
		System.out.println();
		System.out.println("Number of cars that cost less than $5000: " + count);
	}
}
