package com.ccg.spring.springjdbc.service;

import java.util.List;

public interface PayService {

	public void transferManyAccount(String name1,List<String> name2List,Integer account);
	
}
