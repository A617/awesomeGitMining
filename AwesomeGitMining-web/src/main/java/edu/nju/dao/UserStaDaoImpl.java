package edu.nju.dao;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
