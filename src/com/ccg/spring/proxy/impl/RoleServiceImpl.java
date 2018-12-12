package com.ccg.spring.proxy.impl;

import com.ccg.spring.proxy.RoleService;

public class RoleServiceImpl implements RoleService{

	@Override
	public void printRole(String roleName) {
		System.out.println("RoleServiceImpl.printRole()..." + roleName);
	}

}
