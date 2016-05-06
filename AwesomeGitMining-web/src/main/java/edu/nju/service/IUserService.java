package edu.nju.service;

import edu.nju.model.Pager;
import edu.nju.model.User;

import java.util.List;

/**
 * Created by Dora on 2016/5/2.
 */
public interface IUserService {

    List<User> getAllUsers();

    User getUserByLogin(String login);

    int getUserTotal();

    Pager<User> searchUser(String name);
}
