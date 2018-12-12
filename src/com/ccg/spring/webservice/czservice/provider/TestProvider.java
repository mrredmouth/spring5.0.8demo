package com.ccg.spring.webservice.czservice.provider;

import org.apache.cxf.jaxws.spring.NamespaceHandler.SpringServerFactoryBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccg.spring.webservice.czservice.provider.impl.SendServiceImpl;

public class TestProvider {

	@Test
	public void tsetCreateBean(){
		
		//初始化容器创建bean：无参构造器->setter方法
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-webservice.xml");
		
		SpringServerFactoryBean sendService = (SpringServerFactoryBean) ctx.getBean("sendService");
		System.out.println("sendService: "+sendService);
		SendServiceImpl sendServiceBean = (SendServiceImpl) ctx.getBean("sendServiceBean");
		System.out.println("sendServiceBean: "+sendServiceBean);
	}
}
