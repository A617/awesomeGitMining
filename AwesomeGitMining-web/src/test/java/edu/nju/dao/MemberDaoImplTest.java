package edu.nju.dao;

import edu.nju.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dora on 2016/5/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)        //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class MemberDaoImplTest {

    @Resource
    MemberDaoImpl dao;

    @Test
    public void searchMemberTest() throws Exception {
        String name = "abc";
        Member m = new Member();
        m.setUsername(name);
        m.setPassword("123");
        m.setMember_email("123");
        String re = dao.searchMember(m);
        System.out.print("测试测试测试" + re);

    }

    @Test
    public void addMemberTest() throws Exception {
        Member m = new Member();
        m.setUsername("Sa");
        m.setPassword("123456");
        m.setMember_email("llllll");
        String re = dao.addMember(m);
        System.out.print("测试2：" + re);
    }

    @Test
    public void getRecommendBySearchedTest() throws Exception {
//        String username="miku";
//
//        List<Recommender> recommender =dao.getRecommendBySearched(username);
//        for(int i=0;i<recommender.size();i++){
//            System.out.println(recommender.get(i).getRepository()+"   "+recommender.get(i).getKeyword());
//        }

    }

    @Test
    public void findStarRepoTest() throws Exception {
        String username = "miku";

        List<String> share = dao.getStaredRepos(username);
        for (int i = 0; i < share.size(); i++) {
            System.out.println(share.get(i));
        }

    }

    @Test
    public void addShareRepoTest() throws Exception {
        java.sql.Date sd;
        java.util.Date ud;

        ud = new java.util.Date();
        sd = new java.sql.Date(ud.getTime());
        StarRepo w = new StarRepo("Lukawa", "tcurdt/jdeb", sd);
        StarRepo x = new StarRepo("Lukawa", "jdoklovic/maven-cli-plugin", sd);
        StarRepo y = new StarRepo("Lukawa", "davidB/yuicompressor-maven-plugin", sd);
        StarRepo z = new StarRepo("Sa", "tcurdt/jdeb", sd);
        StarRepo r = new StarRepo("Sa", "jlong/radius", sd);
        dao.addShareRepo(w);
        dao.addShareRepo(x);
        dao.addShareRepo(y);
        dao.addShareRepo(z);
        dao.addShareRepo(r);
    }


    @Test
    public void getRecommendByOtherTest() throws Exception {
        String uaername = "miku";
        List<String> list = dao.getRecommendByOther(uaername);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }


    }

    @Test
    public void testGetSearchTag() {
        System.out.println(dao.getSearchTag("miku", "davidB/scala-maven-plugin"));
    }

    @Test
    public void testUnStar() {
        System.out.println(dao.getStaredRepos("miku").size());
        dao.unStarRepo("miku","mojombo/grit");
        System.out.println(dao.getStaredRepos("miku").size());
    }
}