package com.ccg.spring.springjdbc.service;

import java.util.List;

public interface EmployeeService {
	
	/**
	 * name1 转账给 name2 的Employee账户，金额为account
	 * @param name1
	 * @param name2
	 * @param account
	 */
	public void transferAccount(String name1,String name2,Integer account);
	
	public void transferAccountRequiredNew(String name1,String name2,Integer account);

	public void transferManyAccount(String name1,List<String> name2List,Integer account);
	
	
}
