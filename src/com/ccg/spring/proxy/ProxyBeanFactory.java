package com.ccg.spring.proxy;

import com.ccg.spring.aop.Aspect;

public class ProxyBeanFactory {
	/**
	 * 自定义生成代理对象，拥有T和Aspect两个对象的功能
	 */
	public static <T> T getBean(T obj,Aspect aspect){
		return (T)ProxyBeanUtil.getBean(obj,aspect);
	}
}
