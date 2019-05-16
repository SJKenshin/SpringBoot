package com.kenshin.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kenshin.domain.User;



@Repository
public  interface UserRepository<T>  {
	
	   // public User findOne(Long i);
	  
	
	    public int insertUser(User user);

	    public User findUserById(int id) ;
	    
	    public void update(User user);
	    
	    public <T> List<T> findBysql(String sql,Map<String,Object> map);

	
}
