package edu.nju.dao;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dora on 2016/5/13.
 */

@Service("userStaDao")
public class UserStaDaoImpl implements IUserStaDao {

    @Resource
    UserMapper mapper;

    @Override
    public Map<String, Integer> countFirst30Companys() {
        List<Map<String,Object>> list = mapper.countFirst30Companys();
        Map<String,Integer> result = new HashMap<>();
        for(Map<String,Object> m:list) {
            result.put((String)m.get("company"),Integer.valueOf(m.get("c").toString()));
        }
        return result;
    }
}
