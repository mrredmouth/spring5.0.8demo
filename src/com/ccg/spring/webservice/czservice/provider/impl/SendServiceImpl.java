package com.ccg.spring.webservice.czservice.provider.impl;

import javax.jws.WebService;

import com.ccg.spring.bean.Boy;
import com.ccg.spring.webservice.czservice.provider.SendService;

/**
 * 发布此webservice，在application*.xml中修改bean，让spring容器发布
 * @author Administrator
 *
 */
@WebService(endpointInterface="com.ccg.spring.webservice.czservice.provider.SendService",serviceName="sendService")
public class SendServiceImpl implements SendService{
	public boolean sendOA(String param) {
		System.out.println("-------sendOA---------param:"+param);
		if(param.equals("zhoujian")){
			return true;
		}
		return false;
	}
	public boolean sendOrg(Boy oaPro) {
		System.out.println("-------sendOrg--begin-------");
		return true;
	}
}
