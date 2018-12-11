package com.ccg.spring.springjdbc.pojo;

import lombok.Data;

@Data
public class Employee {
	private Integer id;
	private String lastName;
	private String email;
	//private Department department;
	
	public Employee(){}
	
	public Employee(String lastName,String email){
		this.lastName = lastName;
		this.email = email;
	}
	
}
