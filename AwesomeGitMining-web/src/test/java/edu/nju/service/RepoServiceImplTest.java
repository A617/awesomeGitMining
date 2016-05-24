package edu.nju.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.nju.model.Pager;
import edu.nju.model.Repository;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by Dora on 2016/4/30.
 */

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class RepoServiceImplTest {
    @Test
    public void getRelatedRepoViaSubscribers() throws Exception {
        Map<String,Object> map = repoService.getRelatedRepoViaSubscribers("adam12/maxmind");
//        ObjectMapper mapper = new ObjectMapper();
//        StringWriter writer = new StringWriter();
//        JsonGenerator gen = new JsonFactory().createJsonGenerator(writer);
//        mapper.writeValue(gen,map);
//        gen.close();
//        String result = writer.toString();
//        writer.close();
//        System.out.println(result);
    }

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

    @Test
    public void getReposByLan_Key_Year() throws Exception {
        System.out.println(repoService.getReposByLan_Key_Year("","","","Star").getDatas());
    }

}





