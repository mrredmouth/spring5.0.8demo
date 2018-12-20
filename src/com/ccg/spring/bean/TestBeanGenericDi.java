package com.ccg.spring.bean;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccg.spring.bean.generic.di.UserServiceDemo;

public class TestBeanGenericDi {

	/**
	 * 测试新特性：泛型依赖注入创建bean。
	 * 引用关系在父类建立，子类也会继承这个关系，并自动注入对应的对象
	 * 		泛型父类：BaseService<T> 引用 BaseRepository<T>。
	 * 		子类继承的父类：BaseService<User> 引用 BaseRepository<User>
	 * 		则子类：UserService创建bean的时候，会注入BaseRepository<User>的子类UserRepository
	 */
	@SuppressWarnings("resource")
	@Test
	public void testGenericDi(){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-genericdi.xml");
		
		UserServiceDemo userService = (UserServiceDemo) ctx.getBean("userServiceDemo");
		System.out.println(userService);
		userService.add();
	}
}
