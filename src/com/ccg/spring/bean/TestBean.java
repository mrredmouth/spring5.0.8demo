package com.ccg.spring.bean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccg.spring.bean.annotation.TestComponent;
import com.ccg.spring.bean.annotation.controller.UserController;
import com.ccg.spring.bean.annotation.repository.UserRepository;
import com.ccg.spring.bean.annotation.service.UserService;

public class TestBean {
	
	/**
	 * 测试bean的创建
	 */
	@SuppressWarnings("resource")
	@Test
	public void tsetCreateBean(){
		
		//初始化容器创建bean：无参构造器->setter方法
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//setter方式
		Girl beantifulGirl = (Girl) ctx.getBean("beantifulGirl");
		System.out.println("beantifulGirl: "+beantifulGirl);
		//构造器方式
		Girl uglyGirl = (Girl) ctx.getBean("uglyGirl");
		System.out.println("uglyGirl: "+uglyGirl);
		//p命名空间方式
		Girl uglyGirl2 = (Girl) ctx.getBean("uglyGirl2");
		System.out.println("uglyGirl2: "+uglyGirl2);
		
		Girl beautiful2 = (Girl) ctx.getBean("beautiful2");
		System.out.println("beautiful2: "+beautiful2);
		
		Girl uglyGirl4 = (Girl) ctx.getBean("uglyGirl4");
		System.out.println("uglyGirl4: "+uglyGirl4);
		
		//继承装配
		Boy badBoy3 = (Boy) ctx.getBean("badBoy3");
		System.out.println(badBoy3);
	}
	
	/**
	 * 测试bean的作用域Scope
	 */
	@SuppressWarnings("resource")
	@Test
	public void testBeanScope(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-bean2.xml");
		
		Car audiCar1_1 = (Car) ctx.getBean("audiCar1");
		Car audiCar1_2 = (Car) ctx.getBean("audiCar1");
		System.out.println(audiCar1_1==audiCar1_2); //返回true，两个bean是同一个。scope：singleton
		

		Car audiCar2_1 = (Car) ctx.getBean("audiCar2");
		Car audiCar2_2 = (Car) ctx.getBean("audiCar2");
		System.out.println(audiCar2_1==audiCar2_2); //返回true，每次请求都调构造器，生成新的bean。scope：prototype
	}
	/**
	 * 测试Spring的EL表达式语言
	 */
	@SuppressWarnings("resource")
	@Test
	public void testSpEL(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-bean2.xml");
		
		Car audiCar3 = (Car) ctx.getBean("audiCar3");
		System.out.println(audiCar3);
		Boy carBoy = (Boy) ctx.getBean("carBoy");
		System.out.println(carBoy);
	}
	
	/**
	 * 测试Bean的生命周期.
	 */
	@Test
	public void testHelloWorld(){
		
		/*初始化容器时，调用顺序：
		 * Constructor...,
		 * Setter...,
		 * MyBeanPostProcessor.postProcessBeforeInitialization() 后置处理器在init前后执行
		 * HelloWord.initHello()
		 * MyBeanPostProcessor.postProcessAfterInitialization()
		 * */
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-bean2.xml");
		
		//不执行初始化等操作，除非作用域设置为prototype
		HelloWord helloWord = (HelloWord) ctx.getBean("helloWord");
		System.out.println(helloWord);
		
		//关闭创建的容器：ClassPathXmlApplicationContext
		ctx.close(); //调用destroyHello()
	}
	
	/**
	 * 测试工厂方式创建Bean实例：静态工厂，实例工厂，Bean工厂
	 */
	@SuppressWarnings("resource")
	@Test
	public void testStaticFactory(){
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Car carFromStaticFactory = (Car) ctx.getBean("carFromStaticFactory");
		System.out.println(carFromStaticFactory);
		Car carFromInstanceFactory = (Car) ctx.getBean("carFromInstanceFactory");
		System.out.println(carFromInstanceFactory);
		Car carFromFactoryBean = (Car) ctx.getBean("carFromFactoryBean");
		System.out.println(carFromFactoryBean);
	}
	
	/**
	 * 测试注解方式创建bean
	 */
	@SuppressWarnings("resource")
	@Test
	public void testAnnotationBean(){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-annotation.xml");
		
		TestComponent testComponent = (TestComponent) ctx.getBean("testComponent");
		System.out.println(testComponent);
		
		UserService userService = (UserService) ctx.getBean("userService");
		System.out.println(userService);
		
		UserRepository userRepository = (UserRepository) ctx.getBean("userRepository");
		System.out.println(userRepository);
		
		UserController userController = (UserController) ctx.getBean("userController");
		System.out.println(userController);
	}
	
	/**
	 * 测试注解方式创建bean：扫描的子节点包含不包含，exclude-filter,include-filter
	 */
	@SuppressWarnings("resource")
	@Test
	public void testAnnotationBeanFilter(){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-annotation.xml");
		
		/*TestComponent testComponent = (TestComponent) ctx.getBean("testComponent");
		System.out.println(testComponent);
		
		UserService userService = (UserService) ctx.getBean("userService");
		System.out.println(userService);*/
		
		UserRepository userRepository = (UserRepository) ctx.getBean("userRepository");
		System.out.println(userRepository);
		
		/*UserController userController = (UserController) ctx.getBean("userController");
		System.out.println(userController);*/
	}
	
	/**
	 * 测试关联注解方式：根据bean的属性或方法的引用，注入另一个bean
	 * 给属性加了注解@AutoWired,
	 * 则userController的属性userService被自动装配，
	 * userService的属性userRepository属性被自动装配。
	 * =============AutoWired可以放在属性、构造器、普通方法上面
	 * AutoWired(required=true) :
	 * 			required默认为true，表示装配的属性必须在IOC容器中存在，没有则报错。
	 * 			修改为false，表示没有也可以，不报错，返回null。
	 */
	@SuppressWarnings("resource")
	@Test
	public void testAnnotationAsso(){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-annotation.xml");
		
		UserController userController = (UserController) ctx.getBean("userController");
		System.out.println(userController);
		userController.execute();
	}
	
}
