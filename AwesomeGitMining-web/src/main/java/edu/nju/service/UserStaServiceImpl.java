package edu.nju.service;

import edu.nju.dao.UserStaDaoImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Dora on 2016/5/13.
 */
@Service("userStaService")
public class UserStaServiceImpl implements IUserStaService{

    @Resource
    UserStaDaoImpl dao;

    @Override
    public List<LinkedHashMap> getCompanyCounts() {
        List<LinkedHashMap> map = dao.countFirst30Companys();
        map.remove("");
        return map;
    }

    @Override
    public List<LinkedHashMap> getTypeCounts() {
        List<LinkedHashMap> map = dao.countTypes();
        map.remove("");
        return map;
    }

    @Override
    public List<LinkedHashMap> getCreateYear() {
        List<LinkedHashMap> map = dao.countCreate_Year();
        map.remove("");
        return map;
    }

    @Override
    public List<LinkedHashMap> countEmail() {
        List<LinkedHashMap> map = dao.countEmail();
        map.remove("");
        return map;
    }

    @Override
    public List<Integer> countBlog() {
        List<Integer> map = dao.countBlog();
        return map;
    }

    /**
     * 统计粉丝数 {0,10,20,30,40,50,60,70,80,90,100,18727};
     * @return
     */
    @Override
    public List<Integer> countFollowers() {
        return dao.countFollowers();
    }

    /**
     * 统计关注数 {0,10,20,30,40,50,60,70,80,90,100,114999}
     * @return
     */
    @Override
    public List<Integer> countFollowings() {
        return dao.countFollowings();
    }
}
