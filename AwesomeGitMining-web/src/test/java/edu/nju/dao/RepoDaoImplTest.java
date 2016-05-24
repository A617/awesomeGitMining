package edu.nju.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Dora on 2016/5/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)        //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class RepoDaoImplTest {
    @Test
    public void getSubscribionsOfUser() throws Exception {
       // System.out.println(dao.getSubscribionsOfUser("technoweenie"));
    }

    @Resource
    RepoDaoImpl dao;

    @Test
    public void getReposByLan_Key_Year() throws Exception {
        System.out.println(dao.getReposByLan_Key_Year("","","","stargazers_count").getDatas());
    }

    @Test
    public void test(){
        System.out.println(dao.getContributors("zhangzibin/char-rnn-chinese"));
    }

}