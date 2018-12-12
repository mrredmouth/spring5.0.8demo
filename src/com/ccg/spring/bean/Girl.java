package com.ccg.spring.bean;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import lombok.Data;

/**
 * 不同参数类型在setter注入方式中的写法，List,Set,Map,Properties,Array,
 * @author Administrator
 *
 */
@Data
public class Girl {
	private String girlName;
	private List<String> hobies;
	private Map<String,Integer> scores;
	private Properties props;
	private Set<String> set;
	private String[] array;
	private Boy boyFriend;
	private List<Boy> boyFriends;
	
	Girl(){};
	Girl(String name,List<String> hobbies,Boy boyFriend){
		setGirlName(name);
		setHobies(hobbies);
		setBoyFriend(boyFriend);
	};
}
