package com.ccg.spring.bean.annotation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ccg.spring.bean.annotation.service.UserService;

@Controller //模拟表现层
public class UserController {
	
	@Autowired //会自动装配此属性,容器中有userService的bean，则自动装配
	private UserService userService;
	
	public void execute(){
		userService.add();
		System.out.println("UserController.execute()");
	}
}
