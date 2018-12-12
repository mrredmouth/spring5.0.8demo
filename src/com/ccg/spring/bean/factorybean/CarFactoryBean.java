package com.ccg.spring.bean.factorybean;

import org.springframework.beans.factory.FactoryBean;

import com.ccg.spring.bean.Car;

import lombok.Setter;

public class CarFactoryBean implements FactoryBean<Car>{

	@Setter
	private String brand;
	
	/**
	 * 返回bean实例
	 */
	@Override
	public Car getObject() throws Exception {
		return new Car(brand,500000);
	}

	/**
	 * 返回bean的类型
	 */
	@Override
	public Class<?> getObjectType() {
		return Car.class;
	}
	
	/**
	 * 是否单例
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}
}
