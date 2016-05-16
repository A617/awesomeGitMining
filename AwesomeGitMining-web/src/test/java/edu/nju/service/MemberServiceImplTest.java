package edu.nju.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by tj on 2016/5/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class MemberServiceImplTest {
    @Resource
    private IMemberService service;

    @Test
    public void testSearchTag(){
        System.out.println(service.getRecommendTag("miku","davidB/scala-maven-plugin"));
    }
}
