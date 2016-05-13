package edu.nju.dao;

import edu.nju.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dora on 2016/5/2.
 */

@RunWith(SpringJUnit4ClassRunner.class)        //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class UserMapperTest {

    @Resource
    UserMapper dao = null;


    @Test
    public void selectAll() throws Exception {
        List<User> list = dao.selectAll();
        for(User u:list)
            System.out.println(u.getLogin());
    }

    @Test
    public void selectByLogin() throws Exception {
        System.out.println(dao.selectByLogin("jhoblitt").getCollaborationFullname());
    }

    @Test
    public void testSearch() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageSize", 3000);
        map.put("pageOffset", 0);
        map.put("name", "tj");
        List<User> list = dao.searchUser(map);
        for (User u : list)
            System.out.println(u.getLogin());
    }

    @Test
    public void countFirst30Companys(){
        List<Map<String,Integer>> map = dao.countFirst30Companys();
        for(Map<String,Integer> m:map)
            System.out.println(m);
    }
}