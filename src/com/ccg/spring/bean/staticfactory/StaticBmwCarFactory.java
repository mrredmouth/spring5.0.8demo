package com.ccg.spring.bean.staticfactory;

import java.util.HashMap;
import java.util.Map;
import com.ccg.spring.bean.Car;

/**
 * 静态工厂方法，创建Bean时直接调用静态方法创建实例
 * @author Administrator
 *
 */
public class StaticBmwCarFactory {
	private static Map<String ,Car> cars = new HashMap<String ,Car>();
	
	static{
		cars.put("bmw1", new Car("bmw",300000));
		cars.put("mini1", new Car("mini",200000));
	}
	
	//静态工厂方法，从静态块里面取对象
	public static Car getCar(String brand){
		return cars.get(brand);
	}
	
}

