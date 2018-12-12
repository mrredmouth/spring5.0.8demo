package com.ccg.spring.aop.mathculculator;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 有多个切面时，可以使用Order注解，值越小，优先级越高
 */
@Order(2)
@Component
@Aspect
public class ValidateAspect{

	@Before("com.ccg.spring.aop.mathculculator.LoggingAspect.jointPointExpression()")
	public void before(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("[Validate-before] method:" + methodName + ",args:" + args);
	}

	@After("execution(public * com.ccg.spring.aop.mathculculator.MathCulculator.*(int, int))")
	public void after(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("[Validate-after] method:" + methodName);
	}
	
	@AfterThrowing(value = "LoggingAspect.jointPointExpression()",
			throwing = "ex")
	public void afterThrowing(JoinPoint joinPoint,NullPointerException ex) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("[Validate-afterThrowing] method:" + methodName +",Exception:" + ex);
	}

	@AfterReturning(value = "LoggingAspect.jointPointExpression()",
					returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("[Validate-afterReturning] method:" + methodName +",result:" + result);
	}
	
	//@Around("LoggingAspect.jointPointExpression()")
	public Object around(ProceedingJoinPoint pjd) throws Throwable {
		Object result = pjd.proceed();
		return result;
	}
	
}
