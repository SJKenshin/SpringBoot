package com.kenshin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenshin.dao.UserRepository;
import com.kenshin.domain.User;
import com.kenshin.query.QueryUser;


/**
 * 
 * @author Administrator
 * 
 * 业务逻辑层
 */
@Service
public class ServiceOne {

    @Autowired	
	private UserRepository userService;
    
    public int insertUser(User user) {
    	//业务
    	int ii=userService.insertUser(user);
		return ii;
    }

    public User findUserById(int id) {
    	User user=userService.findUserById(id);
		return user==null?new User():user;
    }
    
    public void update(User user) {
    	 userService.update(user);
    }
    
    public List<User> findBysql(QueryUser qs){
    	List<User> list=null;
    	if(qs.getSql()!=null&&!qs.getSql().isEmpty()) {
    		Map<String,Object> map=new HashMap<String,Object>();
    		map.put("user_name",qs.getUser_name()==null?"":qs.getUser_name());
    		map.put("pass_word",qs.getPass_word()==null?"":qs.getPass_word());
    		map.put("email",qs.getEmail()==null?"":qs.getEmail());   
    		map.put("nick_name",qs.getNick_name()==null?"":qs.getNick_name());  
    		map.put("reg_time",qs.getReg_time()==null?"":qs.getReg_time());     		
    		list=userService.findBysql(qs.getSql(),map);
    	}    
		return list;
    }
    
}
