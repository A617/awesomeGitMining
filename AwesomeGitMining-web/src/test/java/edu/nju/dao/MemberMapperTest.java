package edu.nju.dao;

import edu.nju.model.Repository;
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
public class MemberMapperTest {

    private MemberMapper dao;

    @Test
    public void searchMemberTest() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", "333");
        map.put("password", "66666");
        map.put("member_email","ttt@163.com");
        List<Member> list = dao.addMember(map);
        for (Member u : list)
            System.out.println("111");
    }

    @Test
    public void selectByFullName() throws Exception {

    }

    @Test
    public void testForkSort() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageSize", 2);
        map.put("pageOffset", 20);
        List<Repository> list = dao.selectReposSortedByFork(map);
        for (Repository u : list)
            System.out.println(u.getFullName() + ":" + u.getForksCount());
    }

    @Test
    public void testSearch() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageSize", 3000);
        map.put("pageOffset", 0);
        map.put("fullName", "tj");
        List<Repository> list = dao.searchRepository(map);
        for (Repository u : list)
            System.out.println(u.getFullName() + ":" + u.getCreatedAt());
        System.out.println(list.get(0).getCreatedAt().getTime());
    }

    @Test
    public void testCount() {
        System.out.println(dao.countSearch("tj"));
    }
    @Test
    public void testYear(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageSize", 3000);
        map.put("pageOffset", 0);
        map.put("year",2010);
        List<Repository> list = dao.selectReposByYear(map);
        for (Repository u : list)
            System.out.println(u.getFullName() + ":" + u.getCreatedAt());
    }
    @Test
    public void testLanguage(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageSize", 3000);
        map.put("pageOffset", 0);
        map.put("language","java");
        List<Repository> list = dao.selectReposByLanguage(map);
        for (Repository u : list)
            System.out.println(u.getFullName() + ":" + u.getLanguage());
    }
    @Test
    public void testKey(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageSize", 3000);
        map.put("pageOffset", 0);
        map.put("keyword","java");
        List<Repository> list = dao.selectReposByKey(map);
        for (Repository u : list)
            System.out.println(u.getFullName() + ":" + u.getDescription());
    }
}