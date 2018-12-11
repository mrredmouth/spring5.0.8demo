package com.ccg.spring.bean;

import lombok.Data;

@Data
public class Car {
	private String brand;
	private double price;
	private double tyrePerimeter;//轮胎周长
	
	public Car(){
	}
	public Car(String brand,double price){
		this.brand = brand;
		this.price = price;
	}
}
