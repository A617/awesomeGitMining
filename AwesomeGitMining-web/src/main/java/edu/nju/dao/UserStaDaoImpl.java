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
    public List<String> countBlog() {
        String[]list={"twitter","github","co","blogspot","linkedin","wordpress","com","about","google","tumblr","ne","hatenablog","koverflow"};
        return null;
    }
}
