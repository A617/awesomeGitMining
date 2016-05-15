package edu.nju.service;

import edu.nju.model.Pager;
import edu.nju.model.User;

import java.util.List;

/**
 * Created by Dora on 2016/5/2.
 */
public interface IUserService {
    /**
     * 分页得到所有的user
     * @return
     */
    List<User> getAllUsers();

    /**
     * 根据登录名得到唯一的user
     * @param login
     * @return
     */
    User getUserByLogin(String login);

    /**
     * 得到user的总数
     * @return
     */
    int getUserTotal();

    /**
     * 分页模糊搜索用户
     * @param name
     * @return
     */
    Pager<User> searchUser(String name);

    /**
     * 根据语言筛选用户
     * @param language
     * @return
     */
    Pager<User> getUserByLanguage(String language);

    /**
     * 根据公司筛选用户
     * @param company
     * @return
     */
    Pager<User> getUserByCompany(String company);
}
