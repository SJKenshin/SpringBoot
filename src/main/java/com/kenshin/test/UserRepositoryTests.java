//package com.kenshin.test;
//
//import java.text.DateFormat;
//import java.util.Date;
//
//import javax.persistence.EntityManager;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.kenshin.WebApplication;
//import com.kenshin.dao.UserRepository;
//import com.kenshin.domain.User;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@EnableAutoConfiguration//此注释自动载入应用程序所需的所有Bean
//public class UserRepositoryTests {
//
//    @Autowired
//    private UserRepository userRepository;
//    
//  //  EntityManager manager=new EntityManager();
//
//    @Test
//    public void test() throws Exception {
//        Date date = new Date();
//        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);        
//        String formattedDate = dateFormat.format(date);       
//
//        
//        User u = userRepository.findOne(9);
//        System.out.println("Username : " + u.getUserName());
//        
//        userRepository.save(new User("aa1", "aa@126.com", "aa", "aa123456",formattedDate));
//
//    }
//
//}
