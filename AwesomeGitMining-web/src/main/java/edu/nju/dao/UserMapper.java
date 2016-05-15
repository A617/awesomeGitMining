package edu.nju.dao;

import edu.nju.model.Pager;
import edu.nju.model.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    User selectByLogin(String login);

    List<User> selectAllWithPager(Map<String,Object> params);

    List<User> searchUser(Map<String,Object> params);

    int countAll();

    int countSearch(String name);

    List<Map<String,Object>> countFirst30Companys();

    List<User> selectUserByLanguage(Map<String,Object> params);

    List<User> selectUserByCompany(Map<String,Object> params);

    int countLanguage(String language);

    int countCompany(String company);

}