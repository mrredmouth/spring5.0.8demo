package com.ccg.spring.aop.matchmaking;

import org.aspectj.lang.ProceedingJoinPoint;

import com.ccg.spring.aop.Aspect;

/**
 * 切面类，跟代理对象一样，提供通用的服务
 * @author Administrator
 *
 */
public class MatchmakingAspect implements Aspect{

	//前置增强：在业务方法之前调用。一般用于权限的检查
	@Override
	public void before() {
		System.out.println("MatchmakingAspect.before()");
	}
	//后置增强：在业务方法之后调用，无论是否出现异常。一般用于资源的释放，数据库连接池对象等
	@Override
	public void after() {
		System.out.println("MatchmakingAspect.after()");
	}
	
	//异常处理：业务方法出现异常时调用。一般用于异常日志的记录
	@Override
	public void afterThrowing(Throwable th) {
		System.out.println("MatchmakingAspect.afterThrowing()");
	}
	//返回值增强：业务方法有返回值时，会进入该方法。一般用于日志记录
	@Override
	public void afterReturning(Object result) {
		System.out.println("MatchmakingAspect.afterReturning()---"+result);
	}
	//环绕增强，在业务方法执行的期间所执行的方法。一般用于事务处理
	@Override
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("MatchmakingAspect.around()");
		Object result = joinPoint.proceed();
		return result;
	}

}
