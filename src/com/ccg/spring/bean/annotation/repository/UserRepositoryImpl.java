package com.ccg.spring.bean.annotation.repository;

import org.springframework.stereotype.Repository;

@Repository(value="userRepository")  //模拟持久化层
public class UserRepositoryImpl implements UserRepository {

	@Override
	public void save() {
		System.out.println("UserRepositoryImpl.save()");
	}

}
