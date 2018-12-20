package com.ccg.spring.bean.generic.di;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 父类BaseService自动注入了BaseRepository<T> 的Bean
 * 则子类UserService，也会自动注入UserRepository 的Bean
 * @author Administrator
 *
 * @param <T>
 */
public class BaseService<T> {
	
	@Autowired
	protected BaseRepository<T> baseRepository;
	
	public void add(){
		System.out.println("BaseService.add()");
		System.out.println(baseRepository);
	}
	
}
