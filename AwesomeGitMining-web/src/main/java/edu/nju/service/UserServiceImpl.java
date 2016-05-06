package edu.nju.service;

import edu.nju.dao.IUserDao;
import edu.nju.dao.UserDaoImpl;
import edu.nju.dao.UserMapper;
import edu.nju.model.Pager;
import edu.nju.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Dora on 2016/5/2.
 */
@Service("userService")
public class UserServiceImpl implements IUserService{

    @Resource
    private UserDaoImpl userDao;

    @Override
    public List<User> getAllUsers() {

        Pager<User> page = userDao.selectAllWithPager();
        return page.getDatas();
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.selectByLogin(login);
    }

    @Override
    public int getUserTotal() {
        return userDao.countAll();
    }

    @Override
    public Pager<User> searchUser(String name) {
        return userDao.searchUser(name);
    }


}
