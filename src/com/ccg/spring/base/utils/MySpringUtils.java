package com.ccg.spring.base.utils;

import javax.servlet.ServletContext;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ccg.spring.bean.Car;

public class MySpringUtils {

	private static ApplicationContext context;// spring 上下文

	/**
	 * 得到spring容器中的对象
	 * @param id spring中对象的id
	 * @return Object
	 */
	public static Object getBean(String id) {
		return context.getBean(id);
	}

	/**
	 * 初始化
	 * @param sContext
	 */
	public static void setSC(ServletContext sContext) {
		context = WebApplicationContextUtils.getRequiredWebApplicationContext(sContext);
	}
	
	/**
	 * 获取 spring 上下文
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return context;
	}
	
	public static void setApplicationContext(ApplicationContext ctx){
		if(context == null) context = ctx;
	}
	
	public static void setApplicationContext(String[] paths){
		if(context == null){
			ApplicationContext ctx =new ClassPathXmlApplicationContext(paths);
			context = ctx;
		}
	}
	
	/**
	 * 初始化spring容器
	 */
	public static void init(){
		String[] paths = { "classpath:applicationContext*.xml" };
		MySpringUtils.setApplicationContext(paths);
	}
	public static void init(String[] paths){
		MySpringUtils.setApplicationContext(paths);
	}
	
	public static void main(String[] args){
		String[] paths = { "classpath:spring/applicationContext*.xml" };
//		ApplicationContext ctx =new ClassPathXmlApplicationContext(paths);
//		SpringUtil.setApplicationContext(ctx);
		MySpringUtils.setApplicationContext(paths);
//		CHBBservice comms = (CHBBservice) SpringUtil.getBean("chbbservice");
		
		System.out.println("aaa");
	}
	
	@Test
	public void testSpringUtil(){
		MySpringUtils.init();
		Car carFromStaticFactory = (Car) MySpringUtils.getBean("carFromStaticFactory");
		System.out.println(carFromStaticFactory);
	}

	@Test
	public void testSpringListener(){
		Car carFromStaticFactory = (Car) MySpringUtils.getBean("carFromStaticFactory");
		System.out.println(carFromStaticFactory);
	}
}
