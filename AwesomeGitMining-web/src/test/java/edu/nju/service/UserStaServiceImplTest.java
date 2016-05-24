package edu.nju.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Dora on 2016/5/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class UserStaServiceImplTest {
    @Test
    public void getCompanyCounts() throws Exception {

        System.out.println(user.getCompanyCounts());

    }

    @Autowired
    UserStaServiceImpl user;

}