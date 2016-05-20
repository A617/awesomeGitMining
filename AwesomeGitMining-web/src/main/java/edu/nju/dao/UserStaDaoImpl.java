package edu.nju.dao;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Dora on 2016/5/13.
 */

@Service("userStaDao")
public class UserStaDaoImpl implements IUserStaDao {

    @Resource
    UserMapper mapper;

    @Override
    public List<LinkedHashMap> countFirst30Companys() {
        List<LinkedHashMap> list = mapper.countFirst30Companys();

        return list;
    }

    @Override
    public List<LinkedHashMap> countTypes() {
        List<LinkedHashMap> list = mapper.countTypes();
        return list;
    }

    @Override
    public List<LinkedHashMap> countCreate_Year() {
        List<LinkedHashMap> list = mapper.countCreate_Year();
        return list;
    }

    @Override
    public List<LinkedHashMap> countEmail() {
        List<LinkedHashMap> list = mapper.countEmail();
        return list;
    }

    @Override
    public List<Integer> countBlog() {
        String[]list={"twitter","github","blogspot","linkedin","wordpress","about","google","tumblr","hatenablog","koverflow"};
        List<Integer> result=new ArrayList<Integer>();
        for (String u:list) {
            result.add(mapper.countBlog(u));
        }
        return result;
    }

    @Override
    public List<Integer> countFollowers() {
        List<Integer>list=mapper.countFollowers();
        return list;
    }

    @Override
    public List<Integer> countFollowings() {
        List<Integer>list=mapper.countFollowings();
        return list;
    }
}
