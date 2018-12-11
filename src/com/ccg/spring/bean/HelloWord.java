package com.ccg.spring.bean;

public class HelloWord {
	private String name;
	
	public HelloWord(){
		System.out.println("HelloWord's Constructor...HelloWord()");
	}
	
	/*setter方式创建bean，只要有set方法即可。
	 * <property name="name2"，而不是name
	 */	
	public void setName2(String name){
		System.out.println("HelloWord's Setter...setName2()");
		this.name = name;
	}
	
	public void initHello(){
		System.out.println("HelloWord.initHello()");
	}
	public void destroyHello(){
		System.out.println("HelloWord.destroyHello()");
	}
	
	
	
	public void hello(){
		System.out.println("hello: "+ name);
	}
	
	
}
