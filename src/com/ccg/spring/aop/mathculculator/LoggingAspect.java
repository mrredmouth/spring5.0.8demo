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
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 声明为切面类：
 * 		1、讲该类放到IOC容器中@Component
 * 		2、声明为一个切面@Aspect
 * 	有多个切面时，可以使用@Order注解，值越小，优先级越高
 */
@Order(1)
@Component
@Aspect
public class LoggingAspect{

	/**
	 * 定义切点表达式，则其余的切面通知都可以引用这个表达式，简化代码。一般此方法无方法体。
	 * 本类里面直接jointPointExpression()方式引用。
	 * 其他类里面用类全名引用：com.ccg.spring.aop.mathculculator.LoggingAspect.jointPointExpression()
	 */
	@Pointcut("execution(public * com.ccg.spring.aop.mathculculator.MathCulculator.*(int, int))")
	public void jointPointExpression(){}
	
	
	/**
	 * 前置通知。加入了切点JoinPoint：可以访问到方法的细节，如方法签名，参数等。
	 * 	execution(public * com.ccg.spring...  :	表示public修饰符，任意返回值
	 * 	execution( * com.ccg.spring... : 表示任意修饰符，任意返回值
	 * @param joinPoint
	 */
	@Before("jointPointExpression()")
	public void before(JoinPoint joinPoint) {
		//从切点获取执行的方法名
		String methodName = joinPoint.getSignature().getName();
		//从切点获取执行的方法的参数列表
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("[loggiong-before] method:" + methodName + ",args:" + args);
	}

	//后置通知
	@After("jointPointExpression()")
	public void after(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("[loggiong-after] method:" + methodName);
	}
	
	/**
	 * 异常通知：可以指定特定的异常来执行afterThrowing。
	 * 如本来catch到了ArithmeticException异常，这里写NullPointerException异常，
	 * 则不会执行afterThrowing，也不会执行afterReturning。
	 * @param joinPoint
	 * @param ex
	 */
	@AfterThrowing(value = "jointPointExpression()",
			throwing = "ex")
	public void afterThrowing(JoinPoint joinPoint,NullPointerException ex) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("[loggiong-afterThrowing] method:" + methodName +",Exception:" + ex);
	}

	//返回值增强通知
	@AfterReturning(value = "jointPointExpression()",
					returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("[loggiong-afterReturning] method:" + methodName +",result:" + result);
	}
	
	/**
	 * 环绕通知:必须携带ProceedingJoinPoint类型的参数，且必须有返回值，即为目标方法的返回值。
	 * 环绕通知类似于动态代理的全过程，ProceedingJoinPoint参数可以决定是否执行目标方法。
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	//@Around("jointPointExpression()")
	public Object around(ProceedingJoinPoint pjd) throws Throwable {
		Object result = null;
		String methodName = pjd.getSignature().getName();
		try {
			//Before前置
			System.out.println("around-Before, method:" + methodName);
			//执行目标方法
			result = pjd.proceed();
			//AfterReturning返回
			System.out.println("around-AfterReturning, method:"+methodName+", result:" + result);
		} catch (Exception e) {
			//AfterThrowing异常
			System.out.println("around-AfterThrowing, method:"+methodName+", Exception:"+ e);
			throw new RuntimeException(e);
		}
		//After后置
		System.out.println("around-After, method:"+methodName);
		
		return result;
	}
	
}
