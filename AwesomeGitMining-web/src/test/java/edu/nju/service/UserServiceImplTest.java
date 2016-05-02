package edu.nju.service;

import edu.nju.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Dora on 2016/5/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class UserServiceImplTest {
    @Resource
    IUserService userService;

    @Test
    public void getAllUsers() throws Exception {
        List<User> list = userService.getAllUsers();
        for(User u:list)
            System.out.println(u.getLogin());
    }

    @Test
    public void getUserByLogin() throws Exception {
        System.out.println(userService.getUserByLogin("ml8mr").getAvatarUrl());
    }

}