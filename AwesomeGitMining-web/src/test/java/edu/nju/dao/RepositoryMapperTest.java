package edu.nju.dao;

import edu.nju.model.Pager;
import edu.nju.model.Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Dora on 2016/5/2.
 */

@RunWith(SpringJUnit4ClassRunner.class)        //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class RepositoryMapperTest {
    @Resource
    private RepositoryMapper dao;

    @Test
    public void selectAll() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageSize", 300);
        map.put("pageOffset", 0);
        List<Repository> list = dao.selectAllPaged(map);
        for (Repository u : list)
            System.out.println(u.getFullName() + ":" + u.getDescription());
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

    @Test
    public void testLan_Key_Year(){
        String lan="Ruby";
        String key="Ruby";
        String year="2008";
            System.out.println(dao.countLan_Key_Year(lan,key,year));
    }

    @Test
    public void selectReposByLan_Key_Year(){
        String lan="Ruby";
        String key="Ruby";
        String year="2008";
        int pageSize=3000;
        int pageOffset=0;
        List<Repository> list = dao.selectReposByLan_Key_Year(lan,key,year,pageSize,pageOffset);
//        for (Repository u : list)
//            System.out.println(u.getFullName() + ":" + u.getDescription());
        System.out.println(list.size());
    }


}