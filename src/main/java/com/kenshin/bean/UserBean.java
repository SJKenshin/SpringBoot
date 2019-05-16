package com.kenshin.bean;


import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kenshin.dao.UserRepository;
import com.kenshin.domain.User;
import com.kenshin.filter.LoggerUtils;
/**
 * 
 * @author Administrator
 *  数据链路层
 *
 */
@Service
public class UserBean implements UserRepository{	
	
	@PersistenceContext
	 EntityManager em;	
	
	
	
	@Transactional
	public int  insertUser(User user) {
		int i=0;
		try {
			em.persist(user);
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			LoggerUtils.getLogger(UserBean.class).debug(e.getMessage());
		}
		return i;
	}

	@Override
	public User findUserById(int id) {
		User user = null;
		try {
			user=(User)em.find(User.class, id);
		}catch (Exception e) {
			// TODO: handle exception
			LoggerUtils.getLogger(UserBean.class).debug(e.getMessage());
		}
		
		return user;
	}
    //@Transactional 是必须的***************
	@Transactional
	@Override
	public void update(User user) {
		try {
			   em.merge(user);		
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LoggerUtils.getLogger(UserBean.class).debug(e.getMessage());
		}
    
	}

	@Transactional
	@Override
	public List findBysql(String sql, Map map) {
		List list = null;
		try {
	        System.out.println(sql+"--------sql语句-------------");
	        javax.persistence.Query query=em.createQuery(sql);
	        if(map!=null) {
	        	map.forEach((key,value)->{
	        		if(sql.contains(key.toString())) {
	        			query.setParameter(key.toString(),value);
	        		}	        		
	        	});
	        }	        
	         list= query.getResultList();
	        em.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		return list;
	}


}
