package com.ccg.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.ccg.spring.aop.Aspect;

public class ProxyBeanUtil implements InvocationHandler{

	//被代理对象
	private Object obj;
	private Aspect aspect;
	private Throwable fillInStackTrace;
	/**
	 * 获取动态代理对象，拥有T的方法和拦截器的方法。
	 * 静态，用类直接调用，并不会创建ProxyBeanUtil对象，在getBean方法中创建本类对象，则会进入invoke方法
	 * @param obj
	 * @param interceptor
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T>T getBean(T t, Aspect aspect) {
		
		//代理对象由传进来的类加载器进行加载
		ClassLoader classLoader = t.getClass().getClassLoader();
		
		//代理对象的类型，即其中有哪些方法
		Class<?>[] interfaces = t.getClass().getInterfaces();
		
		//创建当前类作为代理方法，被代理对象T执行方法的时候，会先进入当前类的invoke方法
		ProxyBeanUtil _this = new ProxyBeanUtil();
		_this.obj = t;
		_this.aspect = aspect;
		
		return (T) Proxy.newProxyInstance(classLoader, interfaces, _this);
	}

	//代理方法
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object retObj = null;
		boolean exceptionFlag = false;
		/*proxy.toString(); !!! 不能调用代理对象的方法，则会又进入此invoke方法，死循环。*/
		
		aspect.before();	//模拟切面的before
		try {
			retObj = method.invoke(obj, args);	//反射被代理对象的原有方法
		} catch (Exception e) {
			fillInStackTrace = e.fillInStackTrace();
			exceptionFlag = true;
		} finally{
			aspect.after();	//模拟切面的after
		}
		if(exceptionFlag){
			aspect.afterThrowing(fillInStackTrace);	//模拟切面的afterThrowing
		}else{
			aspect.afterReturning(retObj);	//模拟切面的afterReturning
		}
		return null;
	}

}
