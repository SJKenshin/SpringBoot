package com.kenshin.filter;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {
	
	static Map<String,Logger> map=new HashMap<String,Logger>();
	
	static public Logger getLogger(Class type) {
		if(map.get(type.getName())==null) {
			Logger log=LoggerFactory.getLogger(type);
			if(log!=null) {
				map.put(type.getName(), log);
			}
			
		}
		return  map.get(type.getName());
	}

}
