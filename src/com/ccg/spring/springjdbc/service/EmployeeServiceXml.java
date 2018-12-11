package com.ccg.spring.springjdbc.service;

public interface EmployeeServiceXml {
	
	/**
	 * xml形式配置事务,tx命名空间
	 */
	public void transferAccount(String name1,String name2,Integer account);
	
}
