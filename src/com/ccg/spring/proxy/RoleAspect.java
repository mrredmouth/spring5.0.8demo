package com.ccg.spring.proxy;

import org.aspectj.lang.ProceedingJoinPoint;

import com.ccg.spring.aop.Aspect;

public class RoleAspect implements Aspect{

	@Override
	public void before() {
		System.out.println("RoleAspect.before()");
	}

	@Override
	public void after() {
		System.out.println("RoleAspect.after()");
	}

	@Override
	public void afterThrowing(Throwable th) {
		System.out.println("RoleAspect.afterThrowing()");
		try {
			throw new Exception(th);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterReturning(Object result) {
		System.out.println("RoleAspect.afterReturn()");
	}

	@Override
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("RoleAspect.around()");
		Object result = joinPoint.proceed();
		return result;
	}

}
