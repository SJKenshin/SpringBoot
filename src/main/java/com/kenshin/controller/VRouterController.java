package com.kenshin.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kenshin.dao.UserRepository;
import com.kenshin.domain.User;
import com.kenshin.query.QueryUser;
import com.kenshin.service.ServiceOne;
/**
 * 
 * @author Administrator
 *  路由层
 *
 *
 */

@RestController
public class VRouterController {
	
	@Autowired
	
	private ServiceOne serviceOne;
	static Logger log=LoggerFactory.getLogger(VRouterController.class);
	
	/**
	 * @return
	 */
	@GetMapping("/getUserById/{id}")
	@ResponseBody
	public User getUser(@PathVariable   Integer id) {
		log.debug("Coming getUser() method at:"+new Date());
		User user=serviceOne.findUserById(id);
		return user==null?new User():user;
	}
	
	@GetMapping("/insertUser")
	@ResponseBody
	public int  insertUser(User user) {
		log.debug("Coming getUser() method at:"+new Date());
		int ii=serviceOne.insertUser(user);
		return ii;
	}
	
	@GetMapping("/updateUser")
	@ResponseBody
	public int  updateUser(User user) {
		log.debug("Coming getUser() method at:"+new Date());
		serviceOne.update(user);
		return 1;
	}
	
	@GetMapping("/findBySql")
	@ResponseBody
	public List<User>  findBySql(QueryUser qs) {
		log.debug("Coming getUser() method at:"+new Date());
		List<User> list=serviceOne.findBysql(qs);
		return list;
	}
	
}
