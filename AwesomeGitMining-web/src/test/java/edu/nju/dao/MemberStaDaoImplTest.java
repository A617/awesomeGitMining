package edu.nju.dao;

import edu.nju.model.Member;
import edu.nju.model.StarRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Dora on 2016/5/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)        //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class MemberStaDaoImplTest {

    @Resource
    MemberStaDaoImpl dao;

    @Test
    public void countFirst20Keywords() throws Exception {
        System.out.println(dao.countFirst20Keywords());

    }


}