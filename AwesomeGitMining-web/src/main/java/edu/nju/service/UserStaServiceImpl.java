package edu.nju.service;

import edu.nju.dao.UserStaDaoImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
}
