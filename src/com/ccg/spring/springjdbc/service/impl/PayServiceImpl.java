package com.ccg.spring.springjdbc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ccg.spring.springjdbc.dao.EmployeeDao;
import com.ccg.spring.springjdbc.service.EmployeeService;
import com.ccg.spring.springjdbc.service.PayService;

@Service("payService")
public class PayServiceImpl implements PayService{

	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	EmployeeService employeeService;
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void transferManyAccount(String name1, List<String> name2List, Integer account) {
		for(String name2:name2List){
			employeeService.transferAccountRequiredNew(name1, name2, account);
		}
	}
	

}
