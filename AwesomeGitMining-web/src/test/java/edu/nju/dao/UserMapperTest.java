package edu.nju.dao;

import edu.nju.model.EmailSta;
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
    UserMapper dao;


    @Test
    public void selectAll() throws Exception {
        List<User> list = dao.selectAll();
        for (User u : list)
            System.out.println(u.getLogin());
    }

    @Test
    public void selectByLogin() throws Exception {
        // System.out.println(dao.selectByLogin("jhoblitt").getCollaborationFullname());
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
    public void countFirst30Companys() {
//        List<Map<String, Object>> map = dao.countFirst30Companys();
//        for (Map<String, Object> m : map)
//            System.out.println(m.keySet() + " " + m.values());
    }


    @Test
    public void testCountLanguage() {
        System.out.println(dao.countLanguage("java"));
    }

    @Test
    public void testLanguage() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageSize", 3000);
        map.put("pageOffset", 0);
        map.put("language", "java");
        List<User> list = dao.selectUserByLanguage(map);
        for (User u : list)
            System.out.println(u.getLogin() + u.getLanguages());
    }

    @Test
    public void testCountCompany() {
        System.out.println(dao.countCompany("facebook"));
    }

    @Test
    public void testCompany() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageSize", 3000);
        map.put("pageOffset", 0);
        map.put("company", "facebook");
        List<User> list = dao.selectUserByCompany(map);
        for (User u : list)
            System.out.println(u.getLogin() + u.getCompany());
    }

    @Test
    public void countLan_ComTest() {
        String lan = "java";
        String com = "facebook";
        int pageSize = 3000;
        int pageOffset = 0;
        List<User> list = dao.selectLan_Com(lan, com, pageSize, pageOffset);
        for (User u : list)
            System.out.println(u.getName() + ":");
        System.out.println(list.size());

    }

    @Test
    public void countFanTest() throws Exception {
        int a = dao.countFollow(10, 20);
        System.out.println(a);

    }

    @Test
    public void testUserLocation() {
        List<String> list = dao.getUserLocation();
        for (String str : list) {
            System.out.println(str);
        }
    }
    @Test
    public void testSelectLanguages(){
        List<String> list = dao.selectLanguages();
        for (String str : list) {
            System.out.println(str);
        }
    }
}