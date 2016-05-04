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

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class RepositoryMapperTest {
    @Resource
    private RepositoryMapper dao;

    @Test
    public void selectAll() throws Exception {
        List<Repository> list = dao.selectAll();
        for(Repository u:list)
            System.out.println(u.getFullName());
    }

    @Test
    public void selectByFullName() throws Exception {

    }
    @Test
    public void testForkSort(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pageSize", 2);
        map.put("pageOffset", 20);
        List<Repository> list = dao.selectReposSortedByFork(map);
        for(Repository u:list)
            System.out.println(u.getFullName()+":"+u.getForksCount());
    }
    @Test
    public void testSearch(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pageSize", 3000);
        map.put("pageOffset", 0);
        map.put("fullName","tj");
        List<Repository> list = dao.searchRepository(map);
        for(Repository u:list)
            System.out.println(u.getFullName());
    }
    @Test
    public void testCount(){
        System.out.println(dao.countSearch("tj"));
    }
}