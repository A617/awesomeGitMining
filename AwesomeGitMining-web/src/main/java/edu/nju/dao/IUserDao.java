package edu.nju.dao;

import edu.nju.model.Pager;
import edu.nju.model.Repository;
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

   // int countAll();

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


    /**
     * 根据语言和公司筛选用户
     * @param language,company
     * @return
     */

    Pager<User> getUserByLan_Com(String language, String company);
    /**
     * 根据用户名筛选贡献过的项目
     * @param login
     * @return
     */
    List<String> getContriRepo(String login);
    /**
     * 根据用户名筛选合作过的项目
     * @param login
     * @return
     */
    List<String> getCollaRepo(String login);
    /**
     * 根据用户名筛选创建过的项目
     * @param login
     * @return
     */
    List<String> getOwn(String login);
}
