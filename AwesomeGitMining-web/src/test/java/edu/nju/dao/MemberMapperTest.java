package edu.nju.dao;

import edu.nju.model.Member;
import edu.nju.model.StarRepo;
import edu.nju.model.Word;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.validation.constraints.AssertFalse;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void addWordTest() throws Exception{
        java.sql.Date sd;
        java.util.Date ud;

         ud = new java.util.Date();
        sd = new java.sql.Date(ud.getTime());
        Word w=new Word("miku","xml",sd);
        dao.addWord(w);
        System.out.println("2333");


    }

    @Test
    public void getWordTest() throws Exception{
       String name="miku";
        List<String> w=new ArrayList<String>();
        w=dao.getWord(name);
        int length=w.size();
        for(int i=0;i<length;i++) {
            System.out.print(w.get(i)+" ");
        }


    }


    @Test
    public void findWordTest() throws Exception{
        String word="maven";
        List<String> p=new ArrayList<String>();
        p=dao.findWord(word);
        int length=p.size();
        for(int i=0;i<length;i++) {
            System.out.println(p.get(i)+" ");
        }
    }

    @Test
    public void addStarRepoTest() throws Exception{
        java.sql.Date sd;
        java.util.Date ud;

        ud = new java.util.Date();
        sd = new java.sql.Date(ud.getTime());
        StarRepo w=new StarRepo("miku","tcurdt/jdeb",sd);
        dao.addStarRepo(w);
       System.out.println("2333");

    }
    @Test
    public void findStarRepoTest() throws Exception{
        String name="miku";
        List<String> w=new ArrayList<String>();
        w=dao.findStarRepo(name);
        int length=w.size();
        for(int i=0;i<length;i++) {
            System.out.print(w.get(i)+" ");
        }
    }

    @Test
    public void findMemberByRepoTest() throws Exception{
        String reponame="tcurdt/jdeb";
        List<String>username=new ArrayList<String>();
        username=dao.findMemberByRepo(reponame);
        for(int i=0;i<username.size();i++){
            System.out.println(username.get(i));
        }

    }




}