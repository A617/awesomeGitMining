package edu.nju.service;

import edu.nju.dao.UserStaDaoImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Dora on 2016/5/13.
 */
@Service("userStaService")
public class UserStaServiceImpl implements IUserStaService{

    @Resource
    UserStaDaoImpl dao;

    @Override
    public Map<String, Integer> getCompanyCounts() {
        Map<String,Integer> map = dao.countFirst30Companys();
        map.remove("");
        return map;
    }
}
