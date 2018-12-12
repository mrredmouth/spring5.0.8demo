package com.ccg.spring.springjdbc.exception;

/**
 * 创建异常：
 * 		1、继承RuntimeException
 * 		2、创建两个构造器：Generate Constructors From Superclasse
 * @author Administrator
 *
 */
public class EmployeeException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EmployeeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EmployeeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	
}
