package edu.nju.dao;

import edu.nju.model.Member;
import edu.nju.model.StarRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fw on 2016/5/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)        //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class RepoStaDaoImplTest {

    @Resource
    RepoStaDaoImpl dao;

    @Test
    public void countFirst20LanguagesTest() throws Exception {
        System.out.println(dao.countFirst20Languages());
    }

    @Test
    public void getLanguageAndSizeTest() throws Exception {

        System.out.println(dao.getLanguageAndSize());

    }


    @Test
    public void countCreatedYearTest() throws Exception {
        System.out.println(dao.countCreatedYear());


    }


    @Test
    public void getStaLanguagesTest() throws Exception {
        List<String> list = dao.getStaLanguages();

    }

    @Test
    public void countFirst10LanguagesTest() throws Exception {
        List<String> list = dao.countFirst10Languages();
        for (String u : list)
            System.out.println(u);
    }

    @Test
    public void countForksTest() throws Exception {

        List<Integer> list = dao.countForks();
        for (Integer u : list)
            System.out.println(u);

    }

    @Test
    public void countStarsTest() throws Exception {

        List<Integer> list = dao.countStars();
        for (Integer u : list)
            System.out.println(u);

    }

    @Test
    public void countSubsTest() throws Exception {

        List<Integer> list = dao.countSubscribers();
        for (Integer u : list)
            System.out.println(u);

    }

    @Test
    public void getLanByYearTest() throws Exception {
        Map<String, Object> map = dao.getLanByYear();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("key-->" + entry.getKey());
            System.out.println("value-->" + entry.getValue());
        }

    }

    @Test
    public void eachSizeTest() throws Exception {

        List<Integer> list = dao.eachSize();
        for (Integer u : list)
            System.out.println(u);

    }

    @Test
    public void testverageSize() {
        List<LinkedHashMap> list = dao.countAverageSize_year();
        System.out.println(list);
    }

    @Test
    public void testLanguageTrend() {
        System.out.println(dao.getLanguageUsageByYear("2008"));
    }
}