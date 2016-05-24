package edu.nju.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void countEmailTest() throws Exception {
        System.out.println(dao.countEmail());

    }

    @Test
    public void countBlogTest() throws Exception {
        List<Integer> list=dao.countBlog();
        for (Integer u:list) {
            System.out.println(u);
        }
    }
    @Test
    public void countFollowingTest() throws Exception {
        List<Integer> list = dao.countFollowings();
        for (Integer u : list) {
            System.out.println(u);
        }
    }
        @Test
        public  void countFollowersTest() throws  Exception{
            List<Integer>list=dao.countFollowers();
            for (Integer u : list)
                System.out.println(u);

    }
    @Test
    public  void countFollowingsTest() throws  Exception{
        List<Integer>list=dao.countFollowings();
        for (Integer u : list)
            System.out.println(u);

    }

    @Test
    public void testLocationDistribute(){
        List<LinkedHashMap> map = dao.getUserLocationDistribute();
        System.out.println(map.size());
    }
    @Test
    public void testLanguageRelation(){
        int[][] test = dao.getLanguageRelation();
        for (int i = 0; i < test[0].length; i++) {
            System.out.println();
            for (int j = 0; j < test[0].length; j++) {
                System.out.print(test[i][j]+" ");
            }
        }
    }
}