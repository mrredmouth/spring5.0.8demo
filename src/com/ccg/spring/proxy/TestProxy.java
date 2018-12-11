package com.ccg.spring.proxy;

import org.junit.Test;

import com.ccg.spring.aop.Aspect;
import com.ccg.spring.proxy.impl.RoleServiceImpl;

public class TestProxy {
	
	/**
	 * 测试代理对象，赋予被代理对象before、after等方法。模拟AOP：
	 * before -> 反射原有方法roleService -> after -> 
	 * 如果有异常：afterThrowing;如果没有有异常：afterReturning。
	 */
	@Test
	public void tsetProxy(){

		RoleService roleService = new RoleServiceImpl();
		Aspect aspect = new RoleAspect();
		//接口代理对象，getBean自定义实现
		RoleService roleServiceProxy = ProxyBeanFactory.getBean(roleService, aspect);
		
		roleServiceProxy.printRole("roleServiceProxy");
	}
}
