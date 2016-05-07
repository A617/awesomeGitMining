package edu.nju.dao;

import edu.nju.model.Member;
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

    @Resource
    MemberMapper dao;


    @Test
    public void searchMemberTest() throws Exception {
        String name="miku";
        Member list = dao.searchMember(name);
        if(list!=null) {
            System.out.println("This:" + list.getUserid()+list.getUsername()+list.getPassword()+list.getMember_email());
        }
    }

    @Test
    public void addMemberTest() throws Exception {
//        Member m=new Member();
//        m.setUsername("sa");
//        m.setPassword("123456");
//        m.setMember_email("mikumiku");
//        dao.addMember(m);
//        System.out.print(m.getUsername());

    }


}