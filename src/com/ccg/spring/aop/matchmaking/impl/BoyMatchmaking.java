package com.ccg.spring.aop.matchmaking.impl;

import com.ccg.spring.aop.matchmaking.IMatchmaking;
import com.ccg.spring.bean.Boy;

import lombok.Data;

@Data
public class BoyMatchmaking implements IMatchmaking{
	
	private String name;
	private Boy boy;
	
	@Override
	public String meeting() {
		System.out.println("BoyMatchmaking.meeting()");
		//int i = 1/0;  测试aop的异常执行顺序 
		return "success";
	}

}
