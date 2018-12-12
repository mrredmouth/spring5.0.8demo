package com.ccg.spring.springjdbc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccg.spring.springjdbc.dao.EmployeeDao;
import com.ccg.spring.springjdbc.exception.EmployeeException;
import com.ccg.spring.springjdbc.service.EmployeeServiceXml;


@Service("employeeServiceXml")
public class EmployeeServiceXmlImpl implements EmployeeServiceXml {
	@Autowired
	private EmployeeDao employeeDao;
	
	/*@Transactional(propagation=Propagation.REQUIRED,
					isolation=Isolation.READ_COMMITTED,
					timeout=3)*/
	/**
	 * 不通过@Transactional注解方式添加事务，通过xml配置方式
	 */
	public void transferAccount(String name1,String name2,Integer account){
		try {
			employeeDao.decreaseAccount(name1, account);
			employeeDao.addAccount(name2, account);
			Thread.sleep(1000);	//睡3秒，测试timeout
		} catch (EmployeeException e) {		
			throw e;
		} catch (InterruptedException e) {
		} 
	}
}
