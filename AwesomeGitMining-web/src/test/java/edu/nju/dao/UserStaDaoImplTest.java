package edu.nju.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Dora on 2016/5/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)        //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class UserStaDaoImplTest {

    @Resource
    UserStaDaoImpl dao;

    @Test
    public void countFirst30Companys() throws Exception {
        System.out.println(dao.countFirst30Companys());
    }
    @Test
    public void countTypes() throws Exception {
        System.out.println(dao.countTypes());


    }

    @Test
    public void countCreate_Year() throws Exception {
        System.out.println(dao.countCreate_Year());


    }
}