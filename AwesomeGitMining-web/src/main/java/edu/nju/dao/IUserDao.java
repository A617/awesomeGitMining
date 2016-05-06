package edu.nju.dao;

import edu.nju.model.Pager;
import edu.nju.model.User;

import java.util.List;

/**
 * Created by Dora on 2016/5/3.
 */
public interface IUserDao {

    List<User> selectAll();

    /**
     * 查找一个用户
     * @param login
     * @return
     */
    User selectByLogin(String login);

    /**
     * 分页查看所有用户
     * @return
     */
    Pager<User> selectAllWithPager();

    /**
     * 模糊搜索用户
     * @param name
     * @return
     */
    Pager<User> searchUser(String name);

    int countAll();
}
