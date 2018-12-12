package com.ccg.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public interface Aspect {
	public void before();
	
	public void after();
	
	public void afterThrowing(Throwable th);
	
	public void afterReturning(Object result);
	
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable;
}
