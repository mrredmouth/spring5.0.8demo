package com.ccg.spring.bean.annotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccg.spring.bean.annotation.repository.UserRepository;

@Service  //模拟业务层
public class UserService {
	
	/*@Autowired 	//指定装配的属性是一个接口如果存在多个实现类，则会通过名称去匹配。如果匹配不到则报错
	@Qualifier("userRepository")	//用Qualifier指定实现类的bean名去装配
*/	private UserRepository userRepository;
	
	//==============Autowired,Qualifier分别放在属性上面，入参方法上面。均可====================

	/**
	 * Autowired 也可以加到含有入参的方法上面，自动装配入参类型的bean
	 * Qualifier 可以加到入参的参数前面
	 * @param userRepository
	 */
	@Autowired
	public void setUserRepository(@Qualifier("userRepository") UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	public void add(){
		userRepository.save();
		System.out.println("UserService.add()");
	}
}
