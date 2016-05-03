package edu.nju.dao;

import edu.nju.model.Pager;
import edu.nju.model.User;

import java.util.List;

/**
 * Created by Dora on 2016/5/3.
 */
public interface IUserDao {

    List<User> selectAll();

    User selectByLogin(String login);

    Pager<User> selectAllWithPager();

    int countAll();
}
