package edu.nju.service;

import edu.nju.dao.IUserDao;
import edu.nju.dao.UserDaoImpl;
import edu.nju.dao.UserMapper;
import edu.nju.model.Day;
import edu.nju.model.Pager;
import edu.nju.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Dora on 2016/5/2.
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private UserDaoImpl userDao;

    @Override
    public Pager<User> getAllUsers() {

        return userDao.selectAllWithPager();
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.selectByLogin(login);
    }

//    @Override
//    public int getUserTotal() {
//        return userDao.countAll();
//    }

    @Override
    public Pager<User> searchUser(String name) {
        return userDao.searchUser(name);
    }

    @Override
    public Pager<User> getUserByLanguage(String language) {
        return userDao.getUserByLanguage(language);
    }

    @Override
    public Pager<User> getUserByCompany(String company) {
        return userDao.getUserByCompany(company);
    }

    @Override
    public Pager<User> getUserByLan_Com(String language,String company) {
        return userDao.getUserByLan_Com(language, company);
    }

    @Override
    public List<String> getContriRepo(String login) {
        return userDao.getContriRepo(login);
    }

    @Override
    public List<String> getCollaRepo(String login) {
        return userDao.getCollaRepo(login);
    }

    @Override
    public List<String> getOwn(String login) {
        return userDao.getOwn(login);
    }

    @Override
    public List<Day> getCommitCalendar(String login){
        return userDao.getCommitCalendar(login);
    }
}
