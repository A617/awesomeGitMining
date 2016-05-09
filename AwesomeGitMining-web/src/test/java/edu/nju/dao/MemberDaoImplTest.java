package edu.nju.dao;

import edu.nju.model.Pager;
import edu.nju.model.Recommender;
import edu.nju.model.SystemContext;
import edu.nju.model.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dora on 2016/5/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class MemberDaoImplTest {

    @Resource
    IMemberDaoImpl dao;

    @Test
    public void searchMemberTest() throws Exception {
    String name="abc";
        Member m=new Member();
        m.setUsername(name);
        m.setPassword("123");
        m.setMember_email("123");
        String re=dao.searchMember(m);
        System.out.print("测试测试测试"+re);

    }

    @Test
    public void addMemberTest() throws Exception {
    Member m=new Member();
        m.setUsername("miku");
        m.setPassword("123456");
        m.setMember_email("ttttt");
        String re=dao.addMember(m);
        System.out.print("测试2："+re);
    }
    @Test
    public void getRecommendBySearchedTest() throws Exception{
        String username="miku";

        List<Recommender> recommender =dao.getRecommendBySearched(username);
        for(int i=0;i<recommender.size();i++){
            System.out.println(recommender.get(i).getRepository()+"   "+recommender.get(i).getKeyword());
        }

    }


}