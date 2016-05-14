package edu.nju.service;

import edu.nju.model.Pager;
import edu.nju.model.Repository;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Dora on 2016/4/30.
 */

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class RepoServiceImplTest {
    private static Logger logger = Logger.getLogger(RepoServiceImplTest.class);
    //	private ApplicationContext ac = null;
    @Resource
    private IRepoService repoService = null;



    @Test
    public void getAllRepos(){
//       Pager<Repository> list = repoService.getAllRepos();
//        for (Repository repo:list.getDatas()){
//            System.out.println(repo.getFullName());
//        }

    }


    @Test
    public void selectByFullName(){
        Repository repo = repoService.getRepoByFullname("jsbin/jsbin");
        //System.out.println(repo.getFullName()+" "+repo.getCloneUrl());
    }

}





