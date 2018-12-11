package com.ccg.spring.bean.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Bean的后置处理器，分别在初始化的前后执行
 * @author Administrator
 */
public class MyBeanPostProcessor implements BeanPostProcessor{
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		
		
		//只对helloWord有效，否则其他的bean都有后置处理器了
		if("helloWord".equals(beanName)){
			System.out.println("MyBeanPostProcessor.postProcessBeforeInitialization():"+beanName);
			//do something...
		}
		
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		
		if("helloWord".equals(beanName)){
			System.out.println("MyBeanPostProcessor.postProcessAfterInitialization():" +beanName);
			//do something...
		}
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
}
