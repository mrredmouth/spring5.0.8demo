package com.ccg.spring.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccg.spring.aop.matchmaking.IMatchmaking;
import com.ccg.spring.aop.mathculculator.MathCulculator;

/**
 * InvocationHandler,Proxy.newProxyInstance,
 * 动态代理模拟切面Aspect
 * @author Administrator
 *
 */
public class TestAop {
	
	/**
	 * 测试MatchmakingAspect切面
	 */
	@Test
	@SuppressWarnings("resource")
	public void testMatchmaking(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-aop.xml");
		
		//proxy-target-class="false"，只能创建接口对象，采用jdk动态代理方式
		IMatchmaking boyMatchmaking = (IMatchmaking) ctx.getBean("boyMatchmaking");
		boyMatchmaking.meeting();
		
		//proxy-target-class="true"，设置为true，对实现类对象也有效
		/*BoyMatchmaking boyMatchmaking2 = (BoyMatchmaking) ctx.getBean("boyMatchmaking");
		boyMatchmaking2.meeting();*/
	}
	
	/**
	 * 测试注解方式的AOP
	 */
	@Test
	@SuppressWarnings("resource")
	public void testMathCulculator(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-aop.xml");
		
		MathCulculator mathCulculator = (MathCulculator) ctx.getBean("mathCulculator");
		
		//mathCulculator.add(1, 2);
		mathCulculator.div(12,2);
		
	}
}
