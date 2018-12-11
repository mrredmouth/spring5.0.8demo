package com.ccg.spring.bean.instancefactory;

import java.util.HashMap;
import java.util.Map;

import com.ccg.spring.bean.Car;

/**
 * 实例工厂方法配置Bean，需要创建工厂本身，再调用实例方法创建bean
 * @author Administrator
 *
 */
public class InstanceCarFactory {
	private Map<String,Car> cars = null;
	public InstanceCarFactory(){
		cars = new HashMap<String, Car>();
		cars.put("audi", new Car("audi",350000));
		cars.put("benchi", new Car("benchi",330000));
	}
	
	public Car getCar(String brand){
		return cars.get(brand);
	}
}
