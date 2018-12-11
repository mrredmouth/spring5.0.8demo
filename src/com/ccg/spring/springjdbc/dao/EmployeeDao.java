package com.ccg.spring.springjdbc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ccg.spring.springjdbc.exception.EmployeeException;
import com.ccg.spring.springjdbc.pojo.Employee;

@Repository
public class EmployeeDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Employee get(Integer id){
		String sql = "select id,last_name as lastName,email from employee where id = ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, id);
		return employee;
	}
	
	public Long getAccount(Integer id){
		String sql = "select account from employee where id = ?";
		Long account = jdbcTemplate.queryForObject(sql,Long.class ,id);
		
		return account;
	}
	public Long getAccountByName(String name){
		String sql = "select account from employee where last_name = ?";
		Long account = jdbcTemplate.queryForObject(sql,Long.class ,name);
		
		return account;
	}
	
	public int decreaseAccount(String name,Integer account){
		//减后小于0，则抛出异常
		getAccountByName(name);
		if(getAccountByName(name) - account < 0){
			throw new EmployeeException("余额不足");
		}
		
		String sql = "update employee set account = account - ? where last_name = ?";
		int result = jdbcTemplate.update(sql, account,name);
		
		return result;
	}
	public int addAccount(String name,Integer account){
		//加后超过限制额，则抛出异常
		getAccountByName(name);
		if(getAccountByName(name) + account > 200){
			throw new EmployeeException("账户超过限定额200");
		}
		
		String sql = "update employee set account = account + ? where last_name = ?";
		int result = jdbcTemplate.update(sql, account,name);
		
		return result;
	}
	
	
	
}
