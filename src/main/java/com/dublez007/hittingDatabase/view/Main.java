package com.dublez007.hittingDatabase.view;

import com.dublez007.hittingDatabase.model.User;
import com.dublez007.hittingDatabase.model.UserRole;
import com.dublez007.hittingDatabase.service.UserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;

public class Main{
    public static void main(String[] args) {
        ConfigurableApplicationContext ctxt = new ClassPathXmlApplicationContext("spring-db.xml");
        UserService service = (UserService) ctxt.getBean("userServiceImpl");
        User u = new User(
                null,
                "Nikita Ivanov",
                "test345@gmail.com",
                "password",
                2000,
                true,
                LocalDateTime.now(),
                UserRole.USER);
        System.out.println(service.create(u));
    }
}