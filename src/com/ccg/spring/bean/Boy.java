package com.ccg.spring.bean;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Boy {
	private String boyName;
	private List<String> hobies;
	private Map<String,Integer> scores;
	
	//测试SpEL语言
	private Car car;		//引用Girl的boyFriend属性
	private String wantCarName;		//引用想要的car的品牌属性
	private String carPriceLevel;	//引用car的price属性,如果>30w,金领;否则白领。
	
	Boy(){};
	Boy(String name){
		setBoyName(name);
	};
}
