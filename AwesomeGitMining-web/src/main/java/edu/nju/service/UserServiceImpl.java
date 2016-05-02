package edu.nju.service;

import edu.nju.dao.UserMapper;
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
    private UserMapper userDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.selectAll();
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.selectByLogin(login);
    }
}
