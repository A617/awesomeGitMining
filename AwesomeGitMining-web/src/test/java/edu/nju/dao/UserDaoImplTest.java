package edu.nju.dao;

import edu.nju.model.Pager;
import edu.nju.model.Repository;
import edu.nju.model.SystemContext;
import edu.nju.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Dora on 2016/5/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class UserDaoImplTest {

    @Resource
    UserDaoImpl dao;

    @Test
    public void selectAll() throws Exception {

    }

    @Test
    public void selectByLogin() throws Exception {

    }

    @Test
    public void selectAllWithPager() throws Exception {
        SystemContext.setOffset(0);
        SystemContext.setSize(20);
        Pager<User> page = dao.selectAllWithPager();
//        for(User user:page.getDatas()){
//            System.out.println(user.getId()+" "+user.getLogin()+" "+user.getLocation());
//        }
    }
    @Test
    public  void getCollaTest() throws  Exception{
        String name="tobias";
        List<String>list=dao.getCollaRepo(name);
        for (String u : list)
            System.out.println(u);

    }
    @Test
    public  void getContriTest() throws  Exception{
        String name="tobias";
        List<String>list=dao.getContriRepo(name);
        for (String u : list)
            System.out.println(u);

    }
    @Test
    public  void getOwnTest() throws  Exception{
        String name="tobias";
        List<String>list=dao.getOwn(name);
        for (String u : list)
            System.out.println(u);

    }




}