package edu.nju.dao;

import edu.nju.model.Pager;
import edu.nju.model.SystemContext;
import edu.nju.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dora on 2016/5/3.
 */

@Service("userDao")
public class UserDaoImpl implements IUserDao{
    @Resource
    UserMapper mapper;

    @Override
    public List<User> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public User selectByLogin(String login) {
        return mapper.selectByLogin(login);
    }

    @Override
    public Pager<User> selectAllWithPager() {
        System.out.println("dao start");
        int pageSize = SystemContext.getSize();
        int pageOffset = SystemContext.getOffset();
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("pageSize",pageSize);
        map.put("pageOffset",pageOffset);
        List<User> data = mapper.selectAllWithPager(map);

        Pager<User> pages = new Pager<User>();
        pages.setOffset(pageOffset);
        pages.setSize(pageSize);
        pages.setDatas(data);
        int records = mapper.countAll();
        pages.setTotal(records);

        System.out.println("dao ok");
        return pages;
    }

    @Override
    public int countAll() {
        return mapper.countAll();
    }
}
